package com.dev.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "investimento")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Investimento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long investimentoId;
	private Double valorAInvestir;
	private Integer quantidadeDePapeis;
	private Double valorTotalPorPapel;
	private Double valorTotalCompra;
	private Double troco;
	
	@ManyToMany(cascade = CascadeType.ALL)
	  @JoinTable(name="investimento_papel",
      joinColumns={@JoinColumn(name="investimento_id")},
      inverseJoinColumns={@JoinColumn(name="papel_id")})	
	@JsonIgnoreProperties(value = "investimentos")
	private List<Papel> papeisComprados;

}
