package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the Polaznik_has_Test database table.
 * 
 */
@Entity
@NamedQuery(name="Polaznik_has_Test.findAll", query="SELECT p FROM Polaznik_has_Test p")
public class Polaznik_has_Test implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Polaznik_has_TestPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="datum_polaganja")
	@JsonIgnore
	private Date datumPolaganja;

	@Column(name="osvojeni_poeni")
	private double osvojeniPoeni;

	//bi-directional many-to-one association to Polaznik
	@ManyToOne
	@JoinColumn(name="Polaznik_Korisnik_idKorisnik", referencedColumnName = "Korisnik_idKorisnik", insertable = false, updatable = false)
	@JsonIgnore
	private Polaznik polaznik;

	//bi-directional many-to-one association to Test
	@ManyToOne
	@JoinColumn(name="Test_idTest", referencedColumnName = "idTest", insertable = false, updatable = false)
	@JsonIgnore
	private Test test;

	public Polaznik_has_Test() {
	}

	public Polaznik_has_TestPK getId() {
		return this.id;
	}

	public void setId(Polaznik_has_TestPK id) {
		this.id = id;
	}

	public Date getDatumPolaganja() {
		return this.datumPolaganja;
	}

	public void setDatumPolaganja(Date datumPolaganja) {
		this.datumPolaganja = datumPolaganja;
	}

	public double getOsvojeniPoeni() {
		return this.osvojeniPoeni;
	}

	public void setOsvojeniPoeni(double osvojeniPoeni) {
		this.osvojeniPoeni = osvojeniPoeni;
	}

	public Polaznik getPolaznik() {
		return this.polaznik;
	}

	public void setPolaznik(Polaznik polaznik) {
		this.polaznik = polaznik;
	}

	public Test getTest() {
		return this.test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

}