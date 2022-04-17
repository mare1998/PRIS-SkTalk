package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the Test database table.
 * 
 */
@Entity
@NamedQuery(name="Test.findAll", query="SELECT t FROM Test t")
public class Test implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTest;

	@Column(name="broj_bodova")
	private int brojBodova;

	@Column(name="procenat_polaganja")
	private double procenatPolaganja;

	//bi-directional many-to-one association to Pitanje
	@OneToMany(mappedBy="test")
	@JsonIgnore
	private List<Pitanje> pitanjes;

	//bi-directional many-to-one association to Polaznik_has_Test
	@OneToMany(mappedBy="test")
	@JsonIgnore
	private List<Polaznik_has_Test> polaznikHasTests;

	//bi-directional many-to-one association to Kur
	@ManyToOne
	@JoinColumn(name="Kurs_idKurs")
	@JsonIgnore
	private Kur kur;

	public Test() {
	}

	public int getIdTest() {
		return this.idTest;
	}

	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}

	public int getBrojBodova() {
		return this.brojBodova;
	}

	public void setBrojBodova(int brojBodova) {
		this.brojBodova = brojBodova;
	}

	public double getProcenatPolaganja() {
		return this.procenatPolaganja;
	}

	public void setProcenatPolaganja(double procenatPolaganja) {
		this.procenatPolaganja = procenatPolaganja;
	}

	public List<Pitanje> getPitanjes() {
		return this.pitanjes;
	}

	public void setPitanjes(List<Pitanje> pitanjes) {
		this.pitanjes = pitanjes;
	}

	public Pitanje addPitanje(Pitanje pitanje) {
		getPitanjes().add(pitanje);
		pitanje.setTest(this);

		return pitanje;
	}

	public Pitanje removePitanje(Pitanje pitanje) {
		getPitanjes().remove(pitanje);
		pitanje.setTest(null);

		return pitanje;
	}

	public List<Polaznik_has_Test> getPolaznikHasTests() {
		return this.polaznikHasTests;
	}

	public void setPolaznikHasTests(List<Polaznik_has_Test> polaznikHasTests) {
		this.polaznikHasTests = polaznikHasTests;
	}

	public Polaznik_has_Test addPolaznikHasTest(Polaznik_has_Test polaznikHasTest) {
		getPolaznikHasTests().add(polaznikHasTest);
		polaznikHasTest.setTest(this);

		return polaznikHasTest;
	}

	public Polaznik_has_Test removePolaznikHasTest(Polaznik_has_Test polaznikHasTest) {
		getPolaznikHasTests().remove(polaznikHasTest);
		polaznikHasTest.setTest(null);

		return polaznikHasTest;
	}

	public Kur getKur() {
		return this.kur;
	}

	public void setKur(Kur kur) {
		this.kur = kur;
	}

}