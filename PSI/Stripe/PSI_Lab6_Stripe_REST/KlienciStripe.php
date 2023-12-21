<?php
require_once 'stripe-php/init.php';

class KlienciStripe
{
    public function listaKlientow()
    {
        $customers = \Stripe\Customer::all();
        $listaKlientow = [];

        foreach ($customers->data as $customer) {
            $listaKlientow[] = [
                'id' => $customer->id,
                'name' => $customer->name,
                'email' => $customer->email,
                'description' => $customer->description,
            ];
        }

        return $listaKlientow;
    }

    public function dodajKlienta($name, $email, $description)
    {
        $customer = \Stripe\Customer::create([
            'name' => $name,
            'email' => $email,
            'description' => $description,
        ]);

        return $customer->id;
    }

    public function aktualizujKlienta($customer_id, $name, $email, $description)
    {
        $customer = \Stripe\Customer::update(
            $customer_id,
            [
                'name' => $name,
                'email' => $email,
                'description' => $description,
            ]
        );

        return $customer->id;
    }

    public function usunKlienta($customer_id)
    {
        \Stripe\Customer::retrieve($customer_id)->delete();
    }
}
