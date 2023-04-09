<?php
include "connection.php";

// Get the follower and followee IDs from the request
$user_id = $_POST['user_id'];
$following_id = $_POST['following_id'];
$has_notif_on = $_POST['has_notif_on'];
$followed_on = date('Y-m-d H:i:s');

// Insert the follower-followee relationship into the database
$sql = "INSERT INTO Follower (UserId, FollowingId, HasNotificationOn, FollowedOn) 
	VALUES ('$user_id', '$following_id', '$has_notif_on', '$followed_on')";

if ($conn->query($sql) === TRUE) {
    // Return success message as a JSON object
    $response = array('success' => 1, 'message' => 'User followed successfully');
    echo json_encode($response);
} else {
    // Return error message as a JSON object
    $response = array('success' => 0, 'message' => 'Error: ' . $sql . '<br>' . $conn->error);
    echo json_encode($response);
}

// Close the connection
$conn->close();
?>
