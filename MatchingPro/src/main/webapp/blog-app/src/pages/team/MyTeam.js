import React, { useState, useEffect } from 'react';
import { Container, Jumbotron, Button, Form, FormControl, Row, Col, Modal } from 'react-bootstrap';
import styled from 'styled-components';
import Team_schedule from './Team_schedule';
import SpanTagStyle from '../constant/SpanTagStyle';
import { Link } from 'react-router-dom';

const MainCardStyle = styled.div`
  width: 100%;
  margin: auto;
`;

const SlideStyle = styled.div`
	margin-top:15%;
	margin-bottom:4%;
`;

const MyTeam = () => {

	const id = 1;
	const url2 = "/Team_schedule/" + id;

	const inputHandle = (e) => {
		setSearchUser({
			...searchUser,
			[e.target.name]: e.target.value,
		});
	};

	// 여기부터 state ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- 

	// 아래 3개의 state - team datail info fetch에서 받아옴
	const [team, setTeam] = useState([]);
	const [owner, setOwner] = useState([]);
	const [users, setUsers] = useState([]);
	const { explaintation, name } = team;

	const [partys, setPartys] = useState([]);
	const [battles, setBattles] = useState([]);
	const [schedule, setSchedule] = useState([]);
	// 이렇게 위로 6개 state useEffect fetch에서 받아옴

	// modal state and function. show : 팀원 초대 / show2 : 경기 상세정보 page
	const [show, setShow] = useState(false);
	const handleClose = () => setShow(false);
	const handleShow = () => setShow(true);


	const [battleIdInModal, setBattleIdInModal] = useState(0);
	const [battleIdInModalData, setBattleIdInModalData] = useState({});

	const [show3, setShow3] = useState(false);
	const handleClose3 = () => setShow3(false);
	const handleShow3 = (id) => {
		battles.map((res) => (
			res.id === id
				?
				setBattleIdInModalData(res)
				: null
		))
		setBattleIdInModal(id);
		setShow3(true);
	};

	const [show2, setShow2] = useState(false);
	const handleClose2 = () => setShow2(false);
	const handleShow2 = (id, myTeam, jteam, location, matchDate) => {
		setShow2(true);
		setDeId({
			id: id,
			myTeam: myTeam,
			jteam: jteam,
			location: location,
			matchDate: matchDate
		});
	};

	//console.log("myteam",deId.myTeam);
	//console.log("realmyteam",name);
	

	// 팀원 초대할 때 사용되는 state. 아래 status는 검색 결과가 적합한 지 check하는 용도
	const [searchUser, setSearchUser] = useState({
		nickname: "",
		location: "",
		position: "",
		id: null,
	});

	const [searchUserStatue, setSearchUserStatus] = useState("enter nickname and search!");

	const [isSearch, setIsSearch] = useState(false);

	const [deId, setDeId] = useState({
		id: null,
		myTeam: null,
		jteam: null,
		location: null,
		matchDate: null
	}); // 진행하기로 결정 된 경기 자세히 보기 모달에 가져갈 data

	// 팀원 선택 state
	const [rteam, setRteam] = useState(["팀장"]);

	const rteamplus = (id) => {
		alert("추가되었습니다");
		setRteam([
			...rteam,
			id
		]);
		console.log(rteam);
	}

	const memberCheck = () => {
		alert(rteam);
	}
	// 여기가지 state ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- 

	const inviteMember = (userid) => {	// 팀원 초대 함수 (검색 다음 초대)
		fetch(`http://localhost:8000/user/apply2/${userid}`, {
			method: "post",
			headers: {
				'Content-Type': "application/json; charset=utf-8",
				'Authorization': localStorage.getItem("Authorization")
			}
		}).then((res) => res.text())
			.then(res => {
				if (res === "ok") {
					handleClose();
					alert("팀가입 요청 완료");
				}
				else alert("팀가입 요청 실패");
			});
	};

	const searchUserfunction = (nick) => {	// 팀원 검색 함수 (검색 다음 초대)
		alert(nick + " 검색되었습니다");
		// fetch로 검색해서 searchUser에 넣기
		fetch(`http://localhost:8000/nicknameDetail/${nick}`, {
			method: "get",
		}).then((res) => {
			if (res.status === 200) {
				setSearchUserStatus("search complete");
				return res.json()
			}
			else {
				setSearchUserStatus("please check nickname");
				return searchUser;
			}
		})
			.then(res => {
				console.log("닉네임으로 검색 결과", res);
				setSearchUser(res);
			});
		setIsSearch(true);
	}

	// 새로 고침없이 바로 수정되게 하려면 state 등록해야함 / 귀찮으니까 나중에 / 팀 가입 요청 온거 수락하기
	const joinTeamReq = (partyid) => {
		fetch(`http://localhost:8000/Acknowledgment/${partyid}`, {
			method: "put",
		}).then((res) => res.text())
			.then((res) => {
				alert(res);
			});
	};

	// ----- ----- ----- ----- -----  

	const matchAccept = (battleid) => {	// 베틀 신청에 대한 수락 fetch
		fetch(`http://localhost:8000/user/matchAccept/${battleid}`, {
			method: "put",
			headers: {
				'Authorization': localStorage.getItem("Authorization")
			}
		}).then((res) => res.text())
			.then((res) => {
				alert(res);
			});
	};

	// teaminfo create & 베틀 수락 포함
	const createInfo = () => {
		let teamInfo = {
			/* loginid: user.loginid,
			password: user.password */
			//user1: {id: 1},
			user2: { id: rteam[1] },
			user3: { id: rteam[2] },
			user4: { id: rteam[3] },
			user5: { id: rteam[4] },
			user6: { id: rteam[5] },
			user7: { id: rteam[6] },
			user8: { id: rteam[7] },
			user9: { id: rteam[8] },
			user10: { id: rteam[9] },
			user11: { id: rteam[10] },
			/* user2: { id: 1 },
			user3: { id: 1 },
			user4: { id: 1 },
			user5: { id: 1 },
			user6: { id: 1 },
			user7: { id: 1 },
			user8: { id: 1 },
			user9: { id: 1 },
			user10: { id: 1 },
			user11: { id: 1 }, */
		}
		fetch(`http://localhost:8000/user/teamInfo`, {
			method: "post",
			body: JSON.stringify(teamInfo),
			headers: {
				'Content-Type': "application/json; charset=utf-8",
				'Authorization': localStorage.getItem("Authorization")
			}
		}).then((res) => res.text())
			.then(res => {
				if (res === "ok") matchAccept(battleIdInModal);
				else alert("요청 실패");
			});
	};

	// ----- ----- -----

	// 우리팀 이겼다 선택
	const win = () => {
		fetch(`http://localhost:8000/user/scoreWiner/${deId.id}`, {
			method: "put",
			headers: {
				//'Content-Type': "application/json; charset=utf-8",
				'Authorization': localStorage.getItem("Authorization")
			}
		}).then((res) => res.text())
			.then(res => {
				console.log(res);
				if (res === "ok") {
					handleClose2();
					alert("완료");
				}
				else alert("실패");
			});
	}

	// 상대팀 이겼다 선택
	const lose = () => {
		fetch(`http://localhost:8000/user/scoreLose/${deId.id}`, {
			method: "put",
			headers: {
				//'Content-Type': "application/json; charset=utf-8",
				'Authorization': localStorage.getItem("Authorization")
			}
		}).then((res) => res.text())
			.then(res => {
				console.log(res);
				if (res === "ok") {
					handleClose2();
					alert("완료");
				}
				else alert("실패");
			});
	}

	const rjwjf = () => {
		alert("거절되었습니다");
	}
	useEffect(() => {
		// 현재 로그인 되어있는 ID가 가입한 팀의 ID를 받아옴
		fetch("http://localhost:8000/user/myTeam", {
			method: "get",
			headers: {
				'Authorization': localStorage.getItem("Authorization")
			}
		}).then((res) => res.text())
			.then((res) => {
				console.log("MyTeamForm (login ID)Team Id : ", res);	// res : Team ID

				fetch(`http://localhost:8000/teamDetail/${res}`, {	// 팀 상세 정보 받아옴
					method: "get",
				}).then((res) => res.json())
					.then((res) => {
						console.log("MyTeamForm team detail info (json type) ", res);
						setTeam(res);
						setOwner(res.owner);
						setUsers(res.users);
					});

				fetch(`http://localhost:8000/user/teamParty/${res}`, {	// 팀 가입신청 list 받아옴
					method: "get",
					headers: {
						'Authorization': localStorage.getItem("Authorization")
					}
				}).then((res) => res.json())
					.then((res) => {
						console.log("MyTeamForm partyList (json type) ", res);
						setPartys(res);
					});

				fetch(`http://localhost:8000/user/loginBattleList`, {	// 로그인 한 유저 팀의 베틀 lsit 받아옴
					method: "get",
					headers: {
						'Authorization': localStorage.getItem("Authorization")
					}
				}).then((res) => res.json())
					.then((res) => {
						console.log("MyTeamForm battle list (json type) ", res);
						setBattles(res);
					});

				fetch(`http://localhost:8000/battleList/${res}`, {		// 팀 경기 관련 list 받아옴
					method: "get",
				}).then((res) => res.json())
					.then((res) => {
						console.log("team schedule fetch second then res", res);
						setSchedule(res);
					});
			});
	}, []);

	return (
		<Container>
			{/* 팀원 초대 모달 */}
			<Modal show={show} size={"lg"} onHide={handleClose}>
				<Modal.Header closeButton>
					<Modal.Title>팀원초대</Modal.Title>
				</Modal.Header>
				<Modal.Body>
					<Form.Row>
						<Col md={2}></Col>
						<Form.Group as={Col} md={5} controlId="formGridEmail">
							<Form.Label>nickname</Form.Label>
							<Row>
								<Col md={10}>
									<Form.Control
										type="text"
										name="nickname"
										placeholder="nickname"
										onChange={inputHandle}
										value={searchUser.nickname} />
								</Col>
								<Col md={2}>
									<Button variant="secondary" onClick={() => searchUserfunction(searchUser.nickname)}>search</Button>
								</Col>
								<Col md={12}><br /></Col>
								<Col md={12}><SpanTagStyle msg={searchUserStatue}></SpanTagStyle></Col>
							</Row>
						</Form.Group>
					</Form.Row>
				</Modal.Body>
				<Modal.Footer>
					<Button variant="secondary" onClick={() => inviteMember(searchUser.id)}>초대하기</Button>
					<Button variant="secondary" onClick={handleClose}>Close</Button>
				</Modal.Footer>
			</Modal>

			{/* 경기 요청 자세히 보기(+팀원입력 및 수락) 모달 */}
			<Modal show={show3} size={"lg"} onHide={handleClose3}>
				<Modal.Header closeButton>
					<Modal.Title>대전요청 자세히보기</Modal.Title>
				</Modal.Header>
				<Modal.Body>
					<Row>
						<Col md={6}>📍{battleIdInModalData.location}</Col>
						<Col md={6}>🕰{battleIdInModalData.matchDate}</Col>
					</Row>
					<hr />
					<SpanTagStyle msg="게임에 참여할 팀원을 선택해 주세요"></SpanTagStyle>
					<hr />
					<Row>
						{users.map((res) => (//이 팀에 들어온 파티 번호 : {res.id}
							<Col md={3}>🏃 {res.nickname}&nbsp;&nbsp;&nbsp;
								<Button onClick={() => rteamplus(battleIdInModal)} size="sm" variant="outline-secondary">추가</Button></Col>
						))}
					</Row>
					<br />
					<Button onClick={memberCheck} size="sm" variant="outline-secondary">선택된 팀원 확인</Button>
				</Modal.Body>
				<Modal.Footer>
					<Button onClick={() => createInfo()} variant="outline-secondary">수락하기</Button>
					{/* createInfo 안에 베틀수락함수 포함 */}
					<Button variant="secondary" onClick={handleClose3}>Close
					</Button>
				</Modal.Footer>
			</Modal >

			{/* 경기 자세히 보기(+결과입력) 모달 */}
			<Modal show={show2} size={"lg"} onHide={handleClose2}>
				<Modal.Header closeButton>
					<Modal.Title>경기</Modal.Title>
				</Modal.Header>
				<Modal.Body>
					{/* <MatchingCard></MatchingCard> */}
					<Row>
						<Col md={6}>
							<SpanTagStyle imt="📍 " msg={deId.location}></SpanTagStyle>
						</Col>
						<Col md={6}>
							<SpanTagStyle imt="🕰 " msg={deId.matchDate}></SpanTagStyle>
						</Col>
					</Row>
					<hr />
					<Row>
						<Col md={4}>
							<SpanTagStyle msg="승리팀을 선택해주세요!"></SpanTagStyle>
						</Col>
						<Col md={3}>
							<Button variant="outline-secondary" onClick={win}>{deId.myTeam}</Button>
						</Col>
						<Col md={3}>
							<Button variant="outline-secondary" onClick={lose}>{deId.jteam}</Button>
						</Col>
						{/* <Button variant="secondary" onClick={as}>{deId.id}</Button> */}
					</Row>
				</Modal.Body>
				<Modal.Footer>
					<Button variant="secondary" onClick={handleClose2}>
						Close
					</Button>
				</Modal.Footer>
			</Modal>

			{/* page 첫 번째 박스 */}
			<SlideStyle>
				<MainCardStyle>
					<Jumbotron>
						<Row>
							<Col md={10}><h1>⚽ {name}</h1></Col>
							<Col md={2}>
								<Button onClick={handleShow} variant="outline-secondary">
									<SpanTagStyle msg="팀원초대"></SpanTagStyle></Button>
							</Col>
							<Col md={12}><hr /></Col>

							<Col md={3}><h5>👑 {owner.nickname}</h5></Col>
							<Col md={9}><h5>📄 {explaintation}</h5></Col>
							<Col md={12}><hr /></Col>

							<Col md={3}><h3>🏃‍♀️ Member</h3></Col>
							<Col md={9}></Col>
							<Col md={12}><br /></Col>
							{users.map((res) => (//이 팀에 들어온 파티 번호 : {res.id}
								<Col md={3}>🏃 {res.nickname}</Col>
								/* 								<Col md={3}>🏃 {res.username}</Col> */
							))}


						</Row>
					</Jumbotron>
				</MainCardStyle>
			</SlideStyle >

			{/* page 두 번째 박스 */}
			<div>
				<MainCardStyle>
					<Jumbotron>
						<Row>
							<Col md={3}><h3>🙌 가입신청</h3></Col>
							<Col md={8}><h3>{partys.length}건</h3></Col>
							{/* <Col md={6}><Button onClick={joinTeamReq}>전체수락</Button></Col> */}
							<Col md={12}><br /></Col>
							{partys.map((res) => (//이 팀에 들어온 파티 번호 : {res.id}
								<Col md={4}>
									🏃 {res.user.nickname}&nbsp;&nbsp;&nbsp;
									<Button size="sm" variant="outline-secondary" onClick={() => joinTeamReq(res.id)}>수락</Button>
								</Col>
							))}
							<Col md={12}><hr /></Col>

							<Col md={3}><h3>⚔ 대전신청</h3></Col>
							<Col md={12}><br /></Col>
							{battles.map((res) => (
								res.role === 1
									?
									<Col md={6}>
										💥{res.requestTeam.name}&nbsp;&nbsp;&nbsp;
										<Button onClick={() => handleShow3(res.id)} size="sm" variant="outline-secondary">자세히보기</Button>&nbsp;&nbsp;&nbsp;
										<Button size="sm" variant="outline-secondary" onClick={() => rjwjf()}>거절</Button>
										{/* <Button onClick={zzz}>참가명단보기</Button> */}
									</Col>
									: null
							))}
						</Row>
					</Jumbotron>
				</MainCardStyle>
			</div >

			{/* page 세 번째 박스 */}
			<div>
				<MainCardStyle>
					<Jumbotron>
						<Row>
							<Col md={3}><h3>🗓 경기일정</h3></Col>
							<Col md={9} />
							<Col md={12}><br /></Col>
							<Col md={12}>
								{/* <Button onClick={() => handleShow4()} size="sm" variant="outline-secondary">경기일정보기</Button> */}
								<Button size="sm" variant="outline-secondary"><Link to={url2}><SpanTagStyle msg="schedule"></SpanTagStyle></Link></Button>
							</Col>

							<Col md={12}><hr /></Col>
							<Col md={12}><SpanTagStyle msg="승리팀을 선택해주세요"></SpanTagStyle></Col>
							<Col md={12}><br /></Col>
							<Col md={12}>
								{schedule.map((res) => (
									res.role === 2
										? <div>{res.requestTeam.name} ⚔ {res.responseTeam.name}&nbsp;&nbsp;&nbsp;
											< Button onClick={() => handleShow2(res.id, res.requestTeam.name, res.responseTeam.name, res.location, res.matchDate)} size="sm" variant="outline-secondary" ><SpanTagStyle msg="승리팀선택"></SpanTagStyle></Button>
										</div>
										: null
								))}
							</Col>
						</Row>
					</Jumbotron>
				</MainCardStyle>
			</div >
		</Container >
	);
};
// 일단 우리팀의 id를 찾는다
// fetch로 팀 상세보기해서 우리팀 정보 가져와서 보여준다
//팀장일시 : 팀 가입 요청  목록 ) partys로 가져움 + 승인 버튼 , 추방 버튼 
// localhost:8000/givemeid 요청을 보내면 그 유저가 어떤 유저인지 파악해서 id를 리턴해주는 걸 만들면 되겠지?
export default MyTeam;