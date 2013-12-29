package org.jacademie.examenDecembre.DAOs;

import java.io.Serializable;

public class Chanson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4784667312719445300L;
	
	private Integer numero;
	private String titre;
	private Integer duree;
	private Album album;

	public Chanson() {
		super();

	}

	public Chanson(Integer numero, String titre, Integer duree, Album album) {
		super();
		this.numero = numero;
		this.titre = titre;
		this.duree = duree;
		this.album = album;
	}

	public Chanson(Integer numero, String titre, Integer duree) {
		super();
		this.numero = numero;
		this.titre = titre;
		this.duree = duree;
	}
	
	@Override
	public String toString() {
		return "Chanson [numero=" + numero + ", titre=" + titre + ", duree="
				+ duree + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((duree == null) ? 0 : duree.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
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
		Chanson other = (Chanson) obj;
		if (duree == null) {
			if (other.duree != null)
				return false;
		} else if (!duree.equals(other.duree))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}
	
	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Integer getDuree() {
		return duree;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	

	

}
