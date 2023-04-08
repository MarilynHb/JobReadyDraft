<?php
include connection.php;

// Get the current user's ID and the ID of the user to unfollow
$current_user_id = $_POST['current_user_id'];
$unfollow_user_id = $_POST['unfollow_user_id'];

// Delete the record from the Follower table
$sql = "DELETE FROM Follower WHERE follower_id = $current_user_id AND following_id = $unfollow_user_id";

if ($conn->query($sql) === TRUE) {
    // Record deleted successfully, return success message as a JSON object
    $response = array('success' => 1, 'message' => 'User unfollowed successfully');
    echo json_encode($response);
} else {
    // Error deleting record, return error message as a JSON object
    $response = array('success' => 0, 'message' => 'Error unfollowing user: ' . $conn->error);
    echo json_encode($response);
}

// Close the connection
$conn->close();
?>




