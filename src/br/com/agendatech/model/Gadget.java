package br.com.agendatech.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Gadget implements Serializable {
	public User user;

	public String toString() {
		return user.nickname;
	}
}
