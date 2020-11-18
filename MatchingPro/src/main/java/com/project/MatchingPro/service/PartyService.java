package com.project.MatchingPro.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.MatchingPro.domain.party.Party;
import com.project.MatchingPro.domain.party.PartyRepository;
import com.project.MatchingPro.domain.team.Team;
import com.project.MatchingPro.domain.team.TeamRepository;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PartyService {

	private final PartyRepository partyRepository;
	private final TeamRepository teamRepository;
	private final UserRepository userRepository;
	
	//개인-> 팀 가입 요청
	// user는 토큰으로 만든 완벽한 객체, id는 팀 아이디만, party는 빈 객체
	public ResponseEntity<?> solosave(User user, int teamid) {
		try {
			//가입요청중복방지
			if (partyRepository.countByUser_idAndTeam_id(user.getId(), teamid) == 0) {
				//팀이 없을때만 가입요청되게하기
				if (user.getTeams() == null) {
					Party party = new Party();
					party.setRoleNumber(0); // 개인 -> 팀 요청
					party.setUser(user);
					party.setTeam(teamRepository.findById(teamid)
							.orElseThrow(() -> new IllegalArgumentException(teamid + "는 존재하지 않습니다.")));
					partyRepository.save(party);
					return new ResponseEntity<String>("ok", HttpStatus.OK);
				//팀이있으면 메세지
				} else {
					return new ResponseEntity<String>("이미 가입한 팀이 있습니다", HttpStatus.OK);
				}
			//이미 신청했으면 메세지
			} else {
				return new ResponseEntity<String>("이미 신청했음", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("가입요청 실패", HttpStatus.EXPECTATION_FAILED);
		}
	}

	// 팀 -> 개인 초대
	public ResponseEntity<?> teamsave(User user, int userid) {
		try {
			//가입요청중복방지
			if (partyRepository.countByUser_idAndTeam_id(userid, user.getTeams().getId()) == 0) {
				//팀이 없을때만 초대되게하기
				if (userRepository.findById(userid).get().getTeams() == null) {
					Team team = teamRepository.findById(user.getTeams().getId())
							.orElseThrow(() -> new IllegalArgumentException(userid + "는 존재하지 않습니다."));
					User userEntity = userRepository.findById(userid)
							.orElseThrow(() -> new IllegalArgumentException(userid + "는 존재하지 않습니다."));
					Party party = new Party();
					party.setRoleNumber(1); // 팀 -> 개인 요청
					party.setUser(userEntity);
					party.setTeam(team);
					partyRepository.save(party);
					return new ResponseEntity<String>("ok", HttpStatus.OK);
					//팀이있으면 메세지
				} else {
					return new ResponseEntity<String>("이미 가입한 팀이있습니다", HttpStatus.OK);
				}
				//이미 신청했으면 메세지
			} else {
				return new ResponseEntity<String>("이미 신청했음", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("초대실패", HttpStatus.EXPECTATION_FAILED);
		}
	}

	//파티아이디값 받아와서 파티정보 리턴쓰
	public ResponseEntity<?> partyInfo(int partyid) {
		Party partyEntity = partyRepository.findById(partyid)
				.orElseThrow(() -> new IllegalArgumentException(partyid + "는 존재하지 않습니다."));
		return new ResponseEntity<Party>(partyEntity, HttpStatus.OK);
	}

}
