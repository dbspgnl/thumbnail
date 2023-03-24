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
import com.toy.thumbnail.model.Content;
import com.toy.thumbnail.model.ModelInfo;
import com.toy.thumbnail.model.MyEntity;
import com.toy.thumbnail.model.UserHistory;
import com.toy.thumbnail.repo.ContentRepository;
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

	@Autowired
	ContentRepository contentRepository; 

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
		// 티어별 컨텐츠네임 리스트
		Integer start = contentRepository.findByOrderByContentTierAsc().get(0).getContentTier();
		Integer end = contentRepository.findByOrderByContentTierDesc().get(0).getContentTier();
		Map<Integer, List<String>> tierList = new HashMap<Integer, List<String>>();
		for (Integer i = start; i <= end; i++) {
			List<Content> contentList = contentRepository.findByContentTier(i);
			List<String> contentNameList = new ArrayList<String>();
			for (Content content : contentList) {
				contentNameList.add(content.getContentName());
			}
			tierList.put(i, contentNameList);		
		}

		// 티어리스트 돌면서 내용 정리
		Object result = new Object();
		Map<String, Object> doneChildMap = new HashMap<String, Object>();
		for (int i = end; i >= start; i--) {
			// 한 티어에 있는 애들 모으기
			Map<String, List<Map<String, Object>>> valueMap = new HashMap<String, List<Map<String, Object>>>();
			List<Map<String, Object>> valueList = new ArrayList<Map<String, Object>>();
			List<String> parentNameList = new ArrayList<String>();
			for (String contentName : tierList.get(i)) {
				Content content = contentRepository.findByContentName(contentName);
				parentNameList.add(content.getContentParent());
				if(doneChildMap.containsKey(contentName)){ // 만약에 내 이름된 doneChlid가 있다면 그놈을 그대로 사용
					valueList.add(Map.of(contentName, setModelInfoObject(1, doneChildMap.get(contentName))));
				}
				else{ // 내 이름으로 된 차일드가 없어서 빈 값으로 처리 = 마지막 녀석
					valueList.add(Map.of(contentName, setModelInfoObject(1, new ArrayList<String>()))); // 빈값으로 바디준비
				}
				
			}
			// 해당 티어 애들 모아서 부모 이름으로 된 맵 만듬
			for (int j = 0; j < valueList.size(); j++) {
				Map<String, Object> value = valueList.get(j);
				String parentName = parentNameList.get(j);
				if(!valueMap.containsKey(parentName)){ // 새로 만듬
					valueMap.put(parentName, new ArrayList<Map<String, Object>>());
					valueMap.get(parentName).add(value);
				}
				else{ // 기존 키에 값 넣음
					valueMap.get(parentName).add(value);
				}
				doneChildMap.put(parentName, valueMap.get(parentName));
			}
			if(i == start) result = valueMap;
		}
        var modelInfo = ModelInfo.of("dx100", result);
        modelInfoRepository.save(modelInfo);
		return result;
    }

	private Map<String, Object> setModelInfoObject(Integer status, Object list){
		return Map.of(
			"status", status,
			"value", list
        );
	}

	@Transactional
	@SuppressWarnings("unchecked")
    public Object modelInfoGet() {
		ObjectMapper objectMapper = new ObjectMapper();
		ModelInfo model = modelInfoRepository.findById(1L).get();
		Map<String, Object> structure = objectMapper.convertValue(model.getModelInfo(), HashMap.class); // content: {}
		List<Map<String, Object>> contentList = objectMapper.convertValue(structure.get("content"), List.class); // [gd:{}, mv:{}]
		System.out.println(contentList);
		for (Map<String,Object> map : contentList) {
			System.out.println(map.get("Guidance"));
			System.out.println(map.get("Model Viewer"));
		}
		return contentList;
    }
	
}
