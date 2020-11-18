package com.project.MatchingPro.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.MatchingPro.domain.battle.Battle;
import com.project.MatchingPro.domain.battle.BattleRepository;
import com.project.MatchingPro.domain.team.TeamRepository;
import com.project.MatchingPro.domain.teamInfo.TeamInfo;
import com.project.MatchingPro.domain.teamInfo.TeamInfoRepository;
import com.project.MatchingPro.domain.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BattleService {
	private final BattleRepository battleRepository;
	private final TeamInfoRepository teamInfoRepository;
	private final TeamRepository teamRepository;

	// teamInfo 아이디를 받아와서 베틀(매칭) 테이블에 저장
	public ResponseEntity<?> matchApply(Battle battle, int teamid, User user) {
		TeamInfo teamInfoEntity = teamInfoRepository.mFindByTeamid(user.getTeams().getId());
		battle.setTeamInfo1(teamInfoEntity);
		battle.setRole(1);
		battle.setRequestTeam(user.getTeams());
		battle.setResponseTeam(teamRepository.findById(teamid).get());
		battleRepository.save(battle);

		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
	// 상대방이 수락시 팀인포에 팀이랑 나갈인원넣고 베틀테이블에 추가
	@Transactional
	public ResponseEntity<?> matchAccept(User user, int battleid) {
		Battle battleEntity = battleRepository.findById(battleid).get();
		battleEntity.setTeamInfo2(teamInfoRepository.mFindByTeamid(user.getTeams().getId()));
		battleEntity.setRole(2);
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
}
