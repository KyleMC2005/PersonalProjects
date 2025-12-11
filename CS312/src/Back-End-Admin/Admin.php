<!--This page will be used for viewing all tours and bookings, -->
<!--Each tour and booking must be possible to edit/update, delete, and select-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>N`adair | Admin</title>
    <?php echo '<link rel="icon" type="image/x-icon" href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/images/travel.png">';?>
    <!--Travel icons created by Freepik - Flaticon-->   <!--Crediting owner-->
    <!--https://www.flaticon.com/free-icons/travel-->
    <link rel="stylesheet" href="https://devweb2025.cis.strath.ac.uk/~mjb23137/CS312%20Assessment/src/Front-End/CSS/core.css">

</head>
<body>
<nav id="HNavBar">
    <ul>
        <li class="left">N`adair Tours</a></li>
    </ul>
</nav>
<?php require("/home/mjb23137/DEVWEB/2025/CS312 Assessment/src/Front-End/HTML-PHP/DatabaseConnection.php");

// Similar logic to my other pages that use forms, these are mostly hidden forms or copy pastes of my functions from other pages with slight modifications

// Deletes bookings and the Order if none left
if (isset($_POST["DelBooking"]) && isset($_POST["BookingID"])) {    // Deletes Bookings
    $BookingID = $_POST['BookingID'];
    $OrderID = $_POST['OrderID'];

    $sqlDeleteBookings = $conn->prepare("DELETE FROM CS312Bookings WHERE BookingID = ?"); // Delete Booking query
    $sqlDeleteBookings->bind_param("i", $BookingID);
    $sqlDeleteBExecution = $sqlDeleteBookings->execute(); // storing result to prevent executing again

    if ($sqlDeleteBExecution) {
    echo "Booking deleted\n";
    header("Refresh:0");
    }

    // checking how many bookings are left now in an order, if there's none it deletes the order too
    $sqlCheckBooking = $conn->prepare("SELECT COUNT(*) as tours from CS312Bookings where OrderID = ?");
    $sqlCheckBooking->bind_param("i", $OrderID);
    $sqlCheckBooking->execute();
    $TourCount = $sqlCheckBooking->get_result();  // Stores result in this
    $row = $TourCount->fetch_assoc();

    if ($row["tours"] == 0) {// if there's no tours left then delete the order, basically a copy paste of later method
        $sqlDeleteOrders = $conn->prepare("DELETE FROM CS312Orders WHERE OrderID = ?"); // Delete Order query
        $sqlDeleteOrders->bind_param("i", $OrderID);
        $sqlDeleteOExecution = $sqlDeleteOrders->execute();

        if ($sqlDeleteOExecution) { // If we did delete an order, let the user know why
            echo "Order deleted as all bookings have been removed!\n";
            header("Refresh:0");
        } else {
            echo "Error deleting order\n";
        }
    } else {
        echo "Error deleting booking\n";
    }
}

// Deletes Orders
if (isset($_POST["DelOrder"]) && isset($_POST["OrderID"])) {    // Deletes Orders
    $OrderID = $_POST['OrderID'];

    $sqlDeleteBookings = $conn->prepare("DELETE FROM CS312Bookings WHERE OrderID = ?"); // Delete Booking query basically to make sure all booking disappear with the order
    $sqlDeleteBookings->bind_param("i", $OrderID);
    $sqlDeleteBExecution = $sqlDeleteBookings->execute();

    $sqlDeleteOrders = $conn->prepare("DELETE FROM CS312Orders WHERE OrderID = ?"); // Delete Order query
    $sqlDeleteOrders->bind_param("i", $OrderID);
    $sqlDeleteOExecution = $sqlDeleteOrders->execute();

    if ($sqlDeleteBExecution && $sqlDeleteOExecution) {
        echo "Order and Bookings Deleted\n";
        header("Refresh:0");
    } else if ($sqlDeleteOExecution) {
        echo "Order Deleted\n";
    } else if ($sqlDeleteBookings){
        echo "Booking deleted but error with Order Deletion\n";
        header("Refresh:0");
    } else {
        echo "Error deleting\n";
    }
}

