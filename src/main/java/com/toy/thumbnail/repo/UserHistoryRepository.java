package com.toy.thumbnail.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toy.thumbnail.model.UserHistory;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long>{
	
}
