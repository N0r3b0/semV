<?php
session_start();
$_SESSION['moja_zmienna'] = 'Wiktor Fedde';
echo 'jestem ' . $_SESSION['moja_zmienna'];
echo '<br>ID sesji: ' . session_id();
?>
