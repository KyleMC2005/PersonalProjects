<?php session_name("cart");session_start();
if (isset($_POST["submit"])) { // Means button was pressed
    // Ensure session data cannot have any chances for SQL injection
    // Set session variables

    $cart = array(
        'TourID' => htmlspecialchars($_POST["TourID"]),
        'TourName' => htmlspecialchars($_POST["TourName"]),
        'Location' => htmlspecialchars($_POST["Location"]),
        'DepartureDate' => htmlspecialchars($_POST["DepartureDate"]),
        'Price' => htmlspecialchars($_POST["Price"]),
        'Description' => htmlspecialchars($_POST["Description"]),
        'imgLink' => htmlspecialchars($_POST["imgLink"]),
        'imgLink2' => htmlspecialchars($_POST["imgLink2"]),
        'imgLink3' => htmlspecialchars($_POST["imgLink3"]),
        'Tags' => htmlspecialchars($_POST["Tags"]),
        'LocationTag' => htmlspecialchars($_POST["LocationTag"]),
        'StarRating' => htmlspecialchars($_POST["StarRating"])
    );

    // Check if session already exists, if it does append, if not make a new one
    if (isset($_SESSION['cart'])) {
        // Append the new tour to the cart array
        $_SESSION['cart'][] = $cart;
    } else {
        // If no cart exists, create a new one with the current tour
        $_SESSION['cart'] = array($cart);
    }
    header("Location: https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/HTML-PHP/Carts&BookingPage.php");
    exit;
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>N`adair | Highlands Tours</title>
    <?php echo '<link rel="icon" type="image/x-icon" href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/images/travel.png">';?>
    <!--Travel icons created by Freepik - Flaticon-->   <!--Crediting owner-->
    <!--https://www.flaticon.com/free-icons/travel-->
    <link rel="stylesheet" href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/CSS/core.css">
</head>
<body>
<!--Navigation Bar on Top-->
<nav id="HNavBar">
    <ul>
        <li class="left"><a href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/HTML-PHP/index.php">N`adair Tours</a></li>
        <li><a href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/HTML-PHP/ToursListPage.php">Tour Selection</a></li>
        <li><a href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/HTML-PHP/TourDetailsPage.php">Tour Details</a></li>
        <li><a href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/HTML-PHP/Carts&BookingPage.php">Cart</a></li>
    </ul>
</nav>
<br>
<h1 id="IntroHead">Highlands Tours!</h1>
<h2 id="HeadMid">Take a look at some of the tours we believe you would enjoy!</h2>
<br>
<h2 id="HeadLower">Top Tours from Highlands</h2>
<?php require("DatabaseConnection.php");
$sqlBestHighlands = "SELECT * FROM `CS312Tours` WHERE `LocationTag` = 'Highlands' ORDER BY StarRating DESC; ";//note do not have `abc01234`. in front of the table name if you used $dbname to connect
$resultBH = $conn->query($sqlBestHighlands);


echo '<section class = "container">';    // Opens Container

if ($resultBH->num_rows > 0) {      // Best of Glasgow Result (Based on Star Rating on SQL Result)
    // output data of each row
    while ($row = $resultBH->fetch_assoc()) {
        echo '<div class = "container-entry">';
//        Slap a cheeky image here
        echo '<img src="' .htmlspecialchars($row["imgLink"]).'" alt="'.htmlspecialchars($row["TourName"]).'" width="350" height="350">';
        echo '<p>' . htmlspecialchars($row["TourName"]) . '</p>';
        echo '<p>' . htmlspecialchars($row["LocationTag"]) . '</p>';
        echo '<p>' . "£" . htmlspecialchars($row["Price"]) . '</p>';

//        Drop a short, dynamic description in here
//        Drop the price in here
        switch ($row["StarRating"]) {
            case "5":
                echo "★★★★★";
                break;
            case "4.5":
                echo "★★★★½";
                break;
            case "4":
                echo "★★★★";
                break;
            case "3.5":
                echo "★★★½";
                break;
            case "3":
                echo "★★★";
                break;
            case "2.5":
                echo "★★½";
                break;
            case "2":
                echo "★★";
                break;
            case "1.5":
                echo "★½";
                break;
            case "1":
                echo "★";
                break;
            case "0.5":
                echo "½";
                break;
        }
        echo '<a id="button" href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/HTML-PHP/TourDetailsPage.php?id='.$row["TourID"].'">Tour Details</a>';
        echo '<form id="Cart" autocomplete="on" method="post" target="_self">';
        echo '<input type="hidden" id="Tid" name="TourID" value="'.$row["TourID"].'">';
        echo '<input type="hidden" id="TName" name="TourName" value="'.$row["TourName"].'">';
        echo '<input type="hidden" id="Loc" name="Location" value="'.$row["Location"].'">';
        echo '<input type="hidden" id="Dur" name="Duration" value="'.$row["Duration"].'">';
        echo '<input type="hidden" id="DepD" name="DepartureDate" value="'.$row["DepartureDate"].'">';
        echo '<input type="hidden" id="Prc" name="Price" value="'.$row["Price"].'">';
        echo '<input type="hidden" id="Desc" name="Description" value="'.$row["Description"].'">';
        echo '<input type="hidden" id="Img1" name="imgLink" value="'.$row["imgLink"].'">';
        echo '<input type="hidden" id="Img2" name="imgLink2" value="'.$row["imgLink2"].'">';
        echo '<input type="hidden" id="Img3" name="imgLink3" value="'.$row["imgLink3"].'">';
        echo '<input type="hidden" id="Tag" name="Tags" value="'.$row["Tags"].'">';
        echo '<input type="hidden" id="LocT" name="LocationTag" value="'.$row["LocationTag"].'">';
        echo '<input type="hidden" id="StarR" name="StarRating" value="'.$row["StarRating"].'">';
        echo '<input type="submit" value="Add to Cart" name="submit" class="submit">';
        echo '</form>';
        if (isset($_POST["submit"])) { // Means button was pressed
            // Ensure session data cannot have any chances for SQL injection
            // Set session variables

            $cart = array(
                'TourID' => htmlspecialchars($_POST["TourID"]),
                'TourName' => htmlspecialchars($_POST["TourName"]),
                'Location' => htmlspecialchars($_POST["Location"]),
                'DepartureDate' => htmlspecialchars($_POST["DepartureDate"]),
                'Price' => htmlspecialchars($_POST["Price"]),
                'Description' => htmlspecialchars($_POST["Description"]),
                'imgLink' => htmlspecialchars($_POST["imgLink"]),
                'imgLink2' => htmlspecialchars($_POST["imgLink2"]),
                'imgLink3' => htmlspecialchars($_POST["imgLink3"]),
                'Tags' => htmlspecialchars($_POST["Tags"]),
                'LocationTag' => htmlspecialchars($_POST["LocationTag"]),
                'StarRating' => htmlspecialchars($_POST["StarRating"])
            );

            if (isset($_SESSION['cart'])) {
                // Append the new tour to the cart array
                $_SESSION['cart'][] = $cart;
            } else {
                // If no cart exists, create a new one with the current tour
                $_SESSION['cart'] = array($cart);
            }
        }
        echo '</div>';
    }
}

echo '</section>';
$conn->close();
?>

<br><h2 id="TourPag">Check out some more of our tours:</h2>
<ul class="pagination">
    <li><a href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/HTML-PHP/ToursListPage2.php">&laquo;</a></li>
    <li><a href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/HTML-PHP/ToursListPage.php">1</a></li>
    <li><a href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/HTML-PHP/ToursListPage2.php">2</a></li>
    <li><a class="active" href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/HTML-PHP/ToursListPage3.php">3</a></li>
    <li><a href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/HTML-PHP/ToursListPage4.php">4</a></li>
    <li><a href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/HTML-PHP/ToursListPage4.php">&raquo;</a></li>
</ul>
<div class="FooterDiv">
    <br>
    <!--Showing we comply with and align with UN Sustainable Development Goals-->
    <footer>
        <p id="SDG">Our guided tours are designed to immerse you in the authentic spirit of Scotland while promoting
            responsible travel practices that align with the UN Sustainable Development Goals — particularly
            Goal 11: Sustainable Cities and Communities, Goal 12: Responsible Consumption and Production,
            and Goal 15: Life on Land. With N’adair Tours, every journey leaves a positive mark on both travellers
            and the land we call home.</p>
    </footer>
    <br>
</div>
</body>
</html>