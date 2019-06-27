package com.uf.br.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Papel implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	private String papel;

	@ManyToMany(mappedBy = "papeis")
	private List<Usuario> usuarios;

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	@Override
	public String getAuthority() {
		return this.papel;
	}
}
