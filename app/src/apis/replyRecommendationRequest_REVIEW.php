<?php
include connection.php;
// Get the user's credentials
$recommendation_id = $_POST['recommendation_id'];
$status = $_POST['status'];
$content = $_POST['content'];

// Update the recommendation status in the database
$sql = "UPDATE Recommendation SET status='$status' WHERE id='$recommendation_id'";
$sqlcontent = "UPDATE Recommendation SET content='$content' WHERE id='$recommendation_id'";

if ($conn->query($sql) === TRUE) {
    $response = array('success' => 1, 'message' => 'Recommendation status updated successfully');
    echo json_encode($response);
} else {
    $response = array('success' => 0, 'message' => 'Error updating recommendation status: ' . $conn->error);
    echo json_encode($response);
}

// Close the connection
$conn->close();
?>
