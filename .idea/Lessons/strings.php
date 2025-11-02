<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PHP Index</title>
</head>
<body>
    <h1>PHP Index</h1>
    <p><?php

        $x = 5;
        $y = 2;
        $z = $x / $y;

        echo "$x / $y = " . round($z, 2);

        echo "<br>\n";

        $a = "123";
        $b = "321";
        echo $a+$b; //intended plus to add

        $s1 = "Hello";
        $s2 = "World";
        echo "<br>\n";
        echo $s1.$s2."<br>\n";

        // Upper Case Function
        echo strtoupper($s1)."<br>\n";

        // Reverse Function
        echo strrev($s1)."<br>\n";

        // Length of String Function
        echo strlen($s1)."<br>\n";

        // Substring Function
        echo substr($s2, 1, 3)."<br>\n";

        // Replace Characters Function
        echo str_replace("l", "-", $s1)."<br>\n";

        ?></p>
</body>
</html>
