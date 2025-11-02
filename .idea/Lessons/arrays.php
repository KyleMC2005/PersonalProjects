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

    echo "</p><p>\n";

    $price = 49;
    if ($price>= 100) {
        echo "That's too expensive.";
    } else { echo "That's fine thank you.";
    }
    echo ($price<50) ? " - it is very cheap!" : "";

    echo "<br>\n";
    echo "1 --".(TRUE==TRUE)."--<br>\n";
    echo "2 --".(False==TRUE)."--<br>\n";
    echo "3 --".($a==123)."--<br>\n";
    echo "4 --".(TRUE===TRUE)."--<br>\n";
    echo "5 --".(FALSE===TRUE)."--<br>\n";
    echo "6 --".($a===123)."--<br>\n"; // are these of the same type? If not, no comparison therefore false (same type & value for true)
    //  This should also be default in your code
    echo "7 --".($a==="123")."--<br>\n";

    $names = array("Mark", "Isla", "Bob", "Jane");
    for ($i = 0; $i<count($names); $i++) {
        echo $names[$i]. " ";
    }
    array_push($names, "Sam");
    echo "<br>";
    foreach ($names as $name) {
        echo "$name ";
    }

    $phones = array("Mark"=>3497, "Isla"=> 1111, "Bob" => 2222, "Jane" =>3333);
    $phones["Sam"]=4444;
    unset($phones["Jane"]);
    ksort($phones);
    echo "<br>\n";
    foreach($phones as $name=>$number) {
        echo "$name is on ext $number <br>";
    }



    ?></p>
</body>
</html>
