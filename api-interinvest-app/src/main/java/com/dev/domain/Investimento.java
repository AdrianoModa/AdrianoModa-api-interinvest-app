package com.dev.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
	private String papeisCompradas;
	private Double valorTotalPorPapel;
	private Double troco;

}
