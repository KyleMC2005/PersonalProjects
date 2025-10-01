<?php

$num = 60;

function local_var(){
    // $num is local to this function

    // The variable $num outside the function
    // is a completely different
    $num = 50;
    echo "Variable num inside function is: $num \n";
}

local_var();

// The $num outside function is a completely
// different from inside local_var()
echo "Variable num outside function is: $num";

$num2 = 20;

//Function to demonstrate use of global variable
function global_var() {
    // We have to use global keyword before
    //the variable $num2 to access within 
    // the function
    global $num2;

    echo "\nVariable num outside function: $num2 \n";
}

global_var();

echo "Variable num outside function: $num2 \n";
?>