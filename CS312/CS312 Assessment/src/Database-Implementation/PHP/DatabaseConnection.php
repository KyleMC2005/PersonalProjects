<?php
//Connect to MySQL
$host = "devweb2025.cis.strath.ac.uk";//Host to get from
$user = "mjb23137";// My DS Username
$pass = "hai7deelohCh";//My MySQL password
$dbname = $user;
$conn = new mysqli($host, $user, $pass, $dbname);

if ($conn->connect_error){
    die("Connection failed : ".$conn->connect_error); //FIXME remove once working - leaks database info so increasing hacking chances.
}

//Issue the query
$sql = "SELECT * FROM `table`";//note do not have `abc01234`. in front of the table name if you used $dbname to connect
$result = $conn->query($sql);

if (!$result){
    die("Query failed ".$conn->error); //FIXME remove once working.
}

//Handle the results
echo "<p>".$result->num_rows." rows found</p>";

//Disconnect
$conn->close();

?>
