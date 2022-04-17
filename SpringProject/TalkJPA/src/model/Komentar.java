package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the Komentar database table.
 * 
 */
@Entity
@NamedQuery(name="Komentar.findAll", query="SELECT k FROM Komentar k")
public class Komentar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKomentar;

	private String komentar;

	//bi-directional many-to-one association to Kur
	@ManyToOne
	@JoinColumn(name="Kurs_idKurs")
	@JsonIgnore
	private Kur kur;

	//bi-directional many-to-one association to Polaznik
	@ManyToOne
	@JoinColumn(name="Polaznik_Korisnik_idKorisnik")
	@JsonIgnore
	private Polaznik polaznik;

	public Komentar() {
	}

	public int getIdKomentar() {
		return this.idKomentar;
	}

	public void setIdKomentar(int idKomentar) {
		this.idKomentar = idKomentar;
	}

	public String getKomentar() {
		return this.komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public Kur getKur() {
		return this.kur;
	}

	public void setKur(Kur kur) {
		this.kur = kur;
	}

	public Polaznik getPolaznik() {
		return this.polaznik;
	}

	public void setPolaznik(Polaznik polaznik) {
		this.polaznik = polaznik;
	}

}