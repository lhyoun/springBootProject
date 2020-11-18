package com.project.MatchingPro.service;

import org.springframework.http.HttpStatus;	
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.MatchingPro.domain.battle.Battle;
import com.project.MatchingPro.domain.battle.BattleRepository;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScoreService {

	private final BattleRepository battleRepository;
	private final UserRepository userRepository;
	
	
	//로그인한 유저가 자기팀 승 등록 , 상대팀 패 등록
	@Transactional
	public ResponseEntity<?> scoreWin(User user, int battleid){
		Battle battleEntity=battleRepository.findById(battleid).get();
		battleEntity.getRequestTeam().getScore().setWin(user.getTeams().getScore().getWin()+1);
		battleEntity.setWinerTeam(user.getTeams());
		battleEntity.getResponseTeam().getScore().setLose(battleEntity.getResponseTeam().getScore().getLose()+1);
		//승무패 변경하고 배틀테이블 삭제
		//battleRepository.deleteById(battleid);
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
	//점수계산<11/05>
	@Transactional
	public void total(User user, int battleid) {
		int wintotal = (user.getTeams().getScore().getWin()+1)*3;
		int Drawtotal = user.getTeams().getScore().getDraw()*1;
		int LoseTotal=user.getTeams().getScore().getLose()*(-2);
		User userEntity = userRepository.findById(user.getId()).get();
		userEntity.getTeams().getScore().setTotal(wintotal+Drawtotal+LoseTotal);
		
		Battle battleEntity=battleRepository.findById(battleid).get();
		int wintotal2 = (battleEntity.getResponseTeam().getScore().getWin())*3;
		int Drawtotal2 =(battleEntity.getResponseTeam().getScore().getDraw())*1;
		int LoseTotal2=(battleEntity.getResponseTeam().getScore().getLose())*2;
		System.out.println((user.getTeams().getScore().getLose()+1)*2 + "zzxzxz");

		battleEntity.getResponseTeam().getScore().setTotal(wintotal2+Drawtotal2-LoseTotal2);
	}
	//로그인한 유저가 자기팀 무 등록 , 상대팀 무 등록
	@Transactional
	public ResponseEntity<?> scoreDraw(User user, int battleid){
		Battle battleEntity=battleRepository.findById(battleid).get();
		battleEntity.getRequestTeam().getScore().setDraw(user.getTeams().getScore().getDraw()+1);
		battleEntity.getResponseTeam().getScore().setDraw(battleEntity.getResponseTeam().getScore().getDraw()+1);
		//승무패 변경하고 배틀테이블 삭제
		//battleRepository.deleteById(battleid);
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
	//점수계산<11/05>
	@Transactional
	public void total2(User user, int battleid) {
		int wintotal = user.getTeams().getScore().getWin()*3;
		int Drawtotal =( user.getTeams().getScore().getDraw()+1)*1;
		int LoseTotal=user.getTeams().getScore().getLose()*(-2);
		User userEntity = userRepository.findById(user.getId()).get();
		userEntity.getTeams().getScore().setTotal(wintotal+Drawtotal+LoseTotal);
		
		Battle battleEntity=battleRepository.findById(battleid).get();
		int wintotal2 = (battleEntity.getResponseTeam().getScore().getWin())*3;
		int Drawtotal2 =(battleEntity.getResponseTeam().getScore().getDraw())*1;
		int LoseTotal2=(battleEntity.getResponseTeam().getScore().getLose())*2;
		System.out.println((user.getTeams().getScore().getLose()+1)*2 + "zzxzxz");

		battleEntity.getResponseTeam().getScore().setTotal(wintotal2+Drawtotal2-LoseTotal2);
	}
	//로그인한 유저가 자기팀 패 등록 , 상대팀 승 등록
	@Transactional
	public ResponseEntity<?> scoreLose(User user, int battleid){
		Battle battleEntity=battleRepository.findById(battleid).get();
		battleEntity.getRequestTeam().getScore().setLose(user.getTeams().getScore().getLose()+1);
		battleEntity.getResponseTeam().getScore().setWin(battleEntity.getResponseTeam().getScore().getWin()+1);
		battleEntity.setWinerTeam(battleEntity.getResponseTeam());
		//승무패 변경하고 배틀테이블 삭제
		//battleRepository.deleteById(battleid);
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
	//점수계산<11/05>
	@Transactional
	public void total3(User user, int battleid) {
		int wintotal = (user.getTeams().getScore().getWin())*3;
		int Drawtotal = user.getTeams().getScore().getDraw()*1;
		int LoseTotal=(user.getTeams().getScore().getLose()+1)*(-2);
		User userEntity = userRepository.findById(user.getId()).get();
		userEntity.getTeams().getScore().setTotal(wintotal+Drawtotal+LoseTotal);
		
		Battle battleEntity=battleRepository.findById(battleid).get();
		int wintotal2 = (battleEntity.getResponseTeam().getScore().getWin())*3;
		int Drawtotal2 =(battleEntity.getResponseTeam().getScore().getDraw())*1;
		int LoseTotal2=(battleEntity.getResponseTeam().getScore().getLose())*2;
		System.out.println((user.getTeams().getScore().getLose()+1)*2 + "zzxzxz");

		battleEntity.getResponseTeam().getScore().setTotal(wintotal2+Drawtotal2-LoseTotal2);
	}
	

}
