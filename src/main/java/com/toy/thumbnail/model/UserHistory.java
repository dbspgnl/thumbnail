package com.toy.thumbnail.model;

import java.util.HashMap;
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
@Table(name = "user_histories")
public class UserHistory {
    
    @Id
    @GeneratedValue
    private Long id;
    
    // @Column(columnDefinition = "json")
    // MariaDB는 JSON 타입을 사용시 내부적으로 longtext 타입을 가집니다 그래서 columnDefinition을 longtext로 지정 해야 합니다
    // h2에서 할 때 둘 다 가능함. 
    @Type(type = "json")
    @Column(name = "histories", columnDefinition = "longtext")
    private Map<String, Object> history = new HashMap<>();
    
    public static UserHistory of(Map<String, Object> history) {
        return new UserHistory(null, history);
    }
}