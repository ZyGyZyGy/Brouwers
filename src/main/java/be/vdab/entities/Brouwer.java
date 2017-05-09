package be.vdab.entities;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import be.vdab.valueobjects.Adres;

@Entity
@Table(name = "brouwers")
public class Brouwer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Length(min = 1, max = 50)
    @SafeHtml
    private String naam;
    @NumberFormat(style = Style.CURRENCY)
    @NotNull
    @Min(0)
    private Integer omzet;
    @Valid
    @Embedded
    private Adres adres;
    @Version
    private long versie;

    public Brouwer() {
	
    }
    
    public Brouwer(String naam, Integer omzet, Adres adres) {
	this.naam = naam;
	this.omzet = omzet;
	this.adres = adres;
    }

    public Brouwer(long id, String naam, Integer omzet, Adres adres) {
	this(naam, omzet, adres);
	this.id = id;
    }

    public long getBrouwerNr() {
	return id;
    }

    public void setBrouwerNr(long id) {
	this.id = id;
    }

    public String getNaam() {
	return naam;
    }

    public void setNaam(String naam) {
	this.naam = naam;
    }

    public Integer getOmzet() {
	return omzet;
    }

    public void setOmzet(Integer omzet) {
	this.omzet = omzet;
    }

    public Adres getAdres() {
	return adres;
    }

    public void setAdres(Adres adres) {
	this.adres = adres;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((adres == null) ? 0 : adres.hashCode());
	result = prime * result + ((naam == null) ? 0 : naam.hashCode());
	result = prime * result + ((omzet == null) ? 0 : omzet.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (!(obj instanceof Brouwer)) {
	    return false;
	}
	Brouwer other = (Brouwer) obj;
	if (adres == null) {
	    if (other.adres != null) {
		return false;
	    }
	} else if (!adres.equals(other.adres)) {
	    return false;
	}
	if (naam == null) {
	    if (other.naam != null) {
		return false;
	    }
	} else if (!naam.equals(other.naam)) {
	    return false;
	}
	if (omzet == null) {
	    if (other.omzet != null) {
		return false;
	    }
	} else if (!omzet.equals(other.omzet)) {
	    return false;
	}
	return true;
    }

}
