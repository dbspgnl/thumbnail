package com.toy.thumbnail.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toy.thumbnail.model.Content;

public interface ContentRepository extends JpaRepository<Content, Long>{

	List<Content> findByOrderByContentTierAsc();
	List<Content> findByOrderByContentTierDesc();
	List<Content> findByContentTier(Integer tier);
	Content findByContentName(String contentName);
	
}
