package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Predavac database table.
 * 
 */
@Entity
@NamedQuery(name="Predavac.findAll", query="SELECT p FROM Predavac p")
public class Predavac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Korisnik_idKorisnik")
	private int korisnik_idKorisnik;

	private int plata;

	private int staz;

	//bi-directional many-to-one association to Kur
	@OneToMany(mappedBy="predavac")
	private List<Kur> kurs;

	//bi-directional one-to-one association to Korisnik
	@OneToOne
	@JoinColumn(name="Korisnik_idKorisnik")
	private Korisnik korisnik;

	public Predavac() {
	}


	public int getPlata() {
		return this.plata;
	}

	public void setPlata(int plata) {
		this.plata = plata;
	}

	public int getStaz() {
		return this.staz;
	}

	public void setStaz(int staz) {
		this.staz = staz;
	}

	public List<Kur> getKurs() {
		return this.kurs;
	}

	public void setKurs(List<Kur> kurs) {
		this.kurs = kurs;
	}

	public Kur addKur(Kur kur) {
		getKurs().add(kur);
		kur.setPredavac(this);

		return kur;
	}

	public Kur removeKur(Kur kur) {
		getKurs().remove(kur);
		kur.setPredavac(null);

		return kur;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}