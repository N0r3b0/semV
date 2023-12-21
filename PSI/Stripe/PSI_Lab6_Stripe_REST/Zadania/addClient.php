<?php
require_once 'stripe-php/init.php';

$stripe = new \Stripe\StripeClient('sk_test_51O9QzUHxV6XONyshY93LQx0KUhsghQbIkNY7FkCMwotVbQk2Cq5dsy2enOqdvTfsYzMBIkTY6LBVvByUxrNwdPrp00IuE9ywaJ');

$customer = $stripe->customers->create([
 'description' => 'Klient edukacyjny',
 'email' => 'r.lasko@domena.pl',
 'name' => 'Robert Laskowski',
]);
echo $customer;
?>
