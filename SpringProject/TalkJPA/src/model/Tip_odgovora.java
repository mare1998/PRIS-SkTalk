package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Tip_odgovora database table.
 * 
 */
@Entity
@NamedQuery(name="Tip_odgovora.findAll", query="SELECT t FROM Tip_odgovora t")
public class Tip_odgovora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTip;

	private String naziv;

	//bi-directional many-to-one association to Odgovor
	@OneToMany(mappedBy="tipOdgovora")
	private List<Odgovor> odgovors;

	public Tip_odgovora() {
	}

	public int getIdTip() {
		return this.idTip;
	}

	public void setIdTip(int idTip) {
		this.idTip = idTip;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Odgovor> getOdgovors() {
		return this.odgovors;
	}

	public void setOdgovors(List<Odgovor> odgovors) {
		this.odgovors = odgovors;
	}

	public Odgovor addOdgovor(Odgovor odgovor) {
		getOdgovors().add(odgovor);
		odgovor.setTipOdgovora(this);

		return odgovor;
	}

	public Odgovor removeOdgovor(Odgovor odgovor) {
		getOdgovors().remove(odgovor);
		odgovor.setTipOdgovora(null);

		return odgovor;
	}

}