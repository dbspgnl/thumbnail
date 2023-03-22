package com.toy.thumbnail.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toy.thumbnail.model.MyEntity;

public interface MyEntityRepository extends JpaRepository<MyEntity, Long>{
	
}
