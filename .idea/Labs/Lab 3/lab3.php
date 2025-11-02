<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lab 3 Task</title>
</head>
<body>
<h1>Lab 3 Form</h1>
<?php
    $forename  = strip_tags(isset($_GET["forename"]) && $_GET["forename"] !== "" ? $_GET["forename"] : "");
    $surname = strip_tags(isset($_GET["surname"]) && $_GET["surname"] !== "" ? $_GET["surname"] : "");
    $title = isset($_GET["title"]) && $_GET["title"] !== "" ? $_GET["title"] : "";
    date_default_timezone_set('Europe/London');

    if ($forename != "" && $surname != "" && $title != "") {
        echo "Good evening, $title $forename $surname.";
    } else {
        if ($_SERVER['REQUEST_METHOD'] === 'GET' | $forename == "" | $surname == "" | $title == "") {
            echo "<p>Form completion errors - please check all fields.</p>";
        }

?>
<form action="lab3.php" method="get">
    <select name="title" id="title">
        <option value="">Title</option>
        <option value="Dr">Dr</option>
        <option value="Sir">Sir</option>
        <option value="Dame">Dame</option>
        <option value="Mr">Mr</option>
        <option value="Miss">Miss</option>
        <option value="Mrs">Mrs</option>
        <option value="Ms">Ms</option>
    </select>
    <p>Forename: <input type="text" name="forename"></p>
    <p>Surname: <input type="text" name="surname"></p>
    <p><input type="submit" value="Submit"></p>
</form>
<?php
    }
?>
</body>
</html>
