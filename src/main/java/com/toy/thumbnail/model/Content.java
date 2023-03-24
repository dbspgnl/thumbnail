package com.toy.thumbnail.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Table @Getter @Setter @ToString @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Content {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentIndex;

    @Column
    private String contentName;
	
    @Column
    private int contentCount;
    
    @Column
    private int contentTier;

    @Column
    private String contentParent;
	
}
