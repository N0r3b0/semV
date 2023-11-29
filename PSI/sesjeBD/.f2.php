<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formularz - Krok 2</title>
</head>
<body>
    <form action="fp.php?ST=1" method="post">
        <input type="submit" name="submit" value="Poprzedni">
    </form>
    
    <form action="fp.php?ST=3" method="post">
        <label for="doswiadczenie">Doświadczenie:</label>
        <textarea name="doswiadczenie" id="doswiadczenie"><?= $p->get_field_value('doswiadczenie') ?></textarea>
        <br>

        <label for="zainteresowania">Zainteresowania:</label>
        <textarea name="zainteresowania" id="zainteresowania"><?= $p->get_field_value('zainteresowania') ?></textarea>
        <br>

        <input type="submit" name="submit" value="Dalej">
    </form>


    <p>Informacje o zawartości sesji:</p>
    <?php
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $_SESSION['nazwisko'] = $_POST['nazwisko'] ?? '';
        $_SESSION['imie'] = $_POST['imie'] ?? '';
        $_SESSION['wiek'] = $_POST['wiek'] ?? '';
    }

    print_r($_SESSION);
    echo 'ID Sesji: ' . session_id();
    ?>
</body>
</html>
