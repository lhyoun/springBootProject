package com.project.MatchingPro.service;
	
import org.springframework.http.HttpStatus;	
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.MatchingPro.domain.party.Party;
import com.project.MatchingPro.domain.party.PartyRepository;
import com.project.MatchingPro.domain.score.Score;
import com.project.MatchingPro.domain.score.ScoreRepository;
import com.project.MatchingPro.domain.team.Team;
import com.project.MatchingPro.domain.team.TeamRepository;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamService {

	private final TeamRepository teamRepository;
	private final UserRepository userRepository;
	private final PartyRepository partyRepository;
	private final ScoreRepository scoreRepository;
	
	// 팀 생성
		public ResponseEntity<?> save(User user, Team team) {
			try {
				//팀 생성하면서 장동으로 스코어 0승0무0패 설정
					Score score = new Score();
					score.setWin(0);
					score.setDraw(0);
					score.setLose(0);
					score.setTotal(0);
					//score.setTeam(team);
					scoreRepository.save(score);
					team.setScore(score);
					team.setOwner(user);
					teamRepository.save(team);
					// 여기서 그 쿼리 써서 팀에 teamId를 넣으면
					//  유저의 팀에 team id가 들어가니까 

					System.out.println("팀 생성 성공");
					
					return new ResponseEntity<String>("ok", HttpStatus.OK);

			} catch (Exception e) {
				System.out.println("팀생성 실패");
				return new ResponseEntity<String>("no",HttpStatus.OK);
			}
		}
	
	@Transactional
	public void TeamsRegister(User user, int teamId) {
		User realUser = userRepository.findById(user.getId()).orElseThrow(()-> new IllegalArgumentException(teamId+"는 존재하지 않습니다."));
		realUser.setTeams(teamRepository.findById(teamId).get());
		teamRepository.findById(teamId).get().getScore().setTeam(teamRepository.findById(teamId).get());
	}
		
	
	//팀 가입 요청시 수락
	@Transactional
	public ResponseEntity<?> teamJoin(int partyid){
		Party partyEntity = partyRepository.findById(partyid).orElseThrow(()-> new IllegalArgumentException(partyid+"는 존재하지 않습니다."));
		//만약 팀장 가입수가 20명이상이면 '팀원이 가득찼십니더' 메세지 출력함.<11/06>
		if(partyEntity.getTeam().getUsers().size() <= 20) {
			User userEntity = partyEntity.getUser();
			userEntity.setTeams(partyEntity.getTeam());
			//수락을 했으면 해당 유저아이디가 있는 파티테이블 전부 삭제<11/06>
			partyRepository.deleteParty(partyEntity.getUser().getId());
				return new ResponseEntity<String>("ok", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("팀원이 가득찼십니더 ", HttpStatus.OK);
		}
	}
	
	
	//팀상세보기
	public ResponseEntity<?> detail(int id){
		Team team = teamRepository.findById(id).get();
		
		return new ResponseEntity<Team>(team ,HttpStatus.OK);
	}
	

	//팀이름 체크
	public ResponseEntity<?> nameCheck(String name){
		int n = teamRepository.countByName(name);
		if(n == 0) {
			return new ResponseEntity<String>("ok", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("no", HttpStatus.OK);
		}
	}
	
	// 로그인한 유저의 팀 아이디값 리턴기능
	public ResponseEntity<?> myTeam(User user){
		
		return new ResponseEntity<Integer>(user.getTeams().getId(),HttpStatus.OK);
		
	}
	

	
	//팀장위임
	@Transactional
	public ResponseEntity<?> 위임(User user, int userid){
		Team teamEntity=teamRepository.findById(user.getTeams().getId()).get();
		teamEntity.setOwner(userRepository.findById(userid).get());
		//user.getTeams().setOwner(userRepository.findById(userid).get());
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	} 
}

////팀 삭제
//@Transactional
//public ResponseEntity<?> delete(User user){
//	if(user.getTeams() != null) {
//		scoreRepository.deleteById(user.getTeams().getScore().getId());
//		teamRepository.deleteById(user.getTeams().getId());
//	return new ResponseEntity<String>("팀을 탈퇴",HttpStatus.OK);
//	} else {
//	user.getTeams().setOwner(null);
//	//user.setTeams(null);
//	scoreRepository.deleteById(user.getTeams().getScore().getId());
//	teamRepository.deleteById(user.getTeams().getId());
//	return new ResponseEntity<String>("팀있음",HttpStatus.OK);
//	}
//}