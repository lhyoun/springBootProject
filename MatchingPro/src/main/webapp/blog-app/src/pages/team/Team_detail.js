import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Carousel, Jumbotron, Button, Breadcrumb, Card, ListGroup, ListGroupItem, ResponsiveEmbed, Form, FormControl, Modal } from 'react-bootstrap';
import styled from 'styled-components';
import SpanTagStyle from '../constant/SpanTagStyle';

const SlideStyle = styled.div`
	margin-top:15%;
	margin-bottom:4%;
`;

const MainCardStyle = styled.div`
  width: 100%;
  margin: auto;
`;

const Team_detail = (props) => {
	const teamId = props.match.params.id;		// 해당 페이지가 어떤 팀의 페이지인지 ID 받아서 들어옴 (props)

	// useEffect에서 팀정보, 팀장정보, 팀원정보를 state로 받는다
	const [team, setTeam] = useState([]);
	const { explaintation, name } = team;
	const [owner, setOwner] = useState([]);
	const [users, setUsers] = useState([]);

	// 기본적으로 팀 정보를 보여주기 위해 필요한 state들을 받아온다
	useEffect(() => {
		fetch(`http://localhost:8000/teamDetail/${teamId}`, {
			method: "get",
		}).then((res) => res.json())
			.then((res) => {
				console.log("teamDetailForm teamDetail info fetch (json type) ", res);
				setTeam(res);
				setOwner(res.owner);
				setUsers(res.users);
			});
	}, []);

	// 해당 팀에 팀원 가입 요청을 보내는 fetch
	const joinTeamReq = () => {
		fetch(`http://localhost:8000/user/apply1/${teamId}`, {
			method: "post",
			headers: {
				'Content-Type': "application/json; charset=utf-8",
				'Authorization': localStorage.getItem("Authorization")
			}
		}).then((res) => res.text())
			.then(res => {
				if (res === "ok") alert("팀가입 요청 완료");
				else alert("팀가입 요청 실패");
			});
	};

	// 대전시청시 필요한 정보를 입력할 수 있는 modal
	const [show, setShow] = useState(false);
	const handleClose = () => setShow(false);
	const handleShow = () => {	// modal을 활성화 하는데, 내가 선택할 수 있게 팀원 리스트를 보여준다
		fetch("http://localhost:8000/user/myTeam", {	// 로그인 해 있는 ID가 속한 팀의 ID를 받아온다
			method: "get",
			headers: {
				'Authorization': localStorage.getItem("Authorization")
			}
		}).then((res) => res.text())
			.then((res) => {
				// 내가 가입한 팀원들 중 게임에 참가할 인원을 선택하기 위해 우리 팀원 리스트를 받아온다
				fetch(`http://localhost:8000/teamDetail/${res}`, {
					method: "get",
				}).then((res) => res.json())
					.then((res) => {
						console.log("teamDetailForm 대전신청 modal - 신청하는 팀의 팀원 list (json type) ", res); setUsers2(res.users);
					});
			});
		setShow(true);
	};

	// 초대 가능한 파티원 (베틀 신청할 때)
	const [users2, setUsers2] = useState([]);
	// 초대에 선택된 파티원
	const [rteam, setRteam] = useState(["팀장"]);

	const rteamplus = (id) => {
		alert("추가되었습니다");
		setRteam([
			...rteam,
			id
		]);
		console.log(rteam);
	}

	const memberCheck = () =>{
		alert(rteam);
	}

	// teaminfo 만들기 - battle 신청 함수도 안에 있음
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
				if (res === "ok") battleRequest();
				else alert("요청 실패");
			});
	};

	const [battleInfo, setBattleInfo] = useState([{
		location: "",
		matchDate: ""
	}]);

	const inputHandle = (e) => {
		setBattleInfo({
			...battleInfo,
			[e.target.name]: e.target.value,
		});
	};

	const battleRequest = () => {	// battle 신청 함수
		let battle = {
			location: battleInfo.location,
			matchDate: battleInfo.matchDate
		}
		fetch(`http://localhost:8000/user/matchApply/${teamId}`, {
			method: "post",
			body: JSON.stringify(battle),
			headers: {
				'Content-Type': "application/json; charset=utf-8",
				'Authorization': localStorage.getItem("Authorization")
			}
		}).then((res) => res.text())
			.then(res => {
				if (res === "ok") {
					handleClose();
					alert("battle request complete");
				}
				else alert("battle request failed");
			});
	};

	return (
		<Container>
			{/* 대전신청을 진행할 수 있는 mdoal */}
			<Modal show={show} size={"lg"} onHide={handleClose}>
				<Modal.Header closeButton>
					<Modal.Title>대전신청</Modal.Title>
				</Modal.Header>
				<Modal.Body>
					<SpanTagStyle msg="게임에 참여할 팀원을 선택해 주세요"></SpanTagStyle>
					<hr />
					<Row>
						{users2.map((res) => (//이 팀에 들어온 파티 번호 : {res.id}
							<Col md={3}>🏃 {res.nickname}&nbsp;&nbsp;&nbsp;
								<Button onClick={() => rteamplus(res.id)} size="sm" variant="outline-secondary">추가</Button></Col>
						))}
					</Row>
					<br/>
					<Button onClick={memberCheck} size="sm" variant="outline-secondary">선택된 팀원 확인</Button>

					<hr />
					<Form>
						<Form.Group as={Col} controlId="formGridEmail">
							<Form.Label><SpanTagStyle msg="location"></SpanTagStyle></Form.Label>
							<Row>
								<Col md={10}>
									<Form.Control
										type="text"
										name="location"
										placeholder="enter message"
										onChange={inputHandle}
										value={battleInfo.location} /></Col>
							</Row>
							<br />
							<Form.Label><SpanTagStyle msg="matchDate"></SpanTagStyle></Form.Label>
							<Row>
								<Col md={10}>
									<Form.Control
										type="text"
										name="matchDate"
										placeholder="matchDate"
										onChange={inputHandle}
										value={battleInfo.matchDate} /></Col>
							</Row>
						</Form.Group>
					</Form>
				</Modal.Body>
				<Modal.Footer>
					<Button onClick={createInfo} variant="outline-secondary">신청하기</Button>
					{/* createInfo 안에 battleRequest 포함 */}
					<Button variant="secondary" onClick={handleClose}>Close
					</Button>
				</Modal.Footer>
			</Modal >

			<SlideStyle>
				<MainCardStyle>
					<Jumbotron>
						<Row>
							<Col md={8}><h1>⚽ {name}</h1></Col>
							<Col md={2}>
								<Button onClick={handleShow} variant="outline-secondary">대전신청</Button>
							</Col>
							<Col md={2}>
								<Button onClick={joinTeamReq} variant="outline-secondary">가입신청</Button>
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
							))}
						</Row>
					</Jumbotron>
				</MainCardStyle>
			</SlideStyle>
		</Container >
	);
};

export default Team_detail;