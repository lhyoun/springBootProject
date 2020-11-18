package com.project.MatchingPro.controller;
			
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.MatchingPro.domain.team.Team;
import com.project.MatchingPro.domain.team.TeamRepository;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.service.TeamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TeamController {

	private final TeamService teamService;
	private final TeamRepository teamRepository;
	private final HttpSession session;
	
	//팀생성
	@PostMapping("/user/create")
	public ResponseEntity<?> create(@RequestBody Team team){
		User user = (User)session.getAttribute("principal");
		if(user.getTeams()!=null) {
			return new ResponseEntity<String>("팀이 이미 있습니다", HttpStatus.OK);
		}else {
		teamService.save(user, team);
		teamService.TeamsRegister(user, team.getId());
		 return new ResponseEntity<String>("ok", HttpStatus.OK);
		}
	}
	

	//팀 이름 중복체크
	@GetMapping("/check/{teamname}")
	public ResponseEntity<?> nameCheck(@PathVariable String teamname){
		return teamService.nameCheck(teamname);
	}
	
	//팀 상세보기
	@GetMapping("/teamDetail/{teamid}")
	public ResponseEntity<?> detail(@PathVariable int teamid){
		return teamService.detail(teamid);
	}
	
	//팀 가입 요청시 수락
	@PutMapping("/Acknowledgment/{partyid}")
	public ResponseEntity<?> Acknowledgment(@PathVariable int partyid){
		return teamService.teamJoin(partyid);
	}
	
	// 로그인한 유저의 팀 아이디값 리턴기능
	@GetMapping("/user/myTeam")
	public ResponseEntity<?> teamid(){
		User user = (User) session.getAttribute("principal");
		return teamService.myTeam(user);
	}
	
	
	//팀 리스트
	@GetMapping("/teamList")
	public List<Team> teamList(){
		return teamRepository.findAll();
	}

	
	//팀장 위임
	@PutMapping("/user/team/{userid}")
	public ResponseEntity<?> 위임(@PathVariable int userid){
		User user = (User) session.getAttribute("principal");
		return teamService.위임(user,userid);
	}
}

//<보류>
////팀삭제
//@DeleteMapping("/user/teamRemove")
//public ResponseEntity<?> teamDelete(){
//	User user = (User) session.getAttribute("principal");
//	return teamService.delete(user);
//}