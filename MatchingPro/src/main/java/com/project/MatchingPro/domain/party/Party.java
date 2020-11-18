package com.project.MatchingPro.domain.party;

import java.sql.Timestamp;	

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.project.MatchingPro.domain.team.Team;
import com.project.MatchingPro.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Party {
	@Id // 기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 해당 데이터베이스 번호증가 전략을 따라가기
	private int id;

	private int roleNumber; // 권한 / 팀:초대받은거, 개인:초대요청			domain : 1,2 // 1이면 개인이 팀한테 가입 신청 // 2면 팀이 개인한테 가입 요청
	
	@JoinColumn(name = "user_id")
	@ManyToOne
	private User user; // 신청 한 유저
	
	@JoinColumn(name = "team_id")
	@ManyToOne
	private Team team; // 신청 받은 팀
	@CreationTimestamp
	private Timestamp requestDate;	// 신청 들어온 시간
	
}

// 개인 -> 팀 요청보낸거만 처리하는 걸 목적으로



