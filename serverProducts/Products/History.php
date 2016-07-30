<?php
/**
* tittles  -  This class contains all the funcions for tittles
*
* @author		Ruben Maeso Nova
* @version      0.1a
*/


require ("./db/CRUD.class.php");
	
	class History extends CRUD  { //implements tittlesInterface

			
		protected $table = "historico";
		//protected $pk	 = "ID";
		protected $history;

		private $tittle;
		private $imageURL;

		public function __construct( $tittle, $imageURL) {
			echo("<script>console.log('construct History');</script>");
			 $this ->$history = new CRUD();
			 $this ->$history->tittle = $tittle;
			 $this ->$history->imageURL  = $imageURL;

			 echo  $this ->$history->tittle;
		}

		// Create new tittle
		public function insertNewHistory () {
			try {
				echo("<script>console.log('action : newtRecord -> insertando history');</script>");
				$this->history->insert ($this->table); // usuarios
			} catch (Exception $ex) {
				echo $this->ExceptionLog($ex->getMessage());
			}
		}
   
	
	}
	
?>