package com.clubbox.clubbox.model;


import java.util.ArrayList;

public class Scorer {

	private Integer id;
	private User idUser;
	private Match idMatch;
	
	public Scorer() {
		super();
	}

	public static class List extends ArrayList<Scorer> {
		private static final long serialVersionUID = 30L;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getIdUser() {
		return idUser;
	}

	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}

	public Match getIdMatch() {
		return idMatch;
	}

	public void setIdMatch(Match idMatch) {
		this.idMatch = idMatch;
	}
	
}
