package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the Pitanje database table.
 * 
 */
@Entity
@NamedQuery(name="Pitanje.findAll", query="SELECT p FROM Pitanje p")
public class Pitanje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPitanje;

	private double bodovi;

	private String pitanje;

	//bi-directional one-to-one association to Odgovor
	@OneToOne(mappedBy="pitanje")
	@JsonIgnore
	private Odgovor odgovor;

	//bi-directional many-to-one association to Test
	@ManyToOne
	@JoinColumn(name="Test_idTest")
	@JsonIgnore
	private Test test;

	public Pitanje() {
	}

	public int getIdPitanje() {
		return this.idPitanje;
	}

	public void setIdPitanje(int idPitanje) {
		this.idPitanje = idPitanje;
	}

	public double getBodovi() {
		return this.bodovi;
	}

	public void setBodovi(double bodovi) {
		this.bodovi = bodovi;
	}

	public String getPitanje() {
		return this.pitanje;
	}

	public void setPitanje(String pitanje) {
		this.pitanje = pitanje;
	}

	public Odgovor getOdgovor() {
		return this.odgovor;
	}

	public void setOdgovor(Odgovor odgovor) {
		this.odgovor = odgovor;
	}

	public Test getTest() {
		return this.test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

}