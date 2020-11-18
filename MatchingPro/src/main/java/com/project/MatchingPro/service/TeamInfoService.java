package com.project.MatchingPro.service;

import org.springframework.http.HttpStatus;		
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.MatchingPro.domain.team.TeamRepository;
import com.project.MatchingPro.domain.teamInfo.TeamInfo;
import com.project.MatchingPro.domain.teamInfo.TeamInfoRepository;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamInfoService {
	
	private final TeamInfoRepository teamInfoRepository;
	private final UserRepository userRepository;
	
	
	public ResponseEntity<?> teamInfoRegister(TeamInfo teamInfo, User user){
	try {
		int userid2 = teamInfo.getUser2().getId();
		int userid3 = teamInfo.getUser3().getId();
		int userid4 = teamInfo.getUser4().getId();
		int userid5 = teamInfo.getUser5().getId();
		int userid6 = teamInfo.getUser6().getId();
		int userid7 = teamInfo.getUser7().getId();
		int userid8 = teamInfo.getUser8().getId();
		int userid9 = teamInfo.getUser9().getId();
		int userid10 = teamInfo.getUser10().getId();
		int userid11 = teamInfo.getUser11().getId();
		
		TeamInfo teamInfoEntity = new TeamInfo();
		teamInfoEntity.setTeam(user.getTeams());
		teamInfoEntity.setUser1(user);
		teamInfoEntity.setUser2(userRepository.findById(userid2).orElseThrow(()-> new IllegalArgumentException(userid2+"는 존재하지 않습니다.")));
		teamInfoEntity.setUser3(userRepository.findById(userid3).orElseThrow(()-> new IllegalArgumentException(userid3+"는 존재하지 않습니다.")));
		teamInfoEntity.setUser4(userRepository.findById(userid4).orElseThrow(()-> new IllegalArgumentException(userid4+"는 존재하지 않습니다.")));
		teamInfoEntity.setUser5(userRepository.findById(userid5).orElseThrow(()-> new IllegalArgumentException(userid5+"는 존재하지 않습니다.")));
		teamInfoEntity.setUser6(userRepository.findById(userid6).orElseThrow(()-> new IllegalArgumentException(userid6+"는 존재하지 않습니다.")));
		teamInfoEntity.setUser7(userRepository.findById(userid7).orElseThrow(()-> new IllegalArgumentException(userid7+"는 존재하지 않습니다.")));
		teamInfoEntity.setUser8(userRepository.findById(userid8).orElseThrow(()-> new IllegalArgumentException(userid8+"는 존재하지 않습니다.")));
		teamInfoEntity.setUser9(userRepository.findById(userid9).orElseThrow(()-> new IllegalArgumentException(userid9+"는 존재하지 않습니다.")));
		teamInfoEntity.setUser10(userRepository.findById(userid10).orElseThrow(()-> new IllegalArgumentException(userid10+"는 존재하지 않습니다.")));
		teamInfoEntity.setUser11(userRepository.findById(userid11).orElseThrow(()-> new IllegalArgumentException(userid11+"는 존재하지 않습니다.")));
		
		System.out.println("성공");		
		teamInfoRepository.save(teamInfoEntity);
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}catch(Exception e) {
		e.printStackTrace();
		System.out.println("실패");
		return new ResponseEntity<String>("no",HttpStatus.OK);
	}
	}
	
}
