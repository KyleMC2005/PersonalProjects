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
    <title>Tour Details</title>
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
    <h1 id="IntroHead">Tour Details</h1>
<?php require("DatabaseConnection.php");

// Prepare the sql statement to prevent injection
$prprTD = $conn->prepare("SELECT * FROM CS312Tours WHERE TourID = ? ORDER BY StarRating DESC");
$prprTD->bind_param("i", $_GET['id']);
$prprTD->execute();
$resultTD = $prprTD->get_result();       // resultTD holds result of query done safely

if ($resultTD->num_rows > 0) {      // Best of Glasgow Result (Based on Star Rating on SQL Result)
    // output data of each row
    while ($row = $resultTD->fetch_assoc()) {
        echo '<h1 id="detailHead">'. "Here's some information about " . htmlspecialchars($row["TourName"]) ."!".'</h1>';
        echo '<br>';
        echo '<p id="detail">' ."<b>".htmlspecialchars($row["LocationTag"]) ."</b>".'</p>';
        echo '<br>';
        echo '<br>';
echo '<div class="slideshow-container">';

  //<!-- Full-width images with number and caption text -->
  echo '<div class="mySlides fade">';
    echo '<div class="numbertext">1 / 3</div>';
        echo '<img src="' . htmlspecialchars($row["imgLink"]) . '" alt="' . htmlspecialchars($row["TourName"]) . '" class="center-image" width="100%" height="500px">';
  echo '</div>';

  echo '<div class="mySlides fade">';
    echo '<div class="numbertext">2 / 3</div>';
        echo '<img src="' . htmlspecialchars($row["imgLink2"]) . '" alt="' . htmlspecialchars($row["TourName"]) . '" class="center-image" width="100%" height="500px">';
  echo '</div>';

  echo '<div class="mySlides fade">';
    echo '<div class="numbertext">3 / 3</div>';
        echo '<img src="' . htmlspecialchars($row["imgLink3"]) . '" alt="' . htmlspecialchars($row["TourName"]) . '" class="center-image" width="100%" height="500px">';
  echo '</div>';

  echo '<a class="prev" onclick="plusSlides(-1)">&#10094;</a>';
  echo '<a class="next" onclick="plusSlides(1)">&#10095;</a>';
echo '</div>';
echo '<br>';

// <!-- The dots/circles -->
echo '<div style="text-align:center">';
  echo '<span class="dot" onclick="currentSlide(1)"></span>';
  echo '<span class="dot" onclick="currentSlide(2)"></span>';
  echo '<span class="dot" onclick="currentSlide(3)"></span>';
echo '</div>';
echo '<br>';
        echo '<p id="detail">' . "£" . htmlspecialchars($row["Price"]) . '<br>'.'</p>';
        echo '<p id="detail">' ."Located in ".htmlspecialchars($row["LocationTag"]) .", discover the amazing fun just awaiting you at ".htmlspecialchars($row["TourName"]).'</p>';
        echo '<p id="detail">' . htmlspecialchars($row["Description"]) . '</p>';
        echo '<br>';
        echo '<br>';
        echo '<br>';
//        echo '<p>' . htmlspecialchars($row["Description"]) . '</p>';
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
        echo '<p id="detail">' ."Book now and experience the time of your life!".'</p>';
        echo '<input type="submit" id="submitdetails" value="Add to Cart" name="submit">';
        echo '</form>';
    }
} else {
    // This only happens if no results are here, the scenario that happens is clicking the page when no item was selected
   echo '<video autoplay muted loop id="myVideo">';
    echo '<source src="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/images/EmptyDetails.mp4" type="video/mp4">';
echo '</video>';

//<!-- Optional: some overlay text to describe the video -->
echo '<div class="content2">';
    echo '<h1 class="VideoTitle" >No Tour Selected!</h1>';
    echo '<p>Hi there! We appreciate your enthusiasm, however to see this page in its intended form, you need to pick a tour to see details for first!
          in the meantime however, why not rest here for a while, grab a cup of tea, and get comfy before continuing your search for the perfect trip!</p>';
    echo '</div>';
    echo '<body class="b">';
}
$conn->close();
    ?>
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
<script>
    let slideIndex = 1;
    showSlides(slideIndex);

    // Next/previous controls
    function plusSlides(n) {
        showSlides(slideIndex += n);
    }

    // Thumbnail image controls
    function currentSlide(n) {
        showSlides(slideIndex = n);
    }

    function showSlides(n) {
        let i;
        let slides = document.getElementsByClassName("mySlides");
        let dots = document.getElementsByClassName("dot");
        if (n > slides.length) {slideIndex = 1}
        if (n < 1) {slideIndex = slides.length}
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" active", "");
        }
        slides[slideIndex-1].style.display = "block";
        dots[slideIndex-1].className += " active";
    }
</script>
</body>
</html>