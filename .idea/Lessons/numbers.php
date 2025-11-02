<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PHP Index</title>
</head>
<body>
    <h1>PHP Index</h1>
    <p><?php

        $x = 5; // correct way to do it
        $y = 2;
        $z = $x + $y;

        $a = 1;
        $b = 3;
        $c = $a / $b;
        /*int x = 5; wrong*/
        // . is for concatenation
        echo "$a / $b = " . round($c, 2);

        $d = "123x";
        $e = "321";
        echo "<br>\n";
        echo $d+$e;

        ?></p>
</body>
</html>
