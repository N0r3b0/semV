import java.sql.*;
import java.util.ArrayList;

public class daoKsiazki {
	
	private Connection dbcon = null;
	private Statement dbstat = null;
	
	private void otworzCon() 
	{
		String login = "s49016"; //postgres dla lokalnego serwera
		String haslo = "9Vg9Eg2Kt7Ef"; //puste dla lokalnego serwera
		String url="jdbc:postgresql://localhost:5432/s49016?currentSchema=\"ksiazki\"";
		try {
			Class.forName("org.postgresql.Driver");
			dbcon = DriverManager.getConnection(url, login, haslo);
			dbstat=dbcon.createStatement();
			//System.out.println("Polączenie otwarte");
		}
		catch (ClassNotFoundException ex){
			System.err.println("ClassNotFoundException z init:"+ex.getMessage());
		}
		catch (SQLException ex){
			System.err.println("SQLException z init: " + ex.getMessage());
		}
	}
	
	private void zamknijCon() {
		if(dbcon==null) return;
		try {
			dbcon.close();
			//System.out.println("Polączenie zamknięte");
		}
		catch(SQLException ex)
		{
			System.out.println("Problem z zamknięciem bazy");
		}
	}
	
	public ArrayList<modelKategoria> listaKategorii() {
		ArrayList<modelKategoria> lk = new ArrayList<modelKategoria>();
		
		String pyt = "SELECT idk, opis FROM ksiazki.kategoria";

        try {
            otworzCon();
            ResultSet wyniki = dbstat.executeQuery(pyt);

            while (wyniki.next()) {
                modelKategoria k = new modelKategoria();
                k.setIdk(wyniki.getInt("idk"));
                k.setOpis(wyniki.getString("opis"));
                lk.add(k);
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        } 
        finally {
        	zamknijCon();
        }
        return lk;
	}
	
	public ArrayList<modelWydawnictwo> listaWydawnictw() {
		ArrayList<modelWydawnictwo> lk = new ArrayList<modelWydawnictwo>();
		
		String pyt = "SELECT idwyd, nazwa, miasto, panstwo FROM ksiazki.wydawnictwo";

        try {
            otworzCon();
            ResultSet wyniki = dbstat.executeQuery(pyt);

            while (wyniki.next()) {
            	modelWydawnictwo k = new modelWydawnictwo();
                k.setIdwyd(wyniki.getInt("idwyd"));
                k.setNazwa(wyniki.getString("nazwa"));
                k.setMiasto(wyniki.getString("miasto"));
                k.setPanstwo(wyniki.getString("panstwo"));
                lk.add(k);
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        } 
        finally {
        	zamknijCon();
        }
        return lk;
	}
	
	public ArrayList<modelKsiazka> listaKsiazek() {
		ArrayList<modelKsiazka> lk = new ArrayList<modelKsiazka>();
		
		String pyt = "SELECT idk, tytul, rok_wydania, idwyd, idkat FROM ksiazki.ksiazka";

        try {
            otworzCon();
            ResultSet wyniki = dbstat.executeQuery(pyt);

            while (wyniki.next()) {
            	modelKsiazka k = new modelKsiazka();
                k.setIdk(wyniki.getInt("idk"));
                k.setTytul(wyniki.getString("tytul"));
                k.setRokWydania(wyniki.getDate("rok_wydania"));
                
                int idwyd = wyniki.getInt("idwyd");
                modelWydawnictwo wydawnictwo = pobierzWydawnictwo(idwyd);
                
                int idkat = wyniki.getInt("idk");
                modelKategoria kategoria = pobierzKategorie(idkat);
                
                k.setWyd(wydawnictwo);
                k.setKat(kategoria);
                
                lk.add(k);
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        } 
        finally {
        	zamknijCon();
        }
        return lk;
	}
	
	public modelWydawnictwo pobierzWydawnictwo(int id) {
	    modelWydawnictwo wydawnictwo = new modelWydawnictwo();

	    try {
	        otworzCon();

	        String pytanie = "SELECT idwyd, nazwa, miasto, panstwo FROM ksiazki.wydawnictwo WHERE idwyd = ?";
	        PreparedStatement stmt = dbcon.prepareStatement(pytanie);
	        stmt.setInt(1, id);

	        ResultSet wyniki = stmt.executeQuery();

	        if (wyniki.next()) {
	            wydawnictwo.setIdwyd(wyniki.getInt("idwyd"));
	            wydawnictwo.setNazwa(wyniki.getString("nazwa"));
	            wydawnictwo.setMiasto(wyniki.getString("miasto"));
	            wydawnictwo.setPanstwo(wyniki.getString("panstwo"));
	        }
	    } 
	    catch (Exception e) {
	        System.out.println(e);
	    } 
	    finally {
	        zamknijCon();
	    }

	    return wydawnictwo;
	}

	public modelKategoria pobierzKategorie(int id) {
	    modelKategoria kategoria = new modelKategoria();

	    try {
	        otworzCon();

	        String pytanie = "SELECT idk, opis FROM ksiazki.kategoria WHERE idk = ?";
	        PreparedStatement stmt = dbcon.prepareStatement(pytanie);
	        stmt.setInt(1, id);

	        ResultSet wyniki = stmt.executeQuery();

	        if (wyniki.next()) {
	            kategoria.setIdk(wyniki.getInt("idk"));
	            kategoria.setOpis(wyniki.getString("opis"));
	        }
	    } 
	    catch (Exception e) {
	        System.out.println(e);
	    } 
	    finally {
	        zamknijCon();
	    }

	    return kategoria;
	}
	
	public ArrayList<modelKsiazka> listaKsiazekKategorii(int id) {
	    ArrayList<modelKsiazka> lk = new ArrayList<modelKsiazka>();
	    
	    String pyt = "SELECT idk, tytul, rok_wydania, idwyd, idk FROM ksiazki.ksiazka WHERE idk = ?";

	    try {
	        otworzCon();
	        PreparedStatement stmt = dbcon.prepareStatement(pyt);
	        stmt.setInt(1, id);
	        ResultSet wyniki = stmt.executeQuery();

	        while (wyniki.next()) {
	            modelKsiazka k = new modelKsiazka();
	            k.setIdk(wyniki.getInt("idk"));
	            k.setTytul(wyniki.getString("tytul"));
	            k.setRokWydania(wyniki.getDate("rok_wydania"));
	            
	            int idwyd = wyniki.getInt("idwyd");
	            modelWydawnictwo wydawnictwo = pobierzWydawnictwo(idwyd);
	            
	            int idkat = wyniki.getInt("idk");
	            modelKategoria kategoria = pobierzKategorie(idkat);
	            
	            k.setWyd(wydawnictwo);
	            k.setKat(kategoria);
	            
	            lk.add(k);
	        }
	    } 
	    catch (Exception e) {
	        System.out.println(e);
	    } 
	    finally {
	        zamknijCon();
	    }
	    return lk;
	}
	
	public void dodajKsiazke(String tytul, Date rok_wydania, int idwyd, int idk) throws SQLException {
        String pytanie = "INSERT INTO ksiazki.ksiazka (tytul, rok_wydania, idwyd, idkat) VALUES (?, ?, ?, ?)";
        
        try {
            otworzCon();
            PreparedStatement stmt = dbcon.prepareStatement(pytanie);
            stmt.setString(1, tytul);
            stmt.setDate(2, rok_wydania);
            
            // Pobierz id wydawnictwa i kategorii na podstawie nazw
//            int idWydawnictwa = pobierzIdWydawnictwa(wydawnictwo);
//            int idKategorii = pobierzIdKategorii(kategoria);
            
            stmt.setInt(3, idwyd);
            stmt.setInt(4, idk);
            
            stmt.executeUpdate();
        } finally {
            zamknijCon();
        }
    }

    public void usunKsiazke(int id) throws SQLException {
        String pytanie = "DELETE FROM ksiazki.ksiazka WHERE idk = ?";
        
        try {
            otworzCon();
            PreparedStatement stmt = dbcon.prepareStatement(pytanie);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } finally {
            zamknijCon();
        }
    }
    
    public void dodajKategorie(String opis) throws SQLException {
        String pytanie = "INSERT INTO ksiazki.kategoria (opis) VALUES (?)";
        
        try {
            otworzCon();
            PreparedStatement stmt = dbcon.prepareStatement(pytanie);
            stmt.setString(1, opis);
            stmt.executeUpdate();
        } finally {
            zamknijCon();
        }
    }

    public void usunKategorie(int id) throws SQLException {
        String pytanie = "DELETE FROM ksiazki.kategoria WHERE idk = ?";
        
        try {
            otworzCon();
            PreparedStatement stmt = dbcon.prepareStatement(pytanie);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } finally {
            zamknijCon();
        }
    }
    
    public void dodajWydawnictwo(String nazwa, String miasto, String panstwo) throws SQLException {
        String pytanie = "INSERT INTO ksiazki.wydawnictwo (nazwa, miasto, panstwo) VALUES (?, ?, ?)";
        
        try {
            otworzCon();
            PreparedStatement stmt = dbcon.prepareStatement(pytanie);
            stmt.setString(1, nazwa);
            stmt.setString(2, miasto);
            stmt.setString(3, panstwo);
            

            stmt.executeUpdate();
        } finally {
            zamknijCon();
        }
    }

    public void usunWydawnictwo(int id) throws SQLException {
        String pytanie = "DELETE FROM ksiazki.wydawnictwo WHERE idwyd = ?";
        
        try {
            otworzCon();
            PreparedStatement stmt = dbcon.prepareStatement(pytanie);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } finally {
            zamknijCon();
        }
    }
    
    private int pobierzIdWydawnictwa(String nazwa) throws SQLException {
        String pytanie = "SELECT idwyd FROM ksiazki.wydawnictwo WHERE nazwa = ?";
        PreparedStatement stmt = dbcon.prepareStatement(pytanie);
        stmt.setString(1, nazwa);
        ResultSet wyniki = stmt.executeQuery();

        if (wyniki.next()) {
            return wyniki.getInt("idwyd");
        }

        return -1; // Jeśli nie znaleziono, można obsłużyć ten przypadek odpowiednio
    }

    private int pobierzIdKategorii(String opis) throws SQLException {
        String pytanie = "SELECT idk FROM ksiazki.kategoria WHERE opis = ?";
        PreparedStatement stmt = dbcon.prepareStatement(pytanie);
        stmt.setString(1, opis);
        ResultSet wyniki = stmt.executeQuery();

        if (wyniki.next()) {
            return wyniki.getInt("idk");
        }

        return -1; // Jeśli nie znaleziono, można obsłużyć ten przypadek odpowiednio
    }



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//daoKsiazki testpgsql = new daoKsiazki();
		//testpgsql.otworzCon();
		//testpgsql.zamknijCon();
		
		daoKsiazki obi = new daoKsiazki();
		//ArrayList<modelKategoria> lista = obi.listaKategorii();
		//ArrayList<modelWydawnictwo> lista2 = obi.listaWydawnictw();
		ArrayList<modelKsiazka> lista3 = obi.listaKsiazek();
		
		String s="";
		//for(int i=0; i<lista.size(); i++)
		//	s += lista.get(i).getOpis()+", ";
		//System.out.println(s);
		
		//s="";
		//for(int i=0; i<lista2.size(); i++)
		//	s += lista2.get(i).getNazwa()+" - "+lista2.get(i).getAdres()+";  ";
		//System.out.println(s);
		
		s="";
		for(int i=0; i<lista3.size(); i++)
			//s += lista3.get(i).getIdk()+" - "+lista3.get(i).getTytul()+";  ";
			s += lista3.get(i).getIdk() + " - " + lista3.get(i).getTytul() + " - " + lista3.get(i).getRokWydania() + "; (Kategoria: " + lista3.get(i).getKat().getOpis() + "; Wydawnictwo: " + lista3.get(i).getWyd().getNazwa() + ", " + lista3.get(i).getWyd().getMiasto() + ", " + lista3.get(i).getWyd().getPanstwo() + "); \n";
		System.out.println(s);

	
	}

}
