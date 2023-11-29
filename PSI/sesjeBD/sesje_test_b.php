<?php
session_start();

// Funkcja do generowania i wyświetlania tabeli HTML na podstawie tablicy
function displayTable($array) {
    echo '<table border="1">';
    echo '<tr><th>Index</th><th>Value</th></tr>';
    foreach ($array as $index => $value) {
        echo '<tr><td>' . $index . '</td><td>' . $value . '</td></tr>';
    }
    echo '</table>';
}

// Sprawdź, czy w sesji jest już zmienna "tablice"
if (isset($_SESSION['tablice'])) {
    $tablice = $_SESSION['tablice'];
} else {
    $tablice = array();
}

// Wylosuj 10 liczb całkowitych z przedziału od -100 do 100
$ts = array();
for ($i = 0; $i < 10; $i++) {
    $ts[] = rand(-100, 100);
}

// Dodaj nową tablicę do listy tablic w sesji
$tablice[] = $ts;

// Zapisz zaktualizowaną listę tablic w sesji
$_SESSION['tablice'] = $tablice;
?>

<!DOCTYPE html>
<html>
<head>
    <title>Test Sesji</title>
</head>
<body>
    <h1>Nowa tablica:</h1>
    <?php
    displayTable($ts);
    ?>

    <h1>Tablice z sesji:</h1>
    <?php
    foreach ($tablice as $index => $array) {
        echo '<h3>Tablica ' . ($index + 1) . '</h3>';
        displayTable($array);
    }
    ?>
</body>
</html>
