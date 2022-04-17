package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


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

	//bi-directional many-to-one association to Kur
	@OneToMany(mappedBy="kategorija")
	@JsonIgnore
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