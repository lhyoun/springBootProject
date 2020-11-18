package com.project.MatchingPro.controller;

import java.util.List;	

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.MatchingPro.domain.mercenary.Mercenary;
import com.project.MatchingPro.domain.mercenary.MercenaryRepository;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.service.MercenaryService;


import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MercenaryController {
	
	private final MercenaryService mercenaryService;
	private final HttpSession session;
	private final MercenaryRepository mercenaryRepository;
	
	//용병 모집(모집창 만들기)
	@PostMapping("/user/recruitment")
	public ResponseEntity<?> recruitment(@RequestBody Mercenary mercenary){
		User user = (User)session.getAttribute("principal");
		return mercenaryService.recruitment(mercenary,user);
	}
	
	//용병 지원(지원하기 누르면 이거 실행)
	@PutMapping("/user/mercenaryApply/{mercid}")
	public ResponseEntity<?> mercenaryApply(@PathVariable int mercid){
		User user = (User)session.getAttribute("principal");
		return mercenaryService.mercenaryApply(user,mercid);
	}
	
	//용병모집 상세보기쓰(팀장이 누가 지원했는지 보는거)
	@PostMapping("/user/recruitment/info")
	public int recruitmentInfo() {
		User user = (User)session.getAttribute("principal");
		return mercenaryRepository.findByTeam_id(user.getTeams().getId());
	}
	
	//용병모집리스트
	@PostMapping("/recruitmentList")
	public List<Mercenary> recruitmentList(){
		return mercenaryRepository.findAll();
	}
	
	// 한 팀이 올린 용병모집리스트
	@PostMapping("/user/recruitmentList")
	public List<Mercenary> LoginrecruitmentList() {
		User user = (User)session.getAttribute("principal");
		return mercenaryRepository.mercenaryList(user.getTeams().getId());
	}
	
	//상세보기
	@PostMapping("/recruitmentDetail/{mercid}")
	public Mercenary recruitmentDetail(@PathVariable int mercid) {
		return mercenaryRepository.findById(mercid).get();
	}
}
