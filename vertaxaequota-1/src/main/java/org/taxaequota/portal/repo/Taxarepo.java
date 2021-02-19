package org.taxaequota.portal.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.taxaequota.portal.model.Taxa;

public interface Taxarepo extends PagingAndSortingRepository<Taxa, Integer>{
	
	List<Taxa> findByNumero(String numero);
}
