<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Testing</title>
</head>
<body>
<h1>Your pet's age</h1>
<?php
$today = date_create();
$petage = date_create("2023-07-14");
$now = date_diff($petage, $today);
echo "Your dog is ".$now->format("%a")." days old";
?>
</body>
</html>