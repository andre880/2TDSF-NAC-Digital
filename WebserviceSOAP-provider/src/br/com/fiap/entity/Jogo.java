package br.com.fiap.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@SequenceGenerator(allocationSize = 1, name = "sqJogo", sequenceName = "SQ_JOGO")
@Table(name = "T_NAC_JOGO")
public class Jogo implements Serializable {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "sqJogo", strategy = GenerationType.SEQUENCE)
	@Column(name = "CD_JOGO", nullable = false)
	private long id;

	@Column(name = "NM_JOGO", nullable = false, unique = true)
	private String titulo;

	@Column(name = "DT_LANCAMENTO", nullable = false)
	@Temporal(value = TemporalType.DATE)
	private Calendar dataLancamento;

	@Column(name = "DS_GENERO", nullable = false)
	private String genero;

	@Column(name = "DS_DESCRICAO", nullable = true)
	private String descricao;

	public Jogo() {
		super();
	}

	public Jogo(long id, String titulo, Calendar dataLancamento, String genero, String descricao) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.dataLancamento = dataLancamento;
		this.genero = genero;
		this.descricao = descricao;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
