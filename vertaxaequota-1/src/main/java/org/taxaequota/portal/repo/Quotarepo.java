package org.taxaequota.portal.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.taxaequota.portal.model.Quota;

public interface Quotarepo extends CrudRepository<Quota, Integer>{

}
