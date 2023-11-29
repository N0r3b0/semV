<?php
// Połączenie z bazą danych PostgreSQL
$host = "localhost";  // Adres hosta
$port = "5432";       // Port bazy danych
$dbname = "postgres"; // Nazwa bazy danych
$user = "postgres";  // Nazwa użytkownika
$password = "postgres";   // Hasło użytkownika

try {
    $pdo = new PDO("pgsql:host=$host;port=$port;dbname=$dbname;user=$user;password=$password");
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    
    // Zapytanie SQL do pobrania zawartości tabeli ksiazka
    $query = "SELECT * FROM ksiazki.ksiazka";
    
    // Wykonanie zapytania
    $stmt = $pdo->query($query);
    
    // Wyświetlenie wyników
    echo "<table border='1'>";
    echo "<tr><th>ID</th><th>Tytuł</th><th>ID Wydawnictwa</th><th>ID Kategorii</th></tr>";
    
    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
        echo "<tr>";
        echo "<td>{$row['idk']}</td>";
        echo "<td>{$row['tytul']}</td>";
        echo "<td>{$row['idwyd']}</td>";
        echo "<td>{$row['idkat']}</td>";
        echo "</tr>";
    }
    
    echo "</table>";
} catch (PDOException $e) {
    die("Błąd połączenia z bazą danych: " . $e->getMessage());
}
?>
