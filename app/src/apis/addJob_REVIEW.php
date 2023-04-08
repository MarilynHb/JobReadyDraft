<?php
include connection.php;

// Get the user's credentials
$user_id = $_POST['user_id'];
$title = $_POST['title'];
$description = $_POST['description'];
$level = $_POST['level'];

// Check if the user has level 3
$sql_check_level = "SELECT * FROM User WHERE id = '$user_id' AND level = '3'";
$result_check_level = $conn->query($sql_check_level);

if ($result_check_level->num_rows == 0) {
    // User does not have level 3, return error message as a JSON object
    $response = array('success' => 0, 'message' => 'User does not have level 3');
    echo json_encode($response);
} else {
    // User has level 3, insert job into Job table
    $sql_insert_job = "INSERT INTO Job (user_id, title, description, level) VALUES ('$user_id', '$title', '$description', '$level')";
    $result_insert_job = $conn->query($sql_insert_job);
    
    // Get the ID of the inserted job
    $job_id = $conn->insert_id;
    
    // Insert job skills into JobSkill table
    $skills = $_POST['skills'];
    foreach ($skills as $skill) {
        $skill_id = $skill['id'];
        $sql_insert_job_skill = "INSERT INTO JobSkill (job_id, skill_id) VALUES ('$job_id', '$skill_id')";
        $result_insert_job_skill = $conn->query($sql_insert_job_skill);
    }
    
    // Return success message as a JSON object
    $response = array('success' => 1, 'message' => 'Job created successfully');
    echo json_encode($response);
}

// Close the connection
$conn->close();
?>
