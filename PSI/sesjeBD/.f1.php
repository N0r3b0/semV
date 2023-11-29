<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formularz - Krok 1</title>
</head>
<body>
    <form action="fp.php?ST=2" method="post">
        <label for="nazwisko">Nazwisko:</label>
        <input type="text" name="nazwisko" id="nazwisko" value="<?= $p->get_field_value('nazwisko') ?>">
        <br>

        <label for="imie">Imię:</label>
        <input type="text" name="imie" id="imie" value="<?= $p->get_field_value('imie') ?>">
        <br>

        <label for="wiek">Wiek:</label>
        <input type="number" name="wiek" id="wiek" value="<?= $p->get_field_value('wiek') ?>">
        <br>

        <input type="submit" value="Wyślij">
    </form>

    <p>Informacje o zawartości sesji:</p>
        
    <?php
        print_r($_SESSION);
        echo 'ID Sesji: ' . session_id();
    ?>
    
</body>
</html>