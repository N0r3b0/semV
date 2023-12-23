<!DOCTYPE html>
<html> 
@include('partials.head')
<body> 
    @include('partials.navi') 
    @include('partials.alert')

    <div id="zawartosc">
        <h2>Dodaj kategorię</h2>
        <form class="form-inline" action="./dodaj_kategorie" method="post"> 
            @csrf 
            <p> 
                <label for="nazwa">Nazwa kategorii</label> 
                <input id="nazwa" name="nazwa" size="20"> 
            </p>
            <p>
                <button type="submit" class="btn btn-primary mb-2">Dodaj</button>
            </p>
        </form>
    </div>
</body>
</html>