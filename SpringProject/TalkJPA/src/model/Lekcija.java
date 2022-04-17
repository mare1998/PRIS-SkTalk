package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the Lekcija database table.
 * 
 */
@Entity
@NamedQuery(name="Lekcija.findAll", query="SELECT l FROM Lekcija l")
public class Lekcija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLekcija;

	@Lob
	private byte[] slika;

	private String tekst;

	@Column(name="url_videa")
	private String urlVidea;

	//bi-directional many-to-one association to Kur
	@ManyToOne
	@JoinColumn(name="Kurs_idKurs")
	@JsonIgnore
	private Kur kur;

	public Lekcija() {
	}

	public int getIdLekcija() {
		return this.idLekcija;
	}

	public void setIdLekcija(int idLekcija) {
		this.idLekcija = idLekcija;
	}

	public byte[] getSlika() {
		return this.slika;
	}

	public void setSlika(byte[] slika) {
		this.slika = slika;
	}

	public String getTekst() {
		return this.tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public String getUrlVidea() {
		return this.urlVidea;
	}

	public void setUrlVidea(String urlVidea) {
		this.urlVidea = urlVidea;
	}

	public Kur getKur() {
		return this.kur;
	}

	public void setKur(Kur kur) {
		this.kur = kur;
	}

}