<?php
include connection.php;

// Get the user's credentials
$user_id = $_POST['user_id'];
$content = $_POST['content'];

// Prepare and execute the query to insert a new post
$stmt = $conn->prepare("INSERT INTO Post (UserId, Content, PostedOn) VALUES (?, ?, NOW())");
$stmt->bind_param("is", $user_id, $content);
if ($stmt->execute()) {
    // Post was created successfully, return success message as a JSON object
    $response = array('success' => 1, 'message' => 'Post created successfully');
    echo json_encode($response);
} else {
    // Error occurred while creating the post, return error message as a JSON object
    $response = array('success' => 0, 'message' => 'Error creating post');
    echo json_encode($response);
}

// Close the connection
$conn->close();
?>
