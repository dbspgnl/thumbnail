package com.toy.thumbnail.model;

import java.util.ArrayList;

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
import lombok.Setter;

@Getter
@Setter
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
    private Object modelInfo = new ArrayList<>();
    
    public static ModelInfo of(String name, Object modelInfo) {
        return new ModelInfo(null, name, modelInfo);
    }
	
}