// Inserts a Tour
if (isset($_POST["InsertTour"]))  {
    $TourName = $_POST['TourName'];
    $Location = $_POST['Location'];
    $Duration = $_POST['Duration'];
    $DepartureDate = $_POST['DepartureDate'];
    $Price = $_POST['Price'];
    $Description = $_POST['Description'];

    $sqlInsertTour = $conn->prepare("insert into CS312Tours (TourName, Location, Duration, DepartureDate, Price, Description)
    VALUES (?,?,?,?,?,?)");
    $sqlInsertTour->bind_param("ssdsds", $TourName, $Location, $Duration, $DepartureDate, $Price, $Description);
    $sqlInsertExecution = $sqlInsertTour->execute();

       // Execute the query
    if ($sqlInsertExecution) {
        echo "Tour inserted successfully!\n";
        header("Refresh:0");
        exit;
    } else {
        echo "Error inserting tour: " . $sqlInsertTour->error. "\n";  // Print error message
    }
}

// Deletes a Tour
if (isset($_POST["DeleteTour"]))  {
    $TourID = $_POST['TourID'];

    $sqlDeleteTour = $conn->prepare("DELETE FROM CS312Tours WHERE TourID = ?"); // Delete Tour query
    $sqlDeleteTour->bind_param("i", $TourID);
    $sqlDeleteTExecution = $sqlDeleteTour->execute();

    // Execute the query
    if ($sqlDeleteTExecution) {
        echo "Tour Deleted successfully!\n";
        header("Refresh:0");
        exit;
    } else {
        echo "Error Deleting tour: " . $sqlDeleteTour->error."\n";
    }
}

// Edits a Tour
if (isset($_POST["EditTour"]))  {
    $TourID = $_POST['TourID'];
        $TourName = $_POST['TourName'];
        $Location = $_POST['Location'];
        $Duration = $_POST['Duration'];
        $DepartureDate = $_POST['DepartureDate'];
        $Price = $_POST['Price'];
        $Description = $_POST['Description'];
        $StarRating = $_POST['StarRating'];
        $StarRatingBugFix = $StarRating*2;

        $sqlUpdateTour = $conn->prepare("UPDATE CS312Tours SET TourName = ?, Location = ?, Duration = ?, DepartureDate = ?, Price = ?, Description = ?, StarRating = ? WHERE TourID = ?");
        $sqlUpdateTour->bind_param("ssisdsid", $TourName, $Location, $Duration, $DepartureDate, $Price, $Description, $StarRatingBugFix, $TourID);
        $sqlUpdateTExecution = $sqlUpdateTour->execute();

    // Execute the query
    if ($sqlUpdateTExecution) {
        echo "Tour Updated successfully!\n";
        header("Refresh:0");
        exit;
    } else {
        echo "Error Updated tour: " . $sqlUpdateTour->error."\n";
    }

}

$sqlBookings = "SELECT * FROM `CS312Bookings` ORDER BY `BookingID` ASC"; //Select query to view
$resultBookings = $conn->query($sqlBookings);

$sqlOrders = "SELECT * FROM `CS312Orders` ORDER BY `OrderID` ASC"; //Select query to view
$resultOrders = $conn->query($sqlOrders);

echo '<h1 id="AdminOrders">Admin Orders</h1><br>';
echo '<br>';
echo '<h2 class="container-heading">Orders</h2>';
echo '<section class = "container">';    // Opens Container

if ($resultOrders->num_rows > 0) {      // Orders Result (Based on OrderID on SQL Result)
    // output data of each row
    while ($row = $resultOrders->fetch_assoc()) {
        echo '<div class = "container-entry">';
        echo '<p>' . "Order ID: " . htmlspecialchars($row["OrderID"]) . '</p>';
        echo '<p>' . "Booked Name: " . htmlspecialchars($row["FullName"]) . '</p>';
        echo '<p>' . "Email: " . htmlspecialchars($row["CustomerEmail"]) . '</p>';
        echo '<p>' . "Order Price: £" . htmlspecialchars($row["FinalPrice"]) . '</p>';
        echo '<p>' . "Order Date: " . htmlspecialchars($row["OrderDate"]) . '</p>';
//        Drop a short, dynamic description in here
        echo '<br>';
//        echo '<button type="submit" class="EditOrder">Edit Order</button>';
        echo '<form id="DeleteBook" autocomplete="on" method="post" onsubmit="return alertconfirm()" target="_self">';
        echo '<input type="hidden" name="OrderID" value="' . $row["OrderID"] . '">';
        echo '<button type="submit" id="DelOrder" name="DelOrder">Delete Order</button>';
        echo '</form>';

        echo '</div>';
    }
}

echo '</section>';
echo '<br>';
echo '<br>';
echo '<br>';
echo '<br>';
echo '<br>';
echo '<h2 class="container-heading">Bookings</h2>';
echo '<br>';
echo '<br>';
echo '<br>';
echo '<br>';
echo '<br>';
echo '<section class = "container">';      // Opens Container
if ($resultBookings->num_rows > 0) {      // Orders Result (Based on OrderID on SQL Result)
    // output data of each row
    while ($row = $resultBookings->fetch_assoc()) {
        echo '<div class = "container-entry">';
        echo '<p>' . "Order ID: " . htmlspecialchars($row["OrderID"]) . '</p>';
        echo '<p>' . "Booking ID: " . htmlspecialchars($row["BookingID"]) . '</p>';
        echo '<p>' . "Tour Name: " . htmlspecialchars($row["TourName"]) . '</p>';
        echo '<p>' . "Price: £" . htmlspecialchars($row["Price"]) . '</p>';
        echo '<p>' . "Booked Name: " . htmlspecialchars($row["FullName"]) . '</p>';
        echo '<p>' . "Email: " . htmlspecialchars($row["email"]) . '</p>';
        echo '<p>' . "Number of People: " . htmlspecialchars($row["NumPeople"]) . '</p>';
        echo '<p>' . "Departure Date: " . htmlspecialchars($row["DepartureDate"]) . '</p>';
//        Drop a short, dynamic description in here
        echo '<br>';
//        echo '<button type="submit" class="EditBooking">Edit Booking</button>';
        echo '<form id="DeleteBook" autocomplete="on" onsubmit="return alertconfirm()" method="post" target="_self">';
        echo '<input type="hidden" name="OrderID" value="' . $row["OrderID"] . '">';
        echo '<input type="hidden" name="BookingID" value="' . $row["BookingID"] . '">';
        echo '<button type="submit" name="DelBooking">Delete Booking</button>';
        echo '</form>';
        echo '</div>';
    }
}
echo '</section>';

// Now for the section for New Tours
echo '<br>';
echo '<br>';
echo '<br>';
echo '<br>';
echo '<br>';
echo '<h2 class="container-heading">Tour Management</h2>';
echo '<br>';
echo '<section class = "container">';      // Opens Container
echo '<form id="InsertBook" autocomplete="on" onsubmit="return confirmmake()" method="post" target="_self">';
echo '<label for="TourName">Tour Name:</label>';
echo '<input type="text" id="TourNameForm1" name="TourName"><br><br>';
echo '<label for="Location">Location:</label>';
echo '<input type="text" id="LocationForm1" name="Location"><br><br>';
echo '<label for="Duration">Duration:</label>';
echo '<input type="number" id="DurationForm1" name="Duration"><br><br>';
echo '<label for="DepartureDate">Departure Date:</label>';
echo '<input type="date" id="DepartureDateForm1" name="DepartureDate"><br><br>';
echo '<label for="Price">Price:</label>';
echo '<input type="number" id="PriceForm1" name="Price"><br><br>';
echo '<label for="Description">Description:</label>';
echo '<input type="text" id="DescriptionForm1" name="Description"><br><br>';
  echo '<input type="submit" id="TourForm1" name="InsertTour" value="Create Booking">';
echo '</form>';
echo '</section>';

// Now for the section for Editing Tours

// Display table for all tours first
$sqlAllTours = "SELECT * FROM `CS312Tours` ORDER BY TourID ASC; ";
$resultAT = $conn->query($sqlAllTours);

if ($resultAT->num_rows > 0) {      // Show all Tours
// Output data of each row
    echo "<table>";
    echo "<tr>";
    echo "<th>Tour ID</th>";
    echo "<th>Tour Name</th>";
    echo "<th>Location</th>";
    echo "<th>Duration</th>";
    echo "<th>Departure Date</th>";
    echo "<th>Price</th>";
//    echo "<th>Description</th>"; // Was too big, covered whole page
    echo "<th>Image Link 1</th>";
    echo "<th>Image Link 2</th>";
    echo "<th>Image Link 3</th>";
    echo "<th>Tags</th>";
    echo "<th>LocationTag</th>";
    echo "<th>Stars</th>";
    echo "</tr>";

    while ($row = $resultAT->fetch_assoc()) {
        echo "<tr>";  // Start a new row for each tour
        echo "<td>" . htmlspecialchars($row['TourID']) . "</td>";
        echo "<td>" . htmlspecialchars($row['TourName']) . "</td>";
        echo "<td>" . htmlspecialchars($row['Location']) . "</td>";
        echo "<td>" . htmlspecialchars($row['Duration']) . "</td>";
        echo "<td>" . htmlspecialchars($row['DepartureDate']) . "</td>";
        echo "<td>" . htmlspecialchars($row['Price']) . "</td>";
//        echo "<td>" . htmlspecialchars($row['Description']) . "</td>";
        echo "<td>" . htmlspecialchars($row['imgLink']) . "</td>";
        echo "<td>" . htmlspecialchars($row['imgLink2']) . "</td>";
        echo "<td>" . htmlspecialchars($row['imgLink3']) . "</td>";
        echo "<td>" . htmlspecialchars($row['Tags']) . "</td>";
        echo "<td>" . htmlspecialchars($row['LocationTag']) . "</td>";
        echo "<td>" . htmlspecialchars($row['StarRating']) . "</td>";
        echo "</tr>";
    }
        echo "</table>";
}

echo '<br>';
echo '<br>';
echo '<br>';
echo '<br>';
echo '<br>';
echo '<h2 class="container-heading">Tour Editing</h2>';
echo '<br>';
echo '<section class = "container">';      // Opens Container
echo '<form id="InsertBook" name="InsertBook" autocomplete="on" onsubmit="return confirmupdate()" method="post" target="_self">';
echo '<label for="TourID">Tour ID:</label>';
echo '<input type="number" id="TourIDForm2" name="TourID"><br><br>';
echo '<label for="TourName">Tour Name:</label>';
echo '<input type="text" id="TourNameForm2" name="TourName"><br><br>';
echo '<label for="Location">Location:</label>';
echo '<input type="text" id="LocationForm2" name="Location"><br><br>';
echo '<label for="Duration">Duration:</label>';
echo '<input type="number" id="DurationForm2" name="Duration"><br><br>';
echo '<label for="DepartureDate">Departure Date:</label>';
echo '<input type="date" id="DepartureDateForm2" name="DepartureDate"><br><br>';
echo '<label for="Price">Price:</label>';
echo '<input type="number" id="PriceForm2" name="Price"><br><br>';
echo '<label for="Description">Description:</label>';
echo '<input type="text" id="DescriptionForm2" name="Description"><br><br>';
echo '<label for="StarRating">Star Rating:</label>';
echo '<input type="number" id="StarRatingForm2" name="StarRating"><br><br>';
echo '<input type="submit" id="TourForm2" name="EditTour" value="Edit Tour">';
echo '</form>';
echo '</section>';

// Now for the section for Deleting Tours
echo '<br>';
echo '<br>';
echo '<br>';
echo '<br>';
echo '<br>';
echo '<h2 class="container-heading">Tour Deletions</h2>';
echo '<br>';
echo '<section class = "container">';      // Opens Container
echo '<form id="InsertBook" autocomplete="on" onsubmit="return confirmdelete()" method="post" target="_self">';
echo '<label for="TourID">Tour ID:</label>';
echo '<input type="number" id="TourIDForm3" name="TourID"><br>';
echo '<input type="submit" id="TourForm3" name="DeleteTour" value="Delete Tour">';
echo '</form>';
echo '</section>';
$conn->close();
?>
<script>
    function confirmmake() {
        if (confirm("You are about to create a new tour. Are you sure your details are correct?") === false) {
            return false} else return true;
    }
    function confirmupdate() {
        if (confirm("Please ensure the tour exists before update, otherwise nothing will be updated upon submission. Are you sure your details are correct?") === false) {
            return false} else return true;
    }
    function confirmdelete() {

        if (confirm("Please ensure the tour exists before deletion, otherwise nothing will be deleted upon submission. This action cannot be reversed, are you sure you want to do this?") === false) {
            return false} else return true;
    }
</script>
</body>
</html>