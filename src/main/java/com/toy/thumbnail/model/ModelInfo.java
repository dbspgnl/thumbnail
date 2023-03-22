package com.toy.thumbnail.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@TypeDef(name = "json", typeClass = JsonType.class)
@Table(name = "model_info")
public class ModelInfo {

	@Id
    @GeneratedValue
    private Long id;

	@Column
	private String name;
    
    @Type(type = "json")
    @Column(name = "structure", columnDefinition = "longtext")
    private List<Map<String, Object>> modelInfo = new ArrayList<>();
    
    public static ModelInfo of(String name, List<Map<String, Object>> modelInfo) {
        return new ModelInfo(null, name, modelInfo);
    }
	
}
