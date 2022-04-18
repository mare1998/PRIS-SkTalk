package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the Kurs database table.
 * 
 */
@Entity
@Table(name="Kurs")
@NamedQuery(name="Kur.findAll", query="SELECT k FROM Kur k")
public class Kur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKurs;

	private int cena;

	private String naziv;

	@Column(name="ocekivani_ishod")
	private String ocekivaniIshod;

	private String opis;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="kur")
	@JsonIgnore
	private List<Komentar> komentars;

	//bi-directional many-to-one association to Kategorija
	@ManyToOne
	@JoinColumn(name="Kategorija_idKategorija")
	@JsonIgnore
	private Kategorija kategorija;

	//bi-directional many-to-one association to Predavac
	@ManyToOne
	
	@JoinColumn(name="Predavac_Korisnik_idKorisnik")
	@JsonIgnore
	private Predavac predavac;

	//bi-directional many-to-many association to Polaznik
	@ManyToMany(mappedBy="kurs")
	@JsonIgnore
	private List<Polaznik> polazniks;

	//bi-directional many-to-one association to Lekcija
	@OneToMany(mappedBy="kur")
	@JsonIgnore
	private List<Lekcija> lekcijas;

	//bi-directional many-to-one association to Test
	@OneToMany(mappedBy="kur")
	@JsonIgnore
	private List<Test> tests;

	public Kur() {
	}

	public int getIdKurs() {
		return this.idKurs;
	}

	public void setIdKurs(int idKurs) {
		this.idKurs = idKurs;
	}

	public int getCena() {
		return this.cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOcekivaniIshod() {
		return this.ocekivaniIshod;
	}

	public void setOcekivaniIshod(String ocekivaniIshod) {
		this.ocekivaniIshod = ocekivaniIshod;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setKur(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setKur(null);

		return komentar;
	}

	public Kategorija getKategorija() {
		return this.kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	public Predavac getPredavac() {
		return this.predavac;
	}

	public void setPredavac(Predavac predavac) {
		this.predavac = predavac;
	}

	public List<Polaznik> getPolazniks() {
		return this.polazniks;
	}

	public void setPolazniks(List<Polaznik> polazniks) {
		this.polazniks = polazniks;
	}

	public List<Lekcija> getLekcijas() {
		return this.lekcijas;
	}

	public void setLekcijas(List<Lekcija> lekcijas) {
		this.lekcijas = lekcijas;
	}

	public Lekcija addLekcija(Lekcija lekcija) {
		getLekcijas().add(lekcija);
		lekcija.setKur(this);

		return lekcija;
	}

	public Lekcija removeLekcija(Lekcija lekcija) {
		getLekcijas().remove(lekcija);
		lekcija.setKur(null);

		return lekcija;
	}

	public List<Test> getTests() {
		return this.tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}

	public Test addTest(Test test) {
		getTests().add(test);
		test.setKur(this);

		return test;
	}

	public Test removeTest(Test test) {
		getTests().remove(test);
		test.setKur(null);

		return test;
	}

}