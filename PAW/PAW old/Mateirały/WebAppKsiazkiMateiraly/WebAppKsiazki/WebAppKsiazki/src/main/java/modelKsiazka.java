import java.io.Serializable;
import java.sql.Date;

public class modelKsiazka implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int idk;
	private String tytul;
	private Date rokwydania;
	private modelWydawnictwo wyd;
	private modelKategoria kat;
	
	
	public int getIdk() {
		return this.idk;
	}
	
	public void setIdk(int idk) {
		this.idk = idk;
	}
	
	public Date getRokWydania() {
		return this.rokwydania;
	}
	
	public void setRokWydania(Date rokwydania) {
		this.rokwydania = rokwydania;
	}
	
	public String getTytul() {
		return this.tytul;
	}
	

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}
	
	public modelWydawnictwo getWyd() {
		return wyd;
	}
	
	public void setWyd(modelWydawnictwo wyd) {
		this.wyd=wyd;
	}
	
	public modelKategoria getKat() {
		return kat;
	}
	
	public void setKat(modelKategoria kat) {
		this.kat=kat;
	}



}
