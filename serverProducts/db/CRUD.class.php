<?php 
/**
* CRUD  -  This class allow a general read,insert,update and delete :) 
*
* @author		Ruben Maeso Nova
* @version      0.1a
*/

require_once ("MySQL_Manager.class.php");

class CRUD {

	private $con;
    private $variables;
   // private $table,$pk;

    public function __construct($data = array()) {
        echo("<script>console.log('construct CRUD');</script>");
        $this ->con =  new MySQL_Manager();
        $this ->variables  = $data;
    }

  /*  public function __construct($data = array(), $table, $pk) { 
        $this ->con =  new MySQL_Manager();
        $this ->variables  = $data;
        $this ->table = $table;
        $this ->pk = $pk;
    }*/


    public function __set($name, $value){
        if(strtolower($name) === $this ->pk) {
            $this->variables[$this->pk] = $value;
        }
        else {
            $this->variables[$name] = $value;
        }
    }

    public function __get($name)
    {
        if(is_array($this->variables)) {
            if(array_key_exists($name,$this->variables)) {
                return $this->variables[$name];
            }
        }

        $trace = debug_backtrace();
        trigger_error(
            'Undefined property via __get(): ' . $name .
            ' in ' . $trace[0]['file'] .
            ' on line ' . $trace[0]['line'],
            E_USER_NOTICE);
        return null;
    }

     public function insert ($table) {
        echo("<script>console.log('prepare insert query');</script>");
        
        $bindings       = $this->variables;

        if(!empty($bindings)) {
            $fields     =  array_keys($bindings);
            $fieldsvals =  array(implode(",",$fields),":" . implode(",:",$fields));
            $sql        = "INSERT INTO ".$table." (".$fieldsvals[0].") VALUES (".$fieldsvals[1].")";
        }
        else {
            $sql        = "INSERT INTO ".$table." () VALUES ()";
        }

       echo $sql;
        return $this->con->executeQuery($sql,$bindings);
    }

    public function update ($id = "0") {
        $this->variables[$this->pk] = (empty($this->variables[$this->pk])) ? $id : $this->variables[$this->pk];

        $fieldsvals = '';
        $columns = array_keys($this->variables);

        foreach($columns as $column)
        {
            if($column !== $this ->pk)
                $fieldsvals .= $column . " = :". $column . ",";
        }

        $fieldsvals = substr_replace($fieldsvals , '', -1);

        if(count($columns) > 1 ) {
            $sql = "UPDATE " . $this->table .  " SET " . $fieldsvals . " WHERE " . $this->pk . "= :" . $this->pk;
            return $this->con->executeQuery($sql,$this->variables);
        }
    }

    public function delete ($id = "") {
        $id = (empty($this->variables[$this->pk])) ? $id : $this->variables[$this->pk];

        if(!empty($id)) {
            $sql = "DELETE FROM " . $this->table . " WHERE " . $this->pk . "= :" . $this->pk. " LIMIT 1" ;
            return $this->con->executeQuery($sql,array($this->pk=>$id));
        }
    }

      public function getID ($mail) {
        echo $mail;
       // $query = $this->con->executeQuery("SELECT idUsuario FROM " . $this->table) . " WHERE CORREO = " . $mail . " LIMIT 1";
       // $query = $this->con->executeQuery("SELECT idUsuario FROM USUARIOS WHERE CORREO = " . $mail . " LIMIT 1";
        $query = $this->con->single("SELECT idUsuario FROM usuarios WHERE correo = :correo ", array('correo' => "'".$mail."'" ) );
        return $query;
    }


    public function find ($id = "") {
        $id = (empty($this->variables[$this->pk])) ? $id : $this->variables[$this->pk];

        if(!empty($id)) {
            $sql = "SELECT * FROM " . $this->table ." WHERE " . $this->pk . "= :" . $this->pk . " LIMIT 1";
            $this->variables = $this->con->row($sql,array($this->pk=>$id));
        }
    }

    public function getAll () {
        return $this->con->executeQuery("SELECT * FROM " . $this->table);
    }

    public function min($field)  {
        if($field)
            return $this->con->single("SELECT min(" . $field . ")" . " FROM " . $this->table);
    }

    public function max($field)  {
        if($field)
            return $this->con->single("SELECT max(" . $field . ")" . " FROM " . $this->table);
    }

    public function avg($field)  {
        if($field)
            return $this->con->single("SELECT avg(" . $field . ")" . " FROM " . $this->table);
    }

    public function sum($field)  {
        if($field)
            return $this->con->single("SELECT sum(" . $field . ")" . " FROM " . $this->table);
    }

    public function count($field)  {
        if($field)
            return $this->con->single("SELECT count(" . $field . ")" . " FROM " . $this->table);
    }

}
?>



