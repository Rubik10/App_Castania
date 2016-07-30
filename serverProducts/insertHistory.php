<?php 

/*require ('products/History.php');

	echo 'dentro de insert';*/

	 //open connection to mysql db
    $connection = mysqli_connect("localhost","root","root","productsAndroid") or die("Error " . mysqli_error($connection));

    if($_SERVER['REQUEST_METHOD']=='POST') {
		$tittle = $_REQUEST['tittle'];
 		$image = $_REQUEST['image'];	
	}

	$sql = "INSERT INTO HISTORICO (tittle,imgUrl) VALUES ('".$tittle."','".$image."')";
	echo $sql;

	 $result = mysqli_query($connection, $sql) or die("Error in inserting " . mysqli_error($connection));
 
	/* if(mysqli_query($connection,$sql)){
	 	//file_put_contents($path,base64_decode($image));
	 	echo "Successfully Uploaded";
	 }
	 
	 mysqli_close($connection);
	 	}else{
	 	echo "Error";
	 }*/

 	


	//$register = new History ($tittle, $image);
	//$register->insertNewHistory();
	
	//echo "Successfully inserted";




 ?>