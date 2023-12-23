<!DOCTYPE html>
<html> 
@include('partials.head')
<body> 
    @include('partials.navi') 
    @include('partials.alert')

    <div id="zawartosc">
        <h2>Dodaj książkę</h2>
        <form class="form-inline" action="./dodaj_ksiazke" method="post"> 
            @csrf 
            <p> 
                <label for="tytul">Tytuł książki</label> 
                <input id="tytul" name="tytul" size="20"> 
            </p>
            <p> 
                @foreach($kategorie as $el) 
                    <input type="radio" name="idkat" id="idkat" value={{$el->id}}> 
                    <label for="idkat">{{$el->opis}}</label> 
                @endforeach 
            </p>
            <p> 
                <label for="wydawnictwo">Wybierz wydawnictwo:</label>
                <select id="wydawnictwo" name="idwyd">
                    @foreach($wydawnictwa as $el) 
                        <option value={{$el->id}}>{{$el->nazwa}}, {{$el->adres}}</option>
                    @endforeach 
                </select>
            </p>
            <p>
                <button type="submit" class="btn btn-primary mb-2">Dodaj</button>
            </p>
        </form>
    </div>
</body>
</html>