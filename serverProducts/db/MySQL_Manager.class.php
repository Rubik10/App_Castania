<?php
/**
 *  DBManager - Clase de conexion a BD
 *
 * @author		Author: Rubén Maeso Nova
 * @version      0.1
 *
 */

require("./Log.class.php");
header('Access-Control-Allow-Origin: *');

class MySQL_Manager {
    
    private $pdo; 	 # @object, The PDO object
    private $con; 	 # @object, Instance of conecction SQL
	private $sQuery;  		 # @object, PDO statement object
	private $parameters; 	 # @array, The parameters of the SQL query
   
	private $log; 			 # @object, Object for logging exceptions	
	private $settings;		 # @array,  The database settings

	private $_Connected = false;	# @bool ,  Connected to the database
    

      /**
	*     Default Constructor 
	*	1. Connect to database.
	*	2. Creates the parameter array.
	*/
    public function __construct() {
    	echo("<script>console.log('construct MYSQLMANAGER');</script>");

        try {
            $this->log = new Log();	
            $this->connect();
            $this->parameters = array();
        } catch (PDOException $ex) {
                # Write into log
            echo $this->ExceptionLog($ex->getMessage());
            die();
        }
    }

    /***
	*		------------------------------------------
	*				 REQUEST DATABASE
	*		------------------------------------------
    */
    
    public function connect() {
    	$this->settings = parse_ini_file("settings.ini.php");
		$cn = 'mysql:dbname='.$this->settings["dbname"].';host='.$this->settings["host"].'';
			
			try  {
					# Read settings from INI file, set UTF8
				$this->pdo = new PDO($cn, $this->settings["user"], $this->settings["password"], array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8"));
					# We can now log any exceptions on Fatal error. 
				$this->pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
					# Disable emulation of prepared statements, use REAL prepared statements instead.
				$this->pdo->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
					# Connection succeeded, set the boolean to true.
				$this->_Connected = true;
				echo("<script>console.log('conectado');</script>");

			} catch (PDOException $ex) {
					# Write into log
				echo $this->ExceptionLog($ex->getMessage());
				die();
			}
    }
    
   public function getConnection() {
        if ($this->con == null) {
            $this->con = new MySQL_Manager();
        }
        return $this->con;
    }
    
    /*
	 *  Close the PDO connection
	 */
    public function disconnect() {
        // self::$pdo = null;
        if ($this->con != null) {
         	$this->pdo = null;
        }
        
    }

    /***
	*		------------------------------------------
	*				 CONSULTAS BASE DE DATOS
	*		------------------------------------------
    */

       /**
	*	Every method which needs to execute a SQL query uses this method.
	*	
	*	1. If not connected, connect to the database.
	*	2. Prepare Query.
	*	3. Parameterize Query.
	*	4. Execute Query.	
	*	5. On exception : Write Exception into the log + SQL query.
	*	6. Reset the Parameters.
	*/	

		private function InitQuery ($query,$parameters = "") {
			 echo("<script>console.log('Init Query');</script>");
			# Connect to database
		//if(!getConnection()) { $this->connect(); }
		
		try {
				# Prepare query
			$this->sQuery = $this->pdo->prepare($query);
				
				# Add parameters to the parameter array	
			$this->bindMore($parameters);

				# Bind parameters
			if (!empty($this->parameters)) {
				foreach($this->parameters as $param) {
					$parameters = explode("\x7F",$param);
					$this->sQuery->bindParam($parameters[0],$parameters[1]);
				}		
			}
				# Execute SQL 
				$this->succes = $this->sQuery->execute();		
			} catch (PDOException $ex) {
				# Write into log and display Exception
				echo $this->ExceptionLog($ex->getMessage(), $query );
				die();
			}
				# Reset the parameters
			$this->parameters = array();
		}


	 /**
	*   	If the SQL query  contains a SELECT or SHOW statement it returns an array containing all of the result set row
	*	If the SQL statement is a DELETE, INSERT, or UPDATE statement it returns the number of affected rows
	*
	*   @param  string $query
	*	@param  array  $params
	*	@param  int    $fetchmode
	*	@return mixed
	*/			
		public function executeQuery ($query, $params = null, $fetchmode = PDO::FETCH_ASSOC) {
			 echo("<script>console.log('manage typeof queryy');</script>");
			
			$query = trim($query); // quitar espacios
			$this->InitQuery($query,$params);

			# Which SQL statement is used -> Get type the action on Database (1st word)
			$rawStatement = explode(" ", $query); 
			$statement = strtolower($rawStatement[0]);
			
			if ($statement === 'select' || $statement === 'show') {  //Consultas
				return $this->sQuery->fetchAll($fetchmode);
			}
			elseif ( $statement === 'insert' ||  $statement === 'update' || $statement === 'delete' ) {
				 echo("<script>console.log('insert);</script>");
				return $this->sQuery->rowCount();	
			}	
			else {
				return NULL;
			}
		}
		
        /**
	*	
	*	Add the parameter to the parameter array
	*	@param string $para  
	*	@param string $value 
	*/	
		public function bind($param, $value) {	
			$this->parameters[sizeof($this->parameters)] = ":" . $param . "\x7F" . utf8_encode($value);
		}

	  /**
	*	
	*	Add more parameters to the parameter array
	*	@param array $parray
	*/	
		public function bindMore($parray) {
			if(empty($this->parameters) && is_array($parray)) {
				$columns = array_keys($parray);
				foreach($columns as $i => &$column)	{
					$this->bind($column, $parray[$column]);
				}
			}
		}



	 /**
       *  Returns the last inserted id.
       *  @return string
       */	
		public function lastInsertId() {
			return $this->pdo->lastInsertId();
		}	
		
       /**
	*	Returns an array which represents a column from the result set 
	*
	*	@param  string $query
	*	@param  array  $params
	*	@return array
	*/	
		public function column($query,$params = null) {
			$this->Init($query,$params);
			$Columns = $this->sQuery->fetchAll(PDO::FETCH_NUM);		
			
			$column = null;

			foreach($Columns as $cells) {
				$column[] = $cells[0];
			}

			return $column;	
		}


       /**
	*	Returns an array which represents a row from the result set 
	*
	*	@param  string $query
	*	@param  array  $params
	*   	@param  int    $fetchmode
	*	@return array
	*/	
		public function row ($query,$params = null,$fetchmode = PDO::FETCH_ASSOC) {				
			$this->Init($query,$params);
			return $this->sQuery->fetch($fetchmode);			
		}



       /**
	*	Returns the value of one single field/column
	*
	*	@param  string $query
	*	@param  array  $params
	*	@return string
	*/	
		public function single ($query,$params = null) {
			$this->Init($query,$params);
			return $this->sQuery->fetchColumn();
		}



       /**	
	* Writes the log and returns the exception
	*
	* @param  string $message
	* @param  string $sql
	* @return string
	*/
	private function ExceptionLog($message , $sql = "") {
		$exception  = 'Unhandled Exception. <br />';
		$exception .= $message;
		$exception .= "<br /> You can find the error back in the log.";

		if(!empty($sql)) {
			# Add the Raw SQL to the Log
			$message .= "\r\nRaw SQL : "  . $sql;
		}
			# Write into log
			$this->log->write($message);

		return $exception;
	}


    
}

?>

