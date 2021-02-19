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
@Table(name = "taxas")
@Entity
public class Taxa implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	private static final long serialVersionUID = 1L;
	private String numero;
	private double valortaxapago;
	private double valortaxadivida;
	private double valortaxa;
	private String mespagamentotaxa;
	private String datalimitetaxa;
	private String reciboPagamentotaxa;
	public Taxa() {
		
	}
}
