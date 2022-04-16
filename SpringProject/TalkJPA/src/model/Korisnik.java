package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String email;

	private String bio;

	private int idKorisnik;

	private String ime;

	private String password;

	private String prezime;

	private String username;

	//bi-directional one-to-one association to Administrator
	@OneToOne(mappedBy="korisnik")
	private Administrator administrator;

	//bi-directional one-to-one association to Polaznik
	@OneToOne(mappedBy="korisnik")
	private Polaznik polaznik;

	//bi-directional one-to-one association to Predavac
	@OneToOne(mappedBy="korisnik")
	private Predavac predavac;

	public Korisnik() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public int getIdKorisnik() {
		return this.idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Administrator getAdministrator() {
		return this.administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public Polaznik getPolaznik() {
		return this.polaznik;
	}

	public void setPolaznik(Polaznik polaznik) {
		this.polaznik = polaznik;
	}

	public Predavac getPredavac() {
		return this.predavac;
	}

	public void setPredavac(Predavac predavac) {
		this.predavac = predavac;
	}

}