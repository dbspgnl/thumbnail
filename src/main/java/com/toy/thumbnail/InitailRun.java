package com.toy.thumbnail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.toy.thumbnail.model.Content;
import com.toy.thumbnail.repo.ContentRepository;

@Component
public class InitailRun implements CommandLineRunner {
	
	@Autowired
	ContentRepository contentRepository; 

	@Override
	public void run(String... args) throws Exception {
		contentRepository.save(saveContent("Guidance", 1, "content"));
		contentRepository.save(saveContent("Model Viewer", 1, "content"));
		contentRepository.save(saveContent("TSG", 2, "Guidance"));
		contentRepository.save(saveContent("SSM", 2, "Guidance"));
		contentRepository.save(saveContent("PFT", 2, "Guidance"));
		contentRepository.save(saveContent("HYD", 2, "Model Viewer"));
		contentRepository.save(saveContent("ELEC", 2, "Model Viewer"));
		contentRepository.save(saveContent("ENG", 2, "Model Viewer"));
		contentRepository.save(saveContent("EPOS", 3, "TSG"));
		contentRepository.save(saveContent("ECU", 3, "TSG"));
		contentRepository.save(saveContent("TCU", 3, "TSG"));
	}

	private Content saveContent(String name, Integer tier, String parent){
		Content content = new Content();
			content.setContentCount(0);
			content.setContentName(name);
			content.setContentTier(tier);
			content.setContentParent(parent);
		return content;
	}
	
}
