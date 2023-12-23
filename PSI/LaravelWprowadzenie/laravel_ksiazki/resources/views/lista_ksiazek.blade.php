<!DOCTYPE html> 
<html lang="pl">   
    @include('partials.head')   
    <body>
        @include('partials.navi')     
        <div id="zawartosc">       
            <h2>Lista książek</h2>       
            <table>         
                <thead>           
                    <tr> 
                        <th>Tytuł</th> 
                        <th>Kategoria</th> 
                        <th>Wydawnictwo</th> 
                        <th>Akcje</th> 
                    </tr>         
                </thead>         
                <tbody>           
                    @foreach($ksiazki as $el)             
                    <tr> 
                        <td>{{$el->tytul}}</td>
                        <td>{{$el->opis}}</td> 
                        <td>{{$el->nazwa}}</td> 
                        <td>
                            <form method="POST" action="./usun_ksiazke">
                                @csrf
                                @method('DELETE')
                                <input type="hidden" name="idKsiazki" value="{{$el->id_ksiazki}}">
                                <button type="submit">Usuń</button>
                            </form>
                        </td>
                    </tr>           
                    @endforeach         
                </tbody>       
            </table>     
        </div>   
    </body> 
</html>