<?php
include connection.php;

// Get the user's credentials
$sender_id = $_POST['sender_id'];
$receiver_id = $_POST['receiver_id'];
$message = $_POST['message'];

// Insert recommendation into the database
$sql = "INSERT INTO Recommendation (sender_id, receiver_id, message, status) VALUES ('$sender_id', '$receiver_id', '$message', 'Pending')";

if ($conn->query($sql) === TRUE) {
    $response = array('success' => 1, 'message' => 'Recommendation created successfully');
    echo json_encode($response);
} else {
    $response = array('success' => 0, 'message' => 'Error creating recommendation: ' . $conn->error);
    echo json_encode($response);
}

// Close the connection
$conn->close();
?>
