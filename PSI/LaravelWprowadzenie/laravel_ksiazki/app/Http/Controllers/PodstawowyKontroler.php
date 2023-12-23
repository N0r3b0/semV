<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class PodstawowyKontroler extends Controller
{
    public function zwrocStroneDomowa()
    {
        return view('domowa');
    }
    //  ksiązki
    public function zwrocListeKsiazek()
    {
        $ksiazkiZBazy = DB::table('ksiazka')
        ->leftJoin('kategoria', 'ksiazka.idkat', '=', 'kategoria.id') 
        ->leftJoin('wydawnictwo', 'ksiazka.idwyd', '=', 'wydawnictwo.id') 
        ->select('ksiazka.id as id_ksiazki', 'ksiazka.tytul','kategoria.opis', 'wydawnictwo.nazwa')
        -> get();
        return view('lista_ksiazek', ['ksiazki' => $ksiazkiZBazy,]);
    }
    public function zwrocDodajKsiazke()
    {
        $kategorieZBazy = DB::table('kategoria')->get();
        $wydawnictwaZBazy = DB::table('wydawnictwo')->get();
        return view('dodaj_ksiazke', ['kategorie' => $kategorieZBazy, 'wydawnictwa' => $wydawnictwaZBazy]);
    }
    public function dodajKsiazke(Request $request)
    {
        $request->validate([
            'tytul' => 'required|min:3|max:50',
            'idkat' => 'required|exists:kategoria,id',
            'idwyd' => 'required|exists:wydawnictwo,id',
        ], [
            'tytul.required' => 'Pole "Tytuł" jest wymagane.',
            'tytul.min' => 'Pole "Tytuł" musi zawierać co najmniej :min znaki.',
            'tytul.max' => 'Pole "Tytuł" nie może zawierać więcej niż :max znaków.',
            'idkat.required' => 'Pole "Kategoria" jest wymagane.',
            'idkat.exists' => 'Wybrana "Kategoria" jest nieprawidłowa.',
            'idwyd.required' => 'Pole "Wydawnictwo" jest wymagane.',
            'idwyd.exists' => 'Wybrane "Wydawnictwo" jest nieprawidłowe.',
        ]);

        $tytulZFormularza = $request->tytul;
        $idKategoriiZFormularza = $request->idkat;
        $idWydawnictwaZFormularza = $request->idwyd;

        // Dodanie książki do bazy danych
        DB::table('ksiazka')->insert([
            'tytul' => $tytulZFormularza,
            'idkat' => intval($idKategoriiZFormularza),
            'idwyd' => intval($idWydawnictwaZFormularza),
        ]);

        // Powiadomienie sukcesu
        return redirect('/ksiazki')->with('success', 'Książka została dodana pomyślnie!');
    }
    public function usunKsiazke(Request $request)
    {
        $idKsiazkiZFormularza = $request->idKsiazki;
        DB::table('ksiazka')->delete([
            'id' => $idKsiazkiZFormularza, 
        ]);
        return redirect('/ksiazki');
    }

    //  kategorie
    public function zwrocListeKategorii()
    {
        $kategorieZBazy = DB::table('kategoria') -> get();
        return view('lista_kategorii', ['kategorie' => $kategorieZBazy,]);
    }
    public function zwrocDodajKategorie()
    {
        return view('dodaj_kategorie');
    }
    public function dodajKategorie(Request $request)
    {
        $request->validate([
            'nazwa' => 'required|min:3|max:50',
        ], [
            'nazwa.required' => 'Pole "Nazwa" jest wymagane.',
            'nazwa.min' => 'Pole "Nazwa" musi zawierać co najmniej :min znaki.',
            'nazwa.max' => 'Pole "Nazwa" nie może zawierać więcej niż :max znaków.',
        ]);

        $nazwaZFormularza = $request->nazwa;
        DB::table('kategoria')->insert([
            'opis' => $nazwaZFormularza, 
        ]);
        return redirect('/kategorie')->with('success', 'Kategoria została dodana pomyślnie!');
    }
    public function usunKategorie(Request $request)
    {
        $idKategoriiZFormularza = $request->idKategorii;
        DB::table('kategoria')->delete([
            'id' => $idKategoriiZFormularza, 
        ]);
        return redirect('/kategorie');
    }

    //  wydawnictwa
    public function zwrocListeWydawnictw()
    {
        $wydawnictwaZBazy = DB::table('wydawnictwo') -> get();
        return view('lista_wydawnictw', ['wydawnictwa' => $wydawnictwaZBazy,]);
    }
    public function zwrocDodajWydawnictwo()
    {
        return view('dodaj_wydawnictwo');
    }
    public function dodajWydawnictwo(Request $request)
    {
        $request->validate([
            'nazwa' => 'required|min:3|max:50',
            'adres' => 'required|min:3|max:50',
        ], [
            'nazwa.required' => 'Pole "Nazwa" jest wymagane.',
            'nazwa.min' => 'Pole "Nazwa" musi zawierać co najmniej :min znaki.',
            'nazwa.max' => 'Pole "Nazwa" nie może zawierać więcej niż :max znaków.',
            'adres.required' => 'Pole "Adres" jest wymagane.',
            'adres.min' => 'Pole "Adres" musi zawierać co najmniej :min znaki.',
            'adres.max' => 'Pole "Adres" nie może zawierać więcej niż :max znaków.',
        ]);
        $nazwaZFormularza = $request->nazwa;
        $adresZFormularza = $request->adres;
        DB::table('wydawnictwo')->insert([
            'nazwa' => $nazwaZFormularza,
            'adres' => $adresZFormularza,
        ]);
        return redirect('/wydawnictwa')->with('success', 'Wydawnictwo zostało dodane pomyślnie!');
    }
    public function usunWydawnictwo(Request $request)
    {
        $idWydawnictwaZFormularza = $request->idWydawnictwa;
        DB::table('wydawnictwo')->delete([
            'id' => $idWydawnictwaZFormularza, 
        ]);
        return redirect('/wydawnictwa');
    }
}