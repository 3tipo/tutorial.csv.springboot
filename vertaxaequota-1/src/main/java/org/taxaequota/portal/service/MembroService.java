package org.taxaequota.portal.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.taxaequota.portal.model.Membro;
import org.taxaequota.portal.model.Quota;
import org.taxaequota.portal.model.Taxa;
import org.taxaequota.portal.repo.Membrorepo;
import org.taxaequota.portal.repo.Quotarepo;
import org.taxaequota.portal.repo.Taxarepo;

@Service
public class MembroService {
	
	
	
	@Autowired
	private Taxarepo tr;
	@Autowired
	private Quotarepo qr;
	@Autowired
	private Membrorepo mr;
	
	String line="";
	public void getMembro() {	
			mr.deleteAll();
		try {
			BufferedReader br= new BufferedReader(new FileReader("src\\main\\resources\\MEMBRO.csv"));
			
			while((line=br.readLine())!=null) {
				Membro m= new Membro();
				String[] membros= line.split(";");
				
				m.setNome(membros[1]);
				m.setSenha(membros[2]);
				m.setNumero(membros[0]);
				mr.save(m);
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getTaxa() {	
		
		try {
			BufferedReader br= new BufferedReader(new FileReader("src\\main\\resources\\PAGAMENTOTAXAS1.csv"));
			tr.deleteAll();
			while((line=br.readLine())!=null) {
				Taxa t= new Taxa();
				String[] vtaxas= line.split(";");
				t.setNumero(vtaxas[0]);
				t.setReciboPagamentotaxa(vtaxas[6]);
				t.setValortaxadivida(Double.valueOf(vtaxas[8]));
				t.setDatalimitetaxa(vtaxas[4]);
				t.setValortaxa(Double.valueOf(vtaxas[2]));
				t.setValortaxapago(Double.valueOf(vtaxas[5]));
				t.setMespagamentotaxa(vtaxas[3]);
				tr.save(t);
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void deleteAllTaxa() {
		tr.deleteAll();
	}
	
	public void getQuota() {	
				qr.deleteAll();
		try {
			BufferedReader br= new BufferedReader(new FileReader("src\\main\\resources\\PAGAMENTOQUOTAS1.csv"));
			
			while((line=br.readLine())!=null) {
				Quota q= new Quota();
				String[] vtaxas= line.split(";");
				q.setNumero(vtaxas[0]);
				q.setReciboPagamento(vtaxas[6]);
				q.setDatalimitequota(vtaxas[4]);
				q.setValorcota(Double.valueOf(vtaxas[2]));
				q.setValorcotapago(Double.valueOf(vtaxas[5]));
				q.setMespagamento(vtaxas[3]);
				qr.save(q);
				//System.err.println(quotas.get(0).getValorcotapago());
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Membro findMembroBySenha(String senha){
		getMembro();
		return mr.findBySenha(senha);
	}
	
	public List<Taxa> findTaxa(String numero){
		getTaxa(); 
		List<Taxa> taxas =new ArrayList<Taxa>();
		for (Taxa taxa : tr.findAll()) {
			if(taxa.getNumero().equals(numero) && taxa.getValortaxapago()>0)
				taxas.add(taxa);
		}
		return taxas ;
	}
	public List<Quota> findQuota(String numero){
		getQuota();; 
		List<Quota> quotas =new ArrayList<Quota>();
		for (Quota quota : qr.findAll()) {
			if(quota.getNumero().equals(numero) && quota.getValorcotapago()>0)
				quotas.add(quota);
		}
		return quotas ;
	}
	
	
		
	public Double findTaxaTotal(String numero){
		getTaxa(); 
		Double vtaxa=0.0;
		for (Taxa taxa : tr.findAll()) {
			if(taxa.getNumero().equals(numero) && taxa.getValortaxapago()>0)
				vtaxa += taxa.getValortaxapago();
		}
		return vtaxa ;
	}
	
	
	public Double findQuotaTotal(String numero){
		getQuota(); 
		Double vquota=0.0;
		for (Quota quota : qr.findAll()) {
			if(quota.getNumero().equals(numero) && quota.getValorcotapago()>0)
				vquota += quota.getValorcotapago();
		}
		return vquota ;
	}
	
	public Double findEscalao(String numero){
		getQuota(); 
		Double vescalao=0.0;
		for (Quota quota : qr.findAll()) {
			if(quota.getNumero().equals(numero) && quota.getValorcota()>0)
				vescalao = quota.getValorcota();
		}
		return vescalao ;
	}
	
	
}
