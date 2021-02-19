package org.taxaequota.portal.repo;

import org.springframework.data.repository.CrudRepository;
import org.taxaequota.portal.model.Membro;

public interface Membrorepo extends CrudRepository<Membro, Integer>{
		Membro findBySenha(String senha);
	
}
