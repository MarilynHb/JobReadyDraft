<?php

include("connection.php");
// Get the value of 'username' key from the array
$username = $_POST['username'];

$sql = "SELECT location, about, headline FROM users WHERE name = '$username';";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // Create an array to hold the data
    $data = array();

    // Add the data to the array
    while ($row = $result->fetch_assoc()) {
        $data = $row;
    }

    // Convert the data array to JSON and send it to the Java file
    echo json_encode($data);
} else {
    echo "No data found for username $username";
}

$conn->close();
?>
