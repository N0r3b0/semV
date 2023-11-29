<?php
require_once 'stripe-php/init.php';

$stripe = new \Stripe\StripeClient('sk_test_51O9QzUHxV6XONyshY93LQx0KUhsghQbIkNY7FkCMwotVbQk2Cq5dsy2enOqdvTfsYzMBIkTY6LBVvByUxrNwdPrp00IuE9ywaJ');

$max_3_customers = $stripe->customers->all(['limit' => 3]);


header('Content-Type: application/json');
echo json_encode($max_3_customers, JSON_PRETTY_PRINT);
?>