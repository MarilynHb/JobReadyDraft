<?php
include connection.php;

// Get the user's credentials
$user_id = $_POST['user_id']; // ID of the user making the comment
$post_id = $_POST['post_id']; // ID of the post being commented on
$comment = $_POST['comment']; // The comment text

// Insert the comment into the database
$sql = "INSERT INTO Comment (user_id, post_id, comment) VALUES ('$user_id', '$post_id', '$comment')";
if ($conn->query($sql) === TRUE) {
    // Comment added successfully, return success message as a JSON object
    $response = array('success' => 1, 'message' => 'Comment added successfully');
    echo json_encode($response);
} else {
    // Error adding comment, return error message as a JSON object
    $response = array('success' => 0, 'message' => 'Error adding comment: ' . $conn->error);
    echo json_encode($response);
}

// Close the connection
$conn->close();
?>
