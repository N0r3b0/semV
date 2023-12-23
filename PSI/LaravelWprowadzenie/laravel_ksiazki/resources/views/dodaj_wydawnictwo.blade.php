<!DOCTYPE html>
<html> 
@include('partials.head')
<body> 
    @include('partials.navi') 
    @include('partials.alert')

    <div id="zawartosc">
        <h2>Dodaj wydawnicwtwo</h2>
        <form class="form-inline" action="./dodaj_wydawnictwo" method="post"> 
            @csrf 
            <p> 
                <label for="nazwa">Nazwa wydawnictwa</label> 
                <input id="nazwa" name="nazwa" size="20"> 
            </p>
            <p> 
                <label for="adres">Adres wydawnictwa</label> 
                <input id="adres" name="adres" size="20"> 
            </p>
            <p>
                <button type="submit" class="btn btn-primary mb-2">Dodaj</button>
            </p>
        </form>
    </div>
</body>
</html>