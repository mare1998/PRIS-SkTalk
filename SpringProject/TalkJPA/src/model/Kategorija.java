package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Kategorija database table.
 * 
 */
@Entity
@NamedQuery(name="Kategorija.findAll", query="SELECT k FROM Kategorija k")
public class Kategorija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKategorija;

	private String naziv;

	private String nazivKategorija;

	//bi-directional many-to-one association to Kur
	@OneToMany(mappedBy="kategorija")
	private List<Kur> kurs;

	public Kategorija() {
	}

	public int getIdKategorija() {
		return this.idKategorija;
	}

	public void setIdKategorija(int idKategorija) {
		this.idKategorija = idKategorija;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getNazivKategorija() {
		return this.nazivKategorija;
	}

	public void setNazivKategorija(String nazivKategorija) {
		this.nazivKategorija = nazivKategorija;
	}

	public List<Kur> getKurs() {
		return this.kurs;
	}

	public void setKurs(List<Kur> kurs) {
		this.kurs = kurs;
	}

	public Kur addKur(Kur kur) {
		getKurs().add(kur);
		kur.setKategorija(this);

		return kur;
	}

	public Kur removeKur(Kur kur) {
		getKurs().remove(kur);
		kur.setKategorija(null);

		return kur;
	}

}