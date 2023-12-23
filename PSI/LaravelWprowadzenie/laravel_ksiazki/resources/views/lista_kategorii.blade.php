<!DOCTYPE html> 
<html lang="pl">   
    @include('partials.head')   
    <body>
        @include('partials.navi')     
        <div id="zawartosc">       
            <h2>Lista kategorii</h2>       
            <table>         
                <thead>           
                    <tr> 
                        <th>Kategorie:</th> 
                        <th>Akcje</th> 
                    </tr>         
                </thead>         
                <tbody>           
                    @foreach($kategorie as $el)             
                    <tr> 
                        <td>{{$el->opis}}</td> 
                        <td>
                            <form method="POST" action="./usun_kategorie">
                                @csrf
                                @method('DELETE')
                                <input type="hidden" name="idKategorii" value="{{$el->id}}">
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