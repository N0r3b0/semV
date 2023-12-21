<?php
session_start();

require_once 'stripe-php/init.php';

// Ustaw klucz prywatny ze swojego konta Stripe
\Stripe\Stripe::setApiKey('sk_test_51O9QzUHxV6XONyshY93LQx0KUhsghQbIkNY7FkCMwotVbQk2Cq5dsy2enOqdvTfsYzMBIkTY6LBVvByUxrNwdPrp00IuE9ywaJ');

// Załaduj klasę KlienciStripe
require 'KlienciStripe.php';

// Inicjalizuj obiekt klasy KlienciStripe
$klienciStripe = new KlienciStripe();


// Obsługa żądań
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Dodawanie nowego klienta
    if (isset($_POST['dodaj_klienta'])) {
        $name = $_POST['name'];
        $email = $_POST['email'];
        $description = $_POST['description'];

        // Walidacja pól
        if (empty($name) || empty($email) || empty($description)) {
            $error_message = "Wszystkie pola są wymagane.";
        } else {
            $klienciStripe->dodajKlienta($name, $email, $description);
            $success_message = "Klient został dodany pomyślnie.";
        }
    }

    // Aktualizacja klienta
    if (isset($_POST['aktualizuj_klienta'])) {
        $customer_id = $_POST['customer_id'];
        $name = $_POST['name'];
        $email = $_POST['email'];
        $description = $_POST['description'];

        // Walidacja pól
        if (empty($name) || empty($email) || empty($description)) {
            $error_message = "Wszystkie pola są wymagane.";
        } else {
            $klienciStripe->aktualizujKlienta($customer_id, $name, $email, $description);
            $success_message = "Klient został zaktualizowany pomyślnie.";
        }
    }

    // Usunięcie klienta
    if (isset($_POST['usun_klienta'])) {
        $customer_id = $_POST['customer_id'];
        $klienciStripe->usunKlienta($customer_id);
        $success_message = "Klient został usunięty pomyślnie.";
    }
}

// Pobierz listę klientów ze Stripe
$listaKlientow = $klienciStripe->listaKlientow();
?>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Zarządzanie klientami Stripe</title>
</head>
<body>

<div class="menu">
    <ul>
        <li><a href="#lista">Lista klientów</a></li>
        <li><a href="#dodaj">Dodaj klienta</a></li>
        <li><a href="#aktualizuj">Aktualizuj klienta</a></li>
        <li><a href="#usun">Usuń klienta</a></li>
    </ul>
</div>

<div id="lista">
    <h2>Lista klientów</h2>
    <?php if (!empty($listaKlientow)) : ?>
        <ul>
            <?php foreach ($listaKlientow as $klient) : ?>
                <li><?= $klient['name'] ?> - <?= $klient['email'] ?> (<a href="#" onclick="usunKlienta('<?= $klient['id'] ?>')">Usuń</a>)</li>
            <?php endforeach; ?>
        </ul>
    <?php else : ?>
        <p>Brak klientów do wyświetlenia.</p>
    <?php endif; ?>
</div>

<div id="dodaj">
    <h2>Dodaj klienta</h2>
    <?php if (isset($error_message)) : ?>
        <p class="error"><?= $error_message ?></p>
    <?php endif; ?>
    <?php if (isset($success_message)) : ?>
        <p class="success"><?= $success_message ?></p>
    <?php endif; ?>
    <form method="post">
        <label for="name">Imię i nazwisko:</label>
        <input type="text" name="name" required>
        <label for="email">E-mail:</label>
        <input type="email" name="email" required>
        <label for="description">Opis:</label>
        <textarea name="description" required></textarea>
        <input type="submit" name="dodaj_klienta" value="Dodaj klienta">
    </form>
</div>

<div id="aktualizuj">
    <h2>Aktualizuj klienta</h2>
    <form method="post">
        <label for="customer_id">ID Klienta:</label>
        <input type="text" name="customer_id" required>
        <label for="name">Nowe imię i nazwisko:</label>
        <input type="text" name="name" required>
        <label for="email">Nowy e-mail:</label>
        <input type="email" name="email" required>
        <label for="description">Nowy opis:</label>
        <textarea name="description" required></textarea>
        <input type="submit" name="aktualizuj_klienta" value="Aktualizuj klienta">
    </form>
</div>

<div id="usun">
    <h2>Usuń klienta</h2>
    <form method="post">
        <label for="customer_id">ID Klienta:</label>
        <input id="Customer_id_remove" type="text" name="customer_id" required>
        <input type="submit" name="usun_klienta" value="Usuń klienta">
    </form>
</div>

<script>
    function usunKlienta(customer_id) {
        if (confirm("Czy na pewno chcesz usunąć tego klienta?")) {
            document.getElementById('Customer_id_remove').value = customer_id;
            document.querySelector('form[name="usun_klienta"]').submit();
        }
    }
</script>

</body>
</html>
