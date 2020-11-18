package com.project.MatchingPro.controller;

import java.util.List;	

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.MatchingPro.domain.battle.Battle;
import com.project.MatchingPro.domain.party.PartyRepository;
import com.project.MatchingPro.domain.score.Score;
import com.project.MatchingPro.domain.score.ScoreRepository;
import com.project.MatchingPro.domain.team.TeamRepository;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.domain.user.UserRepository;
import com.project.MatchingPro.service.ScoreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ScoreController {
	
	private final ScoreRepository scoreRepository;
	private final ScoreService scoreService;
	private final HttpSession session;
	
	//로그인한 유저가 자기팀 승 등록 , 상대팀 패 등록
	@PutMapping("/user/scoreWiner/{battleid}")
	public ResponseEntity<?> scoreWiner(@PathVariable int battleid){
		System.out.println("우리팀이 이겼다 컨트롤러로 무사히 들어옴");
		User user = (User) session.getAttribute("principal");
		scoreService.scoreWin(user,battleid);
		scoreService.total(user,battleid);
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
	
	//로그인한 유저가 자기팀 무 등록 , 상대팀 무 등록
	@PutMapping("/user/scoreDraw/{battleid}")
	public ResponseEntity<?> scoreDraw(@PathVariable int battleid){
		User user = (User) session.getAttribute("principal");	
		scoreService.scoreDraw(user,battleid);
		scoreService.total2(user,battleid);
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
	//로그인한 유저가 자기팀 패 등록 , 상대팀 승 등록
	@PutMapping("/user/scoreLose/{battleid}")
	public ResponseEntity<?> scoreLose(@PathVariable int battleid){
		User user = (User) session.getAttribute("principal");	
		scoreService.scoreLose(user,battleid);
		scoreService.total3(user,battleid);
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
	
	//랭크 리스트<11/05>
	@GetMapping("/rank")
	public List<Score> rank(){
		return scoreRepository.rankAll();
	}
	
}
