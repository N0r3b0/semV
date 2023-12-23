<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\PodstawowyKontroler;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/

/*
Route::get('/', function () {
    return view('welcome');
});
*/
Route::get('/', [PodstawowyKontroler::class,'zwrocStroneDomowa']); 

//  ksiazki
Route::get('/ksiazki', [PodstawowyKontroler::class,'zwrocListeKsiazek']); 
Route::get('/dodaj_ksiazke', [PodstawowyKontroler::class,'zwrocDodajKsiazke']);
Route::post('/dodaj_ksiazke', [PodstawowyKontroler::class,'dodajKsiazke']);
Route::delete('/usun_ksiazke', [PodstawowyKontroler::class,'usunKsiazke']);

//  kategorie
Route::get('/kategorie', [PodstawowyKontroler::class,'zwrocListeKategorii']); 
Route::get('/dodaj_kategorie', [PodstawowyKontroler::class,'zwrocDodajKategorie']);
Route::post('/dodaj_kategorie', [PodstawowyKontroler::class,'dodajKategorie']);
Route::delete('/usun_kategorie', [PodstawowyKontroler::class,'usunKategorie']);

//  wydawnicwtwa
Route::get('/wydawnictwa', [PodstawowyKontroler::class,'zwrocListeWydawnictw']); 
Route::get('/dodaj_wydawnictwo', [PodstawowyKontroler::class,'zwrocDodajWydawnictwo']);
Route::post('/dodaj_wydawnictwo', [PodstawowyKontroler::class,'dodajWydawnictwo']);
Route::delete('/usun_wydawnictwo', [PodstawowyKontroler::class,'usunWydawnictwo']);