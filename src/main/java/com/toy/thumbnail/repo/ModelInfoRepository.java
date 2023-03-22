package com.toy.thumbnail.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toy.thumbnail.model.ModelInfo;

public interface ModelInfoRepository extends JpaRepository<ModelInfo, Long>{
	
}
