<?php session_name("cart");session_start();         // Read the page and what session methods do on W3Schools


if (!isset($_SESSION['cart'])) {
    $_SESSION['cart'] = [];
}
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
    header("Location: https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/HTML-PHP/Carts&BookingPage.php");
    exit;
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>N`adair | Home</title>
    <?php echo '<link rel="icon" type="image/x-icon" href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/images/travel.png">';?>
    <link id="titleicon" rel="icon" type="image/x-icon" href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/images/travel.png">
    <!--Travel icons created by Freepik - Flaticon-->   <!--Crediting owner-->
    <!--https://www.flaticon.com/free-icons/travel-->
    <!--Intentionally made separate files so that failed compilations don't cause website to go down, only page-->
    <link rel="stylesheet" href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/CSS/core.css">
</head>
<body>
<!--Navigation Bar on Top-->
<nav id="HNavBar">
    <ul>
        <li class="left"><a href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/HTML-PHP/index.php">N`adair Tours</a></li>
        <li class="right"><a href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/HTML-PHP/ToursListPage.php">Tour Selection</a></li>
        <li><a href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/HTML-PHP/TourDetailsPage.php">Tour Details</a></li>
        <li><a href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/HTML-PHP/Carts&BookingPage.php">Cart</a></li>
    </ul>
</nav>
<!--Activate this in the final version, commented out during dev (Not required for Sessions)-->
<script>
    if (confirm("By using this page, you agree to the use of mandatory cookies to track the tours you add to the cart. By confirming this box " +
        "and using this website, you agree to this condition. Cookies will only be used for mandatory reasons and never for marketing purposes.") === false) {
        window.close();
    }
</script>
<br>
<!-- The video -->
<video autoplay muted loop id="myVideo">
    <source src="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/images/ScotlandVideo.mp4" type="video/mp4">
</video>

<div class="content">
    <h1 class="VideoTitle" >Who are we?</h1>
    <p>Welcome to N’adair Tours, Scotland’s premier eco-conscious travel provider.
        We believe that exploring our country’s breathtaking landscapes, rich cultural heritage, and vibrant local communities
        should go hand in hand with protecting them.</p>
</div>

<br>
<!--This is a search bar if I ever implement it-->
<!--<div class="topnav">-->
<!--    <input type="text" placeholder="Search..">-->
<!--</div>-->

<br><br>
<h2 id="checkoutour">Check our hottest tours!</h2>
<br><br><br><br>

<?php
require("DatabaseConnection.php");
$sqlBestGlasgow = "SELECT * FROM `CS312Tours` WHERE StarRating = '5' AND LocationTag = 'Glasgow'; ";//note do not have `abc01234`. in front of the table name if you used $dbname to connect
$resultBG = $conn->query($sqlBestGlasgow);

$sqlBestEdinburgh = "SELECT * FROM `CS312Tours` WHERE StarRating = '5' AND LocationTag = 'Edinburgh'; ";//note do not have `abc01234`. in front of the table name if you used $dbname to connect
$resultBE = $conn->query($sqlBestEdinburgh);

$sqlBestHighlands = "SELECT * FROM `CS312Tours` WHERE StarRating = '5' AND LocationTag = 'Highlands'; ";//note do not have `abc01234`. in front of the table name if you used $dbname to connect
$resultBH = $conn->query($sqlBestHighlands);

$sqlBestInverness = "SELECT * FROM `CS312Tours` WHERE StarRating = '5' AND LocationTag = 'Inverness'; ";//note do not have `abc01234`. in front of the table name if you used $dbname to connect
$resultBI = $conn->query($sqlBestInverness);

echo '<section class = "container">';    // Opens Container

