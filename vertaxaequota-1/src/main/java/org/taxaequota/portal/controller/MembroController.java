package org.taxaequota.portal.controller;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.taxaequota.portal.model.Membro;
import org.taxaequota.portal.model.Quota;
import org.taxaequota.portal.model.Taxa;
import org.taxaequota.portal.service.MembroService;
@Controller
@RequestMapping("/")
@SessionAttributes("membro")
public class MembroController {

	@Autowired
	private MembroService ms;
	private final Logger log = LoggerFactory.getLogger(getClass());
	private static Membro membro;
	
	public MembroController() {
		this.membro= new Membro();
	}


	@PostMapping("home")
	public String mebros(Membro membro, Model model, SessionStatus sesStatus,RedirectAttributes flash){
		this.membro=null;
		double taxatotal=0.0;
		double quotatotal=0.0;
		double escalao=0.0;
		List<Taxa> taxas =new ArrayList<Taxa>(); 
		
		
		this.membro= ms.findMembroBySenha(membro.getSenha());
		
		if(this.membro!=null) {
		taxas= ms.findTaxa(this.membro.getNumero());	
		taxatotal= ms.findTaxaTotal(this.membro.getNumero());
		quotatotal= ms.findQuotaTotal(this.membro.getNumero());
		escalao=ms.findEscalao(this.membro.getNumero());
		model.addAttribute("listtaxas",taxas);
		
		model.addAttribute("titulo", this.membro.getNome());
		model.addAttribute("totaltaxa", taxatotal);
		model.addAttribute("quotatotal", quotatotal);
		model.addAttribute("escalao", escalao);
		return "fininvest/index";
		}
		return "redirect:/login";
	}	
	
	@GetMapping("home1")
	public String home(Membro membro, Model model, SessionStatus sesStatus,RedirectAttributes flash){
		
		double taxatotal=0.0;
		double quotatotal=0.0;
		double escalao=0.0;
		List<Taxa> taxas =new ArrayList<Taxa>(); 
		if(this.membro!=null) {
		taxas= ms.findTaxa(this.membro.getNumero());	
		taxatotal= ms.findTaxaTotal(this.membro.getNumero());
		quotatotal= ms.findQuotaTotal(this.membro.getNumero());
		escalao=ms.findEscalao(this.membro.getNumero());
		model.addAttribute("listtaxas",taxas);
		
		model.addAttribute("titulo", this.membro.getNome());
		model.addAttribute("totaltaxa", taxatotal);
		model.addAttribute("quotatotal", quotatotal);
		model.addAttribute("escalao", escalao);
		return "fininvest/index";
		}
		return "redirect:/login";
	}	
	
	
	
	@GetMapping("extratos")
	public String taxas(Membro membro, Model model, SessionStatus sesStatus,RedirectAttributes flash){
		
		double taxatotal=0.0;
		double quotatotal=0.0;
		List<Taxa> taxas =new ArrayList<Taxa>(); 
		List<Quota> quotas =new ArrayList<Quota>();
		quotatotal= ms.findQuotaTotal(this.membro.getNumero());
		
		if(this.membro!=null) {
		taxas= ms.findTaxa(this.membro.getNumero());
		quotas= ms.findQuota(this.membro.getNumero());		
		taxatotal= ms.findTaxaTotal(this.membro.getNumero());
		
		model.addAttribute("quotatotal", quotatotal);
		model.addAttribute("listtaxas",taxas);
		model.addAttribute("listquotas",quotas);
		model.addAttribute("totaltaxa", taxatotal);
		model.addAttribute("titulo", this.membro.getNome());
		return "fininvest/extrato";
		}
		return "redirect:/home";
	}	
	
	@GetMapping("login")
	public String showform(Membro membro, Model model, SessionStatus sesStatus,RedirectAttributes flash){
		model.addAttribute("membro", membro);
		return "fininvest/form";
		
	}	
}			