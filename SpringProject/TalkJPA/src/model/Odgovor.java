package model;

import java.io.Serializable;

import javax.persistence.Column;
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
 * The persistent class for the Odgovor database table.
 * 
 */
@Entity
@NamedQuery(name="Odgovor.findAll", query="SELECT o FROM Odgovor o")
public class Odgovor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Pitanje_idPitanje")
	private int pitanje_idPitanje;

	private String odgovor;

	//bi-directional one-to-one association to Pitanje
	@OneToOne
	@JoinColumn(name="Pitanje_idPitanje")
	@JsonIgnore
	private Pitanje pitanje;

	//bi-directional many-to-one association to Tip_odgovora
	@ManyToOne
	@JoinColumn(name="Tip_idTip")
	@JsonIgnore
	private Tip_odgovora tipOdgovora;

	public Odgovor() {
	}

	public int getPitanje_idPitanje() {
		return this.pitanje_idPitanje;
	}

	public void setPitanje_idPitanje(int pitanje_idPitanje) {
		this.pitanje_idPitanje = pitanje_idPitanje;
	}

	public String getOdgovor() {
		return this.odgovor;
	}

	public void setOdgovor(String odgovor) {
		this.odgovor = odgovor;
	}

	public Pitanje getPitanje() {
		return this.pitanje;
	}

	public void setPitanje(Pitanje pitanje) {
		this.pitanje = pitanje;
	}

	public Tip_odgovora getTipOdgovora() {
		return this.tipOdgovora;
	}

	public void setTipOdgovora(Tip_odgovora tipOdgovora) {
		this.tipOdgovora = tipOdgovora;
	}

}