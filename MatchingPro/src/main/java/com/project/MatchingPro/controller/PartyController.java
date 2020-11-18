package com.project.MatchingPro.controller;

import java.util.List;		

import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.MatchingPro.domain.party.Party;
import com.project.MatchingPro.domain.party.PartyRepository;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.service.PartyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PartyController {

	private final PartyRepository partyRepository;
	private final HttpSession session;
	private final PartyService partyService;

	// 개인 -> 팀 에 가입 신청
	@PostMapping("/user/apply1/{teamid}")
	public ResponseEntity<?> soloApply(@PathVariable int teamid) {
		User user = (User) session.getAttribute("principal");
		return partyService.solosave(user, teamid);
	}
	
	//팀 -> 개인 에 가입요청
	@PostMapping("/user/apply2/{userid}")
	public ResponseEntity<?>  ownerApply(@PathVariable int userid){
		User user = (User) session.getAttribute("principal");
		return partyService.teamsave(user, userid);
	}
	
	//파티신청 리스트<임시로만듬>
	@GetMapping("/partyList")
	public List<Party> party(){
		return partyRepository.findAll();
	}
	
	//파티아이디값 받아와서 파티정보 리턴쓰<하윤부탁>
	@GetMapping("/user/partyInfo/{partyid}")
	public ResponseEntity<?> partyInfo(@PathVariable int partyid){
		return partyService.partyInfo(partyid);
	}
	
	//팀 아이디 받아와서 해당 팀에 대한 파티 리스트 뿌리기<11/05,하윤부탁>
	@GetMapping("/user/teamParty/{teamid}")
	public List<Party> teamPartyinfo(@PathVariable int teamid){
		return partyRepository.mFindByTeamid(teamid);
	}
	
	//유저아이디 받아와서 파티리스트 뿌리기<11/06,하윤>
	@PostMapping("/user/partyList/{userid}")
	public List<Party> partyList_user(@PathVariable int userid) {
	return partyRepository.findUser_idAll(userid);
	}
}
