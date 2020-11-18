package com.project.MatchingPro.domain.user;

import java.sql.Timestamp;	
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.MatchingPro.domain.battle.Battle;
import com.project.MatchingPro.domain.party.Party;
import com.project.MatchingPro.domain.team.Team;
import com.project.MatchingPro.domain.teamInfo.TeamInfo;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class User {
	@Id // 기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 해당 데이터베이스 번호증가 전략을 따라가기
	private int id;
	@Column(unique = true, nullable = false)
	private String loginid;
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(unique = true, nullable = false)
	private String nickname;
	private String email;
	private String phone;
	private String position;
	private String location;	
	@Column(length = 1000000000)
	private String image;
	private String role; // 권한
	@CreationTimestamp // default 현재시간 자동 적용
	private Timestamp joindate;

	@JsonIgnoreProperties({"partys"})
	@JoinColumn(name = "teams_id")
	@ManyToOne	
	private Team teams;
	
	//party에 fk키 있음 
	@JsonIgnoreProperties({"user","team"})
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)//LAZY
	private List<Party> partys;
	
	
	public String getDate() {

		Timestamp time = this.getJoindate();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(time);
	}
}
