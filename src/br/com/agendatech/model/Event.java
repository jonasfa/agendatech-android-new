package br.com.agendatech.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Event implements Serializable {
	public String nome;
	public String descricao;
	public Gadget[] gadgets;
	public String logo_file_name;
	public String estado;
	public Date data;
	public Date data_termino;
	public String site;
}
