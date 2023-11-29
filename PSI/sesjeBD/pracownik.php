<?php
session_start();
include_once 'db_pgsql.php';

class pracownik {
    private $nazwisko;
    private $imie;
    private $wiek;
    private $doswiadczenie;
    private $zainteresowania;
    private $db;

    public function __construct() {
        $this->db = new db_pgsql();

        // Weryfikacja wartości zmiennych sesyjnych i ustawienie domyślnych wartości
        $this->imie = isset($_SESSION['imie']) ? $_SESSION['imie'] : 'Podaj imię';
        $this->nazwisko = isset($_SESSION['nazwisko']) ? $_SESSION['nazwisko'] : 'Podaj nazwisko';
        $this->wiek = isset($_SESSION['wiek']) ? $_SESSION['wiek'] : 21;
        $this->doswiadczenie = isset($_SESSION['doswiadczenie']) ? $_SESSION['doswiadczenie'] : 'Podaj doświadczenie';
        $this->zainteresowania = isset($_SESSION['zainteresowania']) ? $_SESSION['zainteresowania'] : 'Podaj zainteresowania';
    }

    public function set_field_value($field_name, $value) {
        // Metoda do ustawienia wartości wybranej prywatnej składowej
        if (property_exists($this, $field_name)) {
            $this->$field_name = $value;
        }
    }

    public function get_field_value($field_name) {
        // Metoda do odczytu wartości wybranej prywatnej składowej
        if (property_exists($this, $field_name)) {
            return $this->$field_name;
        }
        return "null";
    }

    public function insert_to_pgsql() {
        // Metoda do zapisu danych pracownika do PostgreSQL wykorzystując klasy db_pgsql
        // Przykład użycia db_pgsql do zapisu danych do bazy
        $this->db->connect();
        $nazwisko = pg_escape_string($this->nazwisko);
        $imie = pg_escape_string($this->imie);
        $wiek = intval($this->wiek);
        $doswiadczenie = pg_escape_string($this->doswiadczenie);
        $zainteresowania = pg_escape_string($this->zainteresowania);
        $sql = "INSERT INTO rekrutacja.pracownicy (nazwisko, imie, wiek, doswiadczenie, zainteresowania) VALUES ('$nazwisko', '$imie', $wiek, '$doswiadczenie', '$zainteresowania')";
        $result = $this->db->query($sql);
        $this->db->disconnect();
        return $result;
    }
}
?>