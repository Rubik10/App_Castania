<?php 

	//echo 'dentro de insert';
	
	require ('History.php');


	

	//if($_SERVER['REQUEST_METHOD']=='POST') {

	 	$tittle = $_REQUEST['tittle'];
	 	$image = $_REQUEST['image'];

	 
	$register = new HistoryResgister ($tittle, $image);
	$register->insertNewHistory();
	
	echo "Successfully Uploaded";

//	}


 ?>