<?php
require('Pracownik.php'); // Zaimportuj plik z definicją klasy "Pracownik"

// Inicjalizacja obiektu klasy Pracownik i połączenie z bazą danych
$pracownik = new Pracownik();
$pracownik->db_connect();

// Funkcja do usuwania pracownika
if (isset($_POST['delete']) && isset($_POST['employee_id'])) {
    $employee_id = intval($_POST['employee_id']);
    $pracownik->usun_pracownika($employee_id);
}

// Pobierz listę pracowników z bazy danych
$lista_pracownikow = $pracownik->lista_pracownikow();

// Wyświetlenie listy pracowników
?>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista Pracowników</title>
</head>
<body>
    <h1>Lista Pracowników</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Nazwisko</th>
            <th>Imię</th>
            <th>Wiek</th>
            <th>Doświadczenie</th>
            <th>Zainteresowania</th>
            <th>Opcje</th>
        </tr>
        <?php foreach ($lista_pracownikow as $pracownik) : ?>
            <tr>
                <td><?= $pracownik['idp'] ?></td>
                <td><?= $pracownik['nazwisko'] ?></td>
                <td><?= $pracownik['imie'] ?></td>
                <td><?= $pracownik['wiek'] ?></td>
                <td><?= $pracownik['doswiadczenie'] ?></td>
                <td><?= $pracownik['zainteresowania'] ?></td>
                <td>
                    <a href="edycja_pracownika.php?id=<?= $pracownik['idp'] ?>">Edytuj</a>
                    <form method="post" action="">
                        <input type="hidden" name="employee_id" value="<?= $pracownik['idp'] ?>">
                        <input type="submit" name="delete" value="Usuń">
                    </form>
                </td>
            </tr>
        <?php endforeach; ?>
    </table>
</body>
</html>
