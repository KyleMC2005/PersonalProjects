<html lang = "en">
<head>
    <meta charset="UTF-8">
    <title>MySQL Select</title>
</head>
<body>
<h1>MySQL Template</h1>
<?php
    function safePost($conn, $name) {
        return isset($_POST[$name])?$conn->real_escape_string(strip_tags($_POST[$name])):"";
    }

    // Connect to MySQL
    $host = "devweb2025.cis.strath.ac.uk"; // Set year for Devweb
    $user = "mjb23137"; // Set your username
    $pass = "lf56yWb58v$"; // Your MySQL password, to obviously not fucking leak yourself
    $dbname = $user;

if (!function_exists('mysqli_init') && !extension_loaded('mysqli')) {
    echo 'We don\'t have mysqli!!!';
} else {
    echo 'Phew we have it!';
}

    $conn = new mysqli($host, $user, $pass, $dbname);

    if ($conn->connect_error) {
        die("Connection failed : ".$conn->connect_error); // FIXME remove details once working
    }

$sql = "SELECT * FROM `CS312Phonebook`;";
    $result = $conn->query($sql);

    if (!$result) {
        die("Query failed ".$conn->error); // FIXME remove details once working
    }

    echo "<p>".$result->num_rows. "rows found.</p>";

    // Disconnect
    $conn->close();
    ?>
</body>
</html>