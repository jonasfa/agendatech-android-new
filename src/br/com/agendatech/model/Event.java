package br.com.agendatech.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Event implements Serializable {
	public String nome;
	public String descricao;
	public Gadget[] gadgets;
}
