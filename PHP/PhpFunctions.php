<?php
$integer = 1;
$string = "word";
$array = [1,2,3,4,5];

class testobject {
    public $thing = "random thing";
    }
$object = new testobject();
$boolean = true;
$nulli = null;

echo "\nThe results are: \n\n";
echo "Is this a String: "; echo is_string($string);
echo "\nIs this an Array: "; echo is_array($array);
echo "\nIs this an Object: "; echo is_object($object);
echo "\nIs this a Boolean: "; echo is_bool($boolean);
echo "\nIs this a Null: "; echo is_null($nulli);
?>