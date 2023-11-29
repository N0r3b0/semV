<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formularz pracownika</title>
</head>
<body>
    <?php
        require_once 'pracownik.php';
        $p = new pracownik();


        if (isset($_GET['ST'])) 
        {
            $page = intval($_GET['ST']);
        }
        else 
        {
            $page = 1;
        }
        
        $page = ($page >= 1 && $page <= 4) ? $page : 1;
        $form = ".f{$page}.php";
        include($form);
    ?>
    
</body>
</html>