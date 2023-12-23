<!DOCTYPE html> 
<html lang="pl">   
    @include('partials.head')   
    <body>
        @include('partials.navi')     
        <div id="zawartosc">       
            <h2>Lista wydawnictw</h2>       
            <table>         
                <thead>           
                    <tr> 
                        <th>Nazwa</th> 
                        <th>Adres</th> 
                        <th>Akcje</th> 
                    </tr>         
                </thead>         
                <tbody>           
                    @foreach($wydawnictwa as $el)             
                    <tr> 
                        <td>{{$el->nazwa}}</td> 
                        <td>{{$el->adres}}</td> 
                        <td>
                            <form method="POST" action="./usun_wydawnictwo">
                                @csrf
                                @method('DELETE')
                                <input type="hidden" name="idWydawnictwa" value="{{$el->id}}">
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