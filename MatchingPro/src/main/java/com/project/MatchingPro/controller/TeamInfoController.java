package com.project.MatchingPro.controller;

import javax.servlet.http.HttpSession;	

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.MatchingPro.domain.teamInfo.TeamInfo;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.service.TeamInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TeamInfoController {

	private final TeamInfoService teamInfoService;
	private final HttpSession session;
	
	//매칭하기(상대방이 수락하는것도 똑같은 방법) 눌렀을 때 팀과 참가하는 유저 등록
	// 유저아이디를 teamInfo에 저장하는 컨트롤러
	@PostMapping("/user/teamInfo")
	public ResponseEntity<?> teamInfoRegister(@RequestBody TeamInfo teamInfo) {
		User user = (User)session.getAttribute("principal");
		System.out.println("controller 들어옴");
		//System.out.println(teamInfo.getUser1());
		return teamInfoService.teamInfoRegister(teamInfo, user);
	}
}