if ($resultBG->num_rows > 0) {      // Best of Glasgow Result (Based on Star Rating on SQL Result) (if the amount of results is greater than 0)
    // output data of each row
    while ($row = $resultBG->fetch_assoc()) {
        echo '<div class = "container-entry">';
//        Slap a cheeky image here
        echo '<img src="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/images/GlasgowGhouls.jpg" alt="Glasgow Ghouls" width="350" height="350">';
        echo '<p>' . htmlspecialchars($row["TourName"]) . '</p>';
        echo '<p>' . htmlspecialchars($row["LocationTag"]) . '</p>';            // htmlspecialchars to prevent SQL injection, learned from W3Schools
        echo '<p>' . "£" . htmlspecialchars($row["Price"]) . '</p>';
//        Drop a short, dynamic description in here
//        Drop the price in here
        switch ($row["StarRating"]) {
            case "5":
                echo "★★★★★";
                break;
            case "4.5":
                echo "★★★★½";       // Couldn't find a half star version so just used ½
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
        echo '<br>';
//        Add 2 buttons here "for add to cart" & "Go to Booking"
        echo '<a id="button" href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/HTML-PHP/TourDetailsPage.php?id=' . $row["TourID"] . '">Tour Details</a>';
        echo '<form id="Cart" autocomplete="on" method="post" target="_self">';
        echo '<input type="hidden" id="Tid" name="TourID" value="' . $row["TourID"] . '">';
        echo '<input type="hidden" id="TName" name="TourName" value="' . $row["TourName"] . '">';
        echo '<input type="hidden" id="Loc" name="Location" value="' . $row["Location"] . '">';
        echo '<input type="hidden" id="Dur" name="Duration" value="' . $row["Duration"] . '">';
        echo '<input type="hidden" id="DepD" name="DepartureDate" value="' . $row["DepartureDate"] . '">';
        echo '<input type="hidden" id="Prc" name="Price" value="' . $row["Price"] . '">';
        echo '<input type="hidden" id="Desc" name="Description" value="' . $row["Description"] . '">';
        echo '<input type="hidden" id="Img1" name="imgLink" value="' . $row["imgLink"] . '">';
        echo '<input type="hidden" id="Img2" name="imgLink2" value="' . $row["imgLink2"] . '">';
        echo '<input type="hidden" id="Img3" name="imgLink3" value="' . $row["imgLink3"] . '">';
        echo '<input type="hidden" id="Tag" name="Tags" value="' . $row["Tags"] . '">';
        echo '<input type="hidden" id="LocT" name="LocationTag" value="' . $row["LocationTag"] . '">';
        echo '<input type="hidden" id="StarR" name="StarRating" value="' . $row["StarRating"] . '">';
        echo '<input type="submit" value="Add to Cart" name="submit" class="submit">';
        echo '</form>';
    }
    echo '</div>';
}

if ($resultBE->num_rows > 0) {      // Best of Edinburgh Result (Based on Star Rating on SQL Result)
    // output data of each row
    while ($row = $resultBE->fetch_assoc()) {
        echo '<div class = "container-entry">';
//        Slap a cheeky image here
        echo '<img src="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/images/NMuseumScotland.jpg" alt="National Museum" width="350" height="350">';
        echo '<p>' . htmlspecialchars($row["TourName"]) . '</p>';
        echo '<p>' . htmlspecialchars($row["LocationTag"]) . '</p>';
        echo '<p>' . "£" . htmlspecialchars($row["Price"]) . '</p>';
//        Drop a short, dynamic description in here
//        Drop the price in here
        echo '<br>';
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
        echo '<br>';
//        echo '<p>'.htmlspecialchars($row["StarRating"]).'</p>';
//        echo '<p>' . htmlspecialchars($row["Description"]) . '</p>';
//        Add 2 buttons here "for add to cart" & "Go to Booking"
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
        echo '</div>';
    }
}
if ($resultBH->num_rows > 0) {      // Best of Highlands Result (Based on Star Rating on SQL Result)
    // output data of each row
    while ($row = $resultBH->fetch_assoc()) {
        echo '<div class = "container-entry">';
//        Slap a cheeky image here
        echo '<img src="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/images/IsleofSkye.avif" alt="Isle of Skye" width="350" height="350">';
        echo '<p>' . htmlspecialchars($row["TourName"]) . '</p>';
        echo '<p>' . htmlspecialchars($row["LocationTag"]) . '</p>';
        echo '<p>' . "£" . htmlspecialchars($row["Price"]) . '</p>';
//        Drop a short, dynamic description in here
//        Drop the price in here
        echo '<br>';
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
        echo '</div>';
    }
}

if ($resultBI->num_rows > 0) {      // Best of Inverness Result (Based on Star Rating on SQL Result)
    // output data of each row
    while ($row = $resultBI->fetch_assoc()) {
        echo '<div class = "container-entry">';
//        Slap a cheeky image here
        echo '<img src="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/images/InvernessCathedral.jpg" alt="Inverness Cathedral" width="350" height="350">';
        echo '<p>' . htmlspecialchars($row["TourName"]) . '</p>';
        echo '<p>' . htmlspecialchars($row["LocationTag"]) . '</p>';
        echo '<p>' . "£" . htmlspecialchars($row["Price"]) . '</p>';
//        Drop a short, dynamic description in here
//        Drop the price in here
        echo '<br>';
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
        echo '<a id="button" href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/HTML-PHP/TourDetailsPage.php?id=' . $row["TourID"] . '">Tour Details</a>';
        echo '<form id="Cart" autocomplete="on" method="post" target="_self">';
        echo '<input type="hidden" id="Tid" name="TourID" value="' . $row["TourID"] . '">';
        echo '<input type="hidden" id="TName" name="TourName" value="' . $row["TourName"] . '">';
        echo '<input type="hidden" id="Loc" name="Location" value="' . $row["Location"] . '">';
        echo '<input type="hidden" id="Dur" name="Duration" value="' . $row["Duration"] . '">';
        echo '<input type="hidden" id="DepD" name="DepartureDate" value="' . $row["DepartureDate"] . '">';
        echo '<input type="hidden" id="Prc" name="Price" value="' . $row["Price"] . '">';
        echo '<input type="hidden" id="Desc" name="Description" value="' . $row["Description"] . '">';
        echo '<input type="hidden" id="Img1" name="imgLink" value="' . $row["imgLink"] . '">';
        echo '<input type="hidden" id="Img2" name="imgLink2" value="' . $row["imgLink2"] . '">';
        echo '<input type="hidden" id="Img3" name="imgLink3" value="' . $row["imgLink3"] . '">';
        echo '<input type="hidden" id="Tag" name="Tags" value="' . $row["Tags"] . '">';
        echo '<input type="hidden" id="LocT" name="LocationTag" value="' . $row["LocationTag"] . '">';
        echo '<input type="hidden" id="StarR" name="StarRating" value="' . $row["StarRating"] . '">';
        echo '<input type="submit" value="Add to Cart" name="submit" class="submit">';
        echo '</form>';
        echo '</div>';
    }
}
echo '</section>';  // Closes Container
$conn->close();
?>
<!--Search bar-->
<!--Never made one but in next iteration maybe-->
<br><br><br>

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


<!--This is the code for an automated slideshow, wanted to make it so when you hover over a container entry it would play but couldn't get it working for that scenario-->
<!--If I had more time I would like to set it up, it worked in Tour Details but I replaced it with a standard slideshow, source code from W3Schools-->
<!--Use this for the images on all pages, make it a slideshow-->
<!--echo '<div class="autoslideshow-container">';-->
<!---->
<!--    echo '<div class="mySlides autofade">';-->
<!--        echo '<div class="numbertext">1 / 3</div>';-->
<!--        echo '<img src="' . htmlspecialchars($row["imgLink"]) . '" alt="' . htmlspecialchars($row["TourName"]) . '" class="center-image" width="800px" height="500px">';-->
<!--        echo '<div class="text">Caption Text</div>';-->
<!--        echo '</div>';-->
<!---->
<!--    echo '<div class="mySlides autofade">';-->
<!--        echo '<div class="numbertext">2 / 3</div>';-->
<!--        echo '<img src="' . htmlspecialchars($row["imgLink2"]) . '" alt="' . htmlspecialchars($row["TourName"]) . '" class="center-image" width="800px" height="500px">';-->
<!--        echo '<div class="text">Caption Two</div>';-->
<!--        echo '</div>';-->
<!---->
<!--    echo '<div class="mySlides autofade">';-->
<!--        echo '<div class="numbertext">3 / 3</div>';-->
<!--        echo '<img src="' . htmlspecialchars($row["imgLink3"]) . '" alt="' . htmlspecialchars($row["TourName"]) . '" class="center-image" width="800px" height="500px">';-->
<!--        echo '<div class="text">Caption Three</div>';-->
<!--        echo '</div>';-->
<!---->
<!--    echo '</div>';-->
<!--echo '<br>';-->
<!---->
<!--echo '<div style="text-align:center">';-->
<!--    echo '<span class="autodot"></span>';-->
<!--    echo '<span class="autodot"></span>';-->
<!--    echo '<span class="autodot"></span>';-->
<!--    echo '</div>';-->

<!--<script>-->
<!--    let slideIndex = 0;-->
<!--    showSlides();-->
<!---->
<!--    function showSlides() {-->
<!--        let i;-->
<!--        let slides = document.getElementsByClassName("mySlides");-->
<!--        let dots = document.getElementsByClassName("dot");-->
<!--        for (i = 0; i < slides.length; i++) {-->
<!--            slides[i].style.display = "none";-->
<!--        }-->
<!--        slideIndex++;-->
<!--        if (slideIndex > slides.length) {slideIndex = 1}-->
<!--        for (i = 0; i < dots.length; i++) {-->
<!--            dots[i].className = dots[i].className.replace(" active", "");-->
<!--        }-->
<!--        slides[slideIndex-1].style.display = "block";-->
<!--        dots[slideIndex-1].className += " active";-->
<!--        setTimeout(showSlides, 2000); // Change image every 2 seconds-->
<!--    }-->
<!--</script>-->


</body>
</html>