package com.shop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shop.struct.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{ 

	Role findByName(String name);
}
