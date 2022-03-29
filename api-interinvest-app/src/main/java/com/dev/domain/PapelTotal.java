package com.dev.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "papel_total")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PapelTotal {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long papelTotalId;
	private String nome;
	private String ticker;
	private Double total;
	
	public PapelTotal() {
		
	}
	
	public PapelTotal(String nome, String ticker, Double total) {
		this.nome = nome;
		this.ticker = ticker;
		this.total = total;
	}
	
	@ManyToMany(mappedBy="valorTotaisPorPapeis", cascade = CascadeType.ALL)
	private List<Investimento> totalPapeis;

}
