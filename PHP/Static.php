<?php

// Function to demonstrate static variables
function static_var() {
  
    // Static Variable
    static $num = 5;
    $sum = 2;
    
    $sum++;
    $num++;
    
    echo $num, "\n";
    echo $sum, "\n";
}

// First function call
static_var();

// Second function call
static_var();

?>