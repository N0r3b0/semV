<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formularz - Krok 3</title>
</head>
<body>
    <form action="fp.php?ST=2" method="post">
        <label>Modyfikacja formularza pracownika:</label>
        <input type="submit" name="submit" value="Poprzedni">
    </form>
    
    <form action="fp.php?ST=4" method="post">
        <label>Zatwierdź dane formularza - zapis do bazy, unicestwienie sesji:</label>
        <input type="submit" name="submit" value="Zatwierdź dane">
    </form>


    <p>Informacje o zawartości sesji:</p>
    <?php
    {
        $_SESSION['doswiadczenie'] = $_POST['doswiadczenie'] ?? '';
        $_SESSION['zainteresowania'] = $_POST['zainteresowania'] ?? '';
    }

    print_r($_SESSION);
    echo 'ID Sesji: ' . session_id();
    ?>

</body>
</html>
