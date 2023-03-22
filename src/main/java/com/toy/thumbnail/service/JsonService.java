package com.toy.thumbnail.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.thumbnail.model.ModelInfo;
import com.toy.thumbnail.model.MyEntity;
import com.toy.thumbnail.model.UserHistory;
import com.toy.thumbnail.repo.ModelInfoRepository;
import com.toy.thumbnail.repo.UserHistoryRepository;

@Service
public class JsonService {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Autowired
	UserHistoryRepository histroyRepository;

	@Autowired
	ModelInfoRepository modelInfoRepository;

	public Object myEntitySaveData() {
		MyEntity entity = new MyEntity();
		entity.setJsonData("{\"name\": \"John\", \"age\": 30}");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		entityManager.persist(entity);
		
		transaction.commit();
		entityManager.close();

		return entity;
	}

	@Transactional
    public UserHistory historySave(String exercise, String work, String movie) {
        Map<String, Object> histories = Map.of(
                "exercise", exercise,
                "work", work,
                "movie", movie
        );
        Map<String, Object> histories2 = Map.of(
                "name", "hong-gildong",
                "data", histories
        );
        var history = UserHistory.of(histories2);
        histroyRepository.save(history);
		return history;
    }

	@Transactional
	@SuppressWarnings("unchecked")
    public Object historyGet() {
		UserHistory history = histroyRepository.findById(1L).get();
		Object myWork =  history.getHistory().get("data");
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> result = objectMapper.convertValue(myWork, HashMap.class);
		System.out.println(result);
		return result;
    }

	@Transactional
    public Object modelInfoSave() {
		List<String> list = new ArrayList<String>();
        Map<String, Object> guidance_value = Map.of(
                "status", 1,
                "value", list
        );
        Map<String, Object> modelviewer_value = Map.of(
			"status", 0,
			"value", list
        );
        Map<String, Object> guidance = Map.of(
                "guidance", guidance_value
        );
        Map<String, Object> modelviewer = Map.of(
			"modelviewer", modelviewer_value
        );
		List<Map<String, Object>> content = List.of(
			guidance,
			modelviewer
		);
        var modelInfo = ModelInfo.of("dx100", content);
        modelInfoRepository.save(modelInfo);
		return content;
    }

	@Transactional
	@SuppressWarnings("unchecked")
    public Object modelInfoGet() {
		ModelInfo model = modelInfoRepository.findById(1L).get();
		Object myWork =  model.getModelInfo().get(0).get("guidance");
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> result = objectMapper.convertValue(myWork, HashMap.class);
		System.out.println(result);
		return result;
    }
	
}
