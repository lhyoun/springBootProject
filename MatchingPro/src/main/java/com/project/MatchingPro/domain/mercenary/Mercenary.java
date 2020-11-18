package com.project.MatchingPro.domain.mercenary;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.MatchingPro.domain.team.Team;
import com.project.MatchingPro.domain.user.User;

import lombok.Data;

@Data
@Entity
public class Mercenary {
	@Id // 기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 해당 데이터베이스 번호증가 전략을 따라가기
	private int id;
	private String title;
	private String content;
	private String gameDate;
	@CreationTimestamp 
	private Date createDate;
	private int role;
	
	
	@JsonIgnoreProperties({"score","owner"})
	@OneToOne
	private Team team;
	@JsonIgnoreProperties({"teams"})
	@OneToOne
	private User user1;
	@JsonIgnoreProperties({"teams"})
	@OneToOne
	private User user2;
	@JsonIgnoreProperties({"teams"})
	@OneToOne
	private User user3;
}
