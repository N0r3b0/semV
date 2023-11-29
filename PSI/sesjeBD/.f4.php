<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Rejestracja - Krok 4</title>
</head>
<body>
    <p>
        Dziękujemy za rejestrację. Wkrótce skontaktujemy się z Tobą w celu omówienia dalszych kroków rekrutacji.
    </p>

    <p>Informacje o zawartości sesji:</p>

    <?php
    if (isset($_POST['submit']) && $_POST['submit'] == 'Zatwierdź dane') {
        // Zapisanie danych do obiektu klasy Pracownik
        $p->set_field_value('imie', $_SESSION['imie'] ?? '');
        $p->set_field_value('nazwisko', $_SESSION['nazwisko'] ?? '');
        $p->set_field_value('wiek', $_SESSION['wiek'] ?? '');
        $p->set_field_value('doswiadczenie', $_SESSION['doswiadczenie'] ?? '');
        $p->set_field_value('zainteresowania', $_SESSION['zainteresowania'] ?? '');
        $p->insert_to_pgsql();
    }
    session_destroy();
    print_r($_SESSION);
    ?>

</body>
</html>
