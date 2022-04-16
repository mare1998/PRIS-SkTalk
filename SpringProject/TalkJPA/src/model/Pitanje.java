package model;

import java.io.Serializable;
import javax.persistence.*;


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
	private Odgovor odgovor;

	//bi-directional many-to-one association to Test
	@ManyToOne
	@JoinColumn(name="Test_idTest")
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