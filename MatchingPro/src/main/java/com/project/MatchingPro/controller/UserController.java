package com.project.MatchingPro.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.multipart.MultipartFile;

import com.project.MatchingPro.domain.party.Party;
import com.project.MatchingPro.domain.party.PartyRepository;
import com.project.MatchingPro.domain.team.Team;
import com.project.MatchingPro.domain.team.TeamRepository;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.domain.user.UserRepository;
import com.project.MatchingPro.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController { // app,web 둘다적용

	private final UserService userService;
	private final HttpSession session;
	private final UserRepository userRepository;
	private final TeamRepository teamRepository;
	private final PartyRepository par;

	// 개인이 팀이 보낸 파티요청 수락하기
	@Transactional
	@PutMapping("/acceptrequest/{partyid}")
	public ResponseEntity<?> acceptrequest(@PathVariable int partyid) {
		System.out.println("controller들어옴 with narae");
		// 컨트롤러가 가지고있는 정보는 파티 아이디밖에 없다
		// 이 파티를 수락하는 애가 누군지 모르지
		// 로그인애지 ? 그러면 토큰 보내면 내가 누군지 알 수 있고
		// 그애 파티아이디에 있는 팀 아이디를 세팅
		// 파티아이디 있지

		System.out.println("파티아이디" + partyid);
		Party partyEntity = par.findById(partyid).get();

		int teamId = partyEntity.getTeam().getId();
		int userId = partyEntity.getUser().getId();

		System.out.println("팀 아이디" + teamId);
		System.out.println("유저 아이디" + userId);

		// User user = userRepository.findById(id);

		User user = userRepository.findById(userId).get();
		Team team = teamRepository.findById(teamId).get();
		user.setTeams(team);

		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}

	// 회원가입
	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody User user) {
		return userService.join(user);
	}

	// id 중복체크
	@GetMapping("/idCheck/{loginid}")
	public ResponseEntity<?> idCheck(@PathVariable String loginid) {
		return userService.idCheck(loginid);
	}

	// 유저 상세보기
	@GetMapping("/userDetail/{userid}")
	public ResponseEntity<?> detail(@PathVariable int userid) {
		return userService.detail(userid);
	}

	// 닉네임 중복체크
	@GetMapping("/nicknameCheck/{nickname}")
	public ResponseEntity<?> nicknameCheck(@PathVariable String nickname) {
		return userService.nicknameCheck(nickname);
	}

	// 로그아웃
	@GetMapping("/logout")
	public ResponseEntity<?> logout() {
		session.removeAttribute("principal");
		// session.invalidate(); //모든 세션 정보 삭제
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}

	// 유저리스트
	@GetMapping("/userList")
	public List<User> list() {
		return userRepository.findAll();
	}

	// 팀없는 유저리스트
	@GetMapping("/notTeamUserList")
	public List<User> notlist() {
		return userRepository.mfindAll();
	}

	// 회원탈퇴<11/05>
	@DeleteMapping("/user/userRemove")
	public ResponseEntity<?> userDelete() {
		User user = (User) session.getAttribute("principal");
		return userService.delete(user);
	}

	// 닉네임으로 유저정보 검색<11/05>
	@GetMapping("/nicknameDetail/{nickname}")
	public User userDetail(@PathVariable String nickname) {
		return userRepository.findByNickname(nickname);
	}

	// 로그인 한놈의 유저 아이디<11/05>
	@GetMapping("/user/loginid")
	public int loginid() {
		User user = (User) session.getAttribute("principal");
		return user.getId();
	}

	// 팀탈퇴<11/05>
	@PutMapping("/user/teamRemove")
	public ResponseEntity<?> teamRemove() {
		User user = (User) session.getAttribute("principal");
		return userService.teamLeave(user);
	}
}
