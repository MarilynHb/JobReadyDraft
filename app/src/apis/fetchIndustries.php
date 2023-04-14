<?php
include "connection.php";

$sql = "SELECT Description FROM Industry";
$result = mysqli_query($conn, $sql);

// Build JSON array of industries
$industries = array();
if (mysqli_num_rows($result) > 0) {
    while($row = mysqli_fetch_assoc($result)) {
        $industries[] = $row["Description"];
    }
}

// Return JSON array of industries
header('Content-Type: application/json');
echo json_encode($industries);

// Close database connection
mysqli_close($conn);

?>
