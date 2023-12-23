<?php

use App\Http\Controllers\ProfileController;
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

Route::get('/dashboard', function () {
    return view('dashboard');
})->middleware(['auth', 'verified'])->name('dashboard');

Route::middleware('auth')->group(function () {
    Route::get('/profile', [ProfileController::class, 'edit'])->name('profile.edit');
    Route::patch('/profile', [ProfileController::class, 'update'])->name('profile.update');
    Route::delete('/profile', [ProfileController::class, 'destroy'])->name('profile.destroy');
});
*/



Route::get('/', [PodstawowyKontroler::class,'zwrocStroneDomowa']); 

//  ksiazki
Route::get('/ksiazki', [PodstawowyKontroler::class,'zwrocListeKsiazek']); 
Route::get('/dodaj_ksiazke', [PodstawowyKontroler::class,'zwrocDodajKsiazke'])->middleware('auth');
Route::post('/dodaj_ksiazke', [PodstawowyKontroler::class,'dodajKsiazke']);
Route::delete('/usun_ksiazke', [PodstawowyKontroler::class,'usunKsiazke'])->middleware('auth');

//  kategorie
Route::get('/kategorie', [PodstawowyKontroler::class,'zwrocListeKategorii']); 
Route::get('/dodaj_kategorie', [PodstawowyKontroler::class,'zwrocDodajKategorie'])->middleware('auth');
Route::post('/dodaj_kategorie', [PodstawowyKontroler::class,'dodajKategorie']);
Route::delete('/usun_kategorie', [PodstawowyKontroler::class,'usunKategorie'])->middleware('auth');

//  wydawnicwtwa
Route::get('/wydawnictwa', [PodstawowyKontroler::class,'zwrocListeWydawnictw']); 
Route::get('/dodaj_wydawnictwo', [PodstawowyKontroler::class,'zwrocDodajWydawnictwo'])->middleware('auth');
Route::post('/dodaj_wydawnictwo', [PodstawowyKontroler::class,'dodajWydawnictwo']);
Route::delete('/usun_wydawnictwo', [PodstawowyKontroler::class,'usunWydawnictwo'])->middleware('auth');

//  autentykacja
Route::get('/loguj', [PodstawowyKontroler::class,'zmienStanUwierzytelnienia']); 
Route::get('/wyloguj',[PodstawowyKontroler::class,'zmienStanUwierzytelnienia']);


require __DIR__.'/auth.php';