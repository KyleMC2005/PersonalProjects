<?php session_name("cart");session_start();?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Carts and Bookings</title>
    <?php echo '<link rel="icon" type="image/x-icon" href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/images/GlasgowGhouls.jpg">';?>
    <!--Travel icons created by Freepik - Flaticon-->   <!--Crediting owner-->
    <!--https://www.flaticon.com/free-icons/travel-->
    <link href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/CSS/core.css" type="text/css" rel="stylesheet"> <!--This is responsible for this page's design-->
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
    <?php require("DatabaseConnection.php");
    // Make an IF statement for if the page was chosen before a tour was selected, Check if $_GET['ID'] is null

    // Also check if the user has previous bookings, if they do, offer the possibility to cancel that booking
    // Checking if the session is cart and that the session called cart actually has stuff in it
    if (isset($_POST['ClearCart'])) {
        unset($_SESSION['cart']); // wipes out cart
    }

    // checking cart exists and isn't empty, if these are met do everything here, else it displays different version of page
    if (isset($_SESSION['cart']) && !empty($_SESSION['cart'])) {
    echo '<body class="c">';
    echo "<h2 id='carttitle'>Your Cart</h2>";
    echo "<table>";
    echo "<tr>";
    echo "<th>Tour Name</th>";
    echo "<th>Location</th>";
    echo "<th>Departure Date</th>";
    echo "<th>Price</th>";
    echo "<th>Tags</th>";
    echo "<th>Stars</th>";
    echo "</tr>";

    //Declaring this so I can use it to display a total price
    $total = 0;
    // Displays all the tours
    foreach ($_SESSION['cart'] as $tour) {
        echo "<tr>";  // Start a new row for each tour
        echo "<td>" . htmlspecialchars($tour['TourName']) . "</td>";
        echo "<td>" . htmlspecialchars($tour['Location']) . "</td>";
        echo "<td>" . htmlspecialchars($tour['DepartureDate']) . "</td>";
        echo "<td>" . htmlspecialchars($tour['Price']) . "</td>";
        echo "<td>" . htmlspecialchars($tour['Tags']) . "</td>";
        echo "<td>" . htmlspecialchars($tour['StarRating']) . "</td>";
        $total+=$tour['Price']; // running total
        echo "</tr>";  // End the row for each tour
    }
    echo "</table>";  // Close the table
//     Checking if the form was already submitted
        if (isset($_POST["submit"])) {     // Recycling my old code
            $FullName = $_POST['fname'];
            $Email = $_POST['email'];
            $NumberofPeople = $_POST['NumPeople'];
            $FinalPrice = 0;
            $OrderDate = date("Y-m-d");
            foreach ($_SESSION['cart'] as $tour) {
                $FinalPrice += $tour['Price'] * $NumberofPeople;
            }

            // inserting order into order table
            $PrprOrder = $conn->prepare("INSERT INTO CS312Orders (FullName, CustomerEmail, FinalPrice, OrderDate)
            VALUES (?,?,?,?)");
            $PrprOrder->bind_param("ssds", $FullName, $Email, $FinalPrice, $OrderDate);
            $PrprOrder->execute();
            $OrderID = $conn->insert_id;    // Grabs OrderID

            // now doing the same idea but for bookings for each array in session (which are bookings)
            foreach ($_SESSION['cart'] as $tour) {
                $TourID = $tour['TourID'];
                $TourName = $tour['TourName'];
                $TPrice = $tour['Price'];
                $DepartureDt = $tour['DepartureDate'];
                // insertion sql preparation and insertion
                $bookinginst = $conn->prepare("INSERT INTO CS312Bookings ( OrderID,TourID, TourName, FullName,
                email, NumPeople, Price, DepartureDate) VALUES (?,?,?,?,?,?,?,?)");
                $bookinginst->bind_param("iisssids", $OrderID, $TourID, $TourName, $FullName, $Email, $NumberofPeople, $TPrice, $DepartureDt);
                $bookinginst->execute();
            }
            // Clearing the cart once it's submitted
            unset($_SESSION['cart']);

            // Source - https://stackoverflow.com/a
        // Posted by user1847051, modified by community. See post 'Timeline' for change history
        // Retrieved 2025-11-26, License - CC BY-SA 3.0
            header("Refresh:0");
        }
        else {// We now have the tour details for the form, only showing if no order id
                echo '<h1 id="BookingFormTitle">Booking Form</h1>';
                echo '<form id="BookingForm" name="BookingForm" onsubmit="return validateForm()" action="" method="post">';
                echo '<label id="fname" for="fname">Full Name:</label>';
                echo '<input type="text" id="fnameBooking" name="fname" required><br><br>';
                echo '<label id="email" for="email">Email:</label>';
                echo '<input type="email" id="emailBooking" name="email" required><br><br>';
                echo '<label id="NumPeople" for="NumPeople">Number of People:</label>';
//                Learned what oninput was through this https://www.geeksforgeeks.org/html/html-oninput-event-attribute/
                echo '<input type="number" id="NumPeopleBooking" name="NumPeople" oninput="liveupdate()" required><br><br>';
                echo '<p id="livetotalresult">Total Price: £<span id="result"></span></p>';
                echo '<input type="submit" id="BookingSubmit" value="Submit Booking" name="submit">';
                echo '</form>';
        }
        echo '<div style="text-align:center;">';
        echo '<form method="post" onsubmit="return confirmation()">';
        echo '<button type="submit" id="ClearCart" name="ClearCart">Clear Cart</button>';
        echo '</form>';
        echo '</div>';
        } else  {echo '<h1 id="IntroHead">Cart</h1>';
    // This only happens if no results are here, the scenario that happens is clicking the page when no item was selected
   echo '<video autoplay muted loop id="myVideo">';
    echo '<source src="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/images/EmptyDetails.mp4" type="video/mp4">';
echo '</video>';

echo '<div class="content2">';
    echo '<h1 class="VideoTitle" >No Tours Selected!</h1>';
    echo '<p>Hi there! We appreciate your enthusiasm, however to see this page in its intended form, you need to pick a tour to see it stored in your cart first!
          in the meantime however, why not rest here for a while, grab a cup of tea, and get comfy before continuing your search for the perfect trip!</p>';
    echo '</div>';
    echo '<body class="b">';
    }
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
<!--This is where I learned how to take a php value and manipulate it https://www.geeksforgeeks.org/php/how-to-pass-variables-and-data-from-php-to-javascript/-->
<script type="text/javascript">
    function liveupdate() {
    var currenttotal = "<?php echo "$total"?>";
    let currentnumber = document.getElementById('NumPeopleBooking').value;
    let livetotal = currentnumber*currenttotal;
    livetotal = (Math.round(livetotal * 100) / 100).toFixed(2);
    document.getElementById('result').textContent = livetotal;
    }

    function validateForm() {
        let Numpeople = document.forms["BookingForm"]["NumPeople"].value;
        if (Numpeople >= 13) {
            alert("Please choose a group size smaller than 13");
            return false;
        }
        if (confirm("You are about to submit the form, completing your booking, are you sure your details are correct?") === false) {
            return false}
    }

    function confirmation() {
        if (confirm("You are about to delete all the items in your cart! Are you sure this is what you want to do?") === false) {
        return false}
    }
</script>
</body>
</html>