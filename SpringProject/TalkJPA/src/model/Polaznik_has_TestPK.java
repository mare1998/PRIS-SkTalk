package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Polaznik_has_Test database table.
 * 
 */
@Embeddable
public class Polaznik_has_TestPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="Polaznik_Korisnik_idKorisnik", insertable=false, updatable=false)
	private int polaznik_Korisnik_idKorisnik;

	@Column(name="Test_idTest", insertable=false, updatable=false)
	private int test_idTest;

	public Polaznik_has_TestPK() {
	}
	public int getPolaznik_Korisnik_idKorisnik() {
		return this.polaznik_Korisnik_idKorisnik;
	}
	public void setPolaznik_Korisnik_idKorisnik(int polaznik_Korisnik_idKorisnik) {
		this.polaznik_Korisnik_idKorisnik = polaznik_Korisnik_idKorisnik;
	}
	public int getTest_idTest() {
		return this.test_idTest;
	}
	public void setTest_idTest(int test_idTest) {
		this.test_idTest = test_idTest;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Polaznik_has_TestPK)) {
			return false;
		}
		Polaznik_has_TestPK castOther = (Polaznik_has_TestPK)other;
		return 
			(this.polaznik_Korisnik_idKorisnik == castOther.polaznik_Korisnik_idKorisnik)
			&& (this.test_idTest == castOther.test_idTest);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.polaznik_Korisnik_idKorisnik;
		hash = hash * prime + this.test_idTest;
		
		return hash;
	}
}