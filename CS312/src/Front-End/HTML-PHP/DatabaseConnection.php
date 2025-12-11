<?php
//Connect to MySQL
$host = "devweb2025";//Host to get from
$user = "mjb23137";// My DS Username
$pass = 'hai7deelohCh';//My MySQL password
$dbname = $user;
$conn = new mysqli($host, $user, $pass, $dbname);

//Issue the query
$sql = "SELECT * FROM `CS312Tours`";
$result = $conn->query($sql);

// I learned this section through the videos on the week they were uploaded
?>