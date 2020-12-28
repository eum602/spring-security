package com.eum602.security.basicAuth.jpa;

import com.eum602.security.basicAuth.DAO.Kayak;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface KayakRepository extends CrudRepository<Kayak,Integer> {
}
