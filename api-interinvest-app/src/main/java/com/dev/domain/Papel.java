package com.dev.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "papel")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Papel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long papelId;
	private String nome;
	private String ticker;
	private Double preco;
	@Enumerated(EnumType.STRING)
	private Status status;	
	
	@ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
	@JsonIgnoreProperties(value = "papeis")
	private Cliente cliente;
	
}
