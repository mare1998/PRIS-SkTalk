package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the Polaznik database table.
 * 
 */
@Entity
@NamedQuery(name="Polaznik.findAll", query="SELECT p FROM Polaznik p")
public class Polaznik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(name="Korisnik_idKorisnik")
	private int korisnik_idKorisnik;

	private String adresa;

	private String telefon;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="polaznik")
	@JsonIgnore
	private List<Komentar> komentars;

	//bi-directional many-to-many association to Kur
	@ManyToMany
	@JoinTable(
		name="Kurs_has_Polaznik"
		, joinColumns={
			@JoinColumn(name="Polaznik_Korisnik_idKorisnik")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Kurs_idKurs")
			}
		)
	@JsonIgnore
	private List<Kur> kurs;

	//bi-directional one-to-one association to Korisnik
	@OneToOne
	@MapsId 
	@JoinColumn(name="Korisnik_idKorisnik")
	@JsonIgnore
	private Korisnik korisnik;

	//bi-directional many-to-one association to Polaznik_has_Test
	@OneToMany(mappedBy="polaznik")
	@JsonIgnore
	private List<Polaznik_has_Test> polaznikHasTests;

	public Polaznik() {
	}

	public String getAdresa() {
		return this.adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getTelefon() {
		return this.telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setPolaznik(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setPolaznik(null);

		return komentar;
	}

	public List<Kur> getKurs() {
		return this.kurs;
	}

	public void setKurs(List<Kur> kurs) {
		this.kurs = kurs;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public List<Polaznik_has_Test> getPolaznikHasTests() {
		return this.polaznikHasTests;
	}

	public void setPolaznikHasTests(List<Polaznik_has_Test> polaznikHasTests) {
		this.polaznikHasTests = polaznikHasTests;
	}

	public Polaznik_has_Test addPolaznikHasTest(Polaznik_has_Test polaznikHasTest) {
		getPolaznikHasTests().add(polaznikHasTest);
		polaznikHasTest.setPolaznik(this);

		return polaznikHasTest;
	}

	public Polaznik_has_Test removePolaznikHasTest(Polaznik_has_Test polaznikHasTest) {
		getPolaznikHasTests().remove(polaznikHasTest);
		polaznikHasTest.setPolaznik(null);

		return polaznikHasTest;
	}

}