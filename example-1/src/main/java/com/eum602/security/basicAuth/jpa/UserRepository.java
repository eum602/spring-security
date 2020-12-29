package com.eum602.security.basicAuth.jpa;

import com.eum602.security.basicAuth.DAO.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
}
