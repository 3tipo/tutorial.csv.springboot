package org.taxaequota.portal.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Table(name = "quotas")
@Entity
public class Quota implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	private static final long serialVersionUID = 1L;
	private String numero;
	private double valorcotapago;
	private double valorcotadivida;
	private double valorcota;
	private String mespagamento;
	private String datalimitequota;
	private String reciboPagamento;
	public Quota() {
		
	}
	
}
