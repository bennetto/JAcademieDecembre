package org.jacademie.examenDecembre.DAOs;

import java.util.Set;

public class Artiste {

	private Integer codeArtist;
	private String nom;
	private Set<Chanson> chansons;
	
	public Artiste(Integer codeArtist, String nom, Set<Chanson> chansons) {
		super();
		this.codeArtist = codeArtist;
		this.nom = nom;
		this.chansons = chansons;
	}
	
	
	
	@Override
	public String toString() {
		return "Artiste [codeArtist=" + codeArtist + ", nom=" + nom
				+ ", chansons=" + chansons + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chansons == null) ? 0 : chansons.hashCode());
		result = prime * result
				+ ((codeArtist == null) ? 0 : codeArtist.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artiste other = (Artiste) obj;
		if (chansons == null) {
			if (other.chansons != null)
				return false;
		} else if (!chansons.equals(other.chansons))
			return false;
		if (codeArtist == null) {
			if (other.codeArtist != null)
				return false;
		} else if (!codeArtist.equals(other.codeArtist))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	
	public Integer getCodeArtist() {
		return codeArtist;
	}
	public void setCodeArtist(Integer codeArtist) {
		this.codeArtist = codeArtist;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Set<Chanson> getChansons() {
		return chansons;
	}
	public void setChansons(Set<Chanson> chansons) {
		this.chansons = chansons;
	}
	
	
}
