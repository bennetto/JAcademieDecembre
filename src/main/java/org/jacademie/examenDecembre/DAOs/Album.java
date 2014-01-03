package org.jacademie.examenDecembre.DAOs;

import java.util.HashSet;
import java.util.Set;

public class Album {

	private Integer codeAlbum;
	private String nom;
	private Set<Chanson> chansons;
	
	public Album()
	{
		super();
	}
	
	public Album(Integer codeAlbum, String nom, Set<Chanson> chansons) {
		super();
		this.codeAlbum = codeAlbum;
		this.nom = nom;
		this.chansons = (chansons != null)? chansons : new HashSet<Chanson>();
	}

	@Override
	public String toString() {
		return "Album [codeAlbum=" + codeAlbum + ", nom=" + nom + ", chansons="
				+ chansons + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chansons == null) ? 0 : chansons.hashCode());
		result = prime * result
				+ ((codeAlbum == null) ? 0 : codeAlbum.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass() )
			return false;
		Album other = (Album) obj;
		
		if (chansons == null || chansons.size() == 0) {
			if (other.chansons != null  && other.chansons.size() != 0)
				return false;
		}
		else if (!chansons.equals(other.chansons))
			return false;
		if (codeAlbum == null) {
			if (other.codeAlbum != null)
				return false;
		} else if (!codeAlbum.equals(other.codeAlbum))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

	public Integer getCodeAlbum() {
		return codeAlbum;
	}
	public void setCodeAlbum(Integer codeAlbum) {
		this.codeAlbum = codeAlbum;
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
	
	public void addChanson(Chanson chanson){
		chansons.add(chanson);
		if(chanson.getAlbum()!=this)
			chanson.setAlbum(this);
	}
	
}
