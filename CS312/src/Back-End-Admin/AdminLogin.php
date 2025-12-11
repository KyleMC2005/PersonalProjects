<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>N`adair | Login</title>
    <?php echo '<link rel="icon" type="image/x-icon" href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/images/travel.png">';?>
    <link id="titleicon" rel="icon" type="image/x-icon" href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/images/travel.png">
    <!--Travel icons created by Freepik - Flaticon-->   <!--Crediting owner-->
    <!--https://www.flaticon.com/free-icons/travel-->
    <!--Intentionally made separate files so that failed compilations don't cause website to go down, only page-->
    <link rel="stylesheet" href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/CSS/admin.css">
</head>
<body>


    <br>
<h1 id="Heading">Admin Login Page</h1>
    <div class="adminlogincontainer">
    <form id="login" action="AdminLogin.php" method="POST">
        <img src="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/images/travel.png" width="7.5%" height="7.5%">
        <br>
        <br>
        <br>
        <label class="AdminLoginLabel" for="uname"><b>Username: *</b></label>
    <input type="text" name="uname" placeholder="username..." required><br>
        <label class="AdminLoginLabel" for="pword"><b>Password: *</b></label>
    <input type="password" name="pword" placeholder="password..." required><br>
    <input class="LoginSubmit" type="submit" value="Login">
    </form>
    </div>


<?php require("/home/mjb23137/DEVWEB/2025/CS312 Assessment/src/Front-End/HTML-PHP/DatabaseConnection.php");
// Username and Password have to be correct to work, either hardcode it or make a database for it
$correctUser = "admin";
$correctPass = "1234";

// For quickness made it hardcoded, with more time I would make a database full of accounts instead

if ($_SERVER["REQUEST_METHOD"] === "POST") {
    $username = $_POST["uname"];
    $password = $_POST["pword"];

    // Compare form input with hardcoded credentials
    if ($username === $correctUser && $password === $correctPass) {
        header("Location: /~mjb23137/CS312 Assessment/src/Back-End-Admin/Admin.php");
        exit;
    } else {
        echo "Sign-in was not successful...";
    }
}
?>

</body>
</html>

