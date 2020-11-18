import React, { useEffect, useState } from 'react';
import { Jumbotron, Container, Tooltip, Button, OverlayTrigger, Col, Card, Accordion } from 'react-bootstrap';
import styled from 'styled-components';
import SpanTagStyle from '../constant/SpanTagStyle';

const MainCardStyle = styled.div`
  width: 100%;
  margin: auto;
`;

const SlideStyle = styled.div`
	margin-top:15%;
	margin-bottom:4%;
`;
const Mypage = () => {

	const [user, setUser] = useState([]);
	const [partys, setPartys] = useState([]);
	const [team, setTeam] = useState([]);

	const acceptrequest = (id) => {
		fetch(`http://localhost:8000/acceptrequest/${id}`, {
			method: "put",
		}).then((res) => {
			return 0;
		}).then((res) => {
			console.log("Mypage useEffect, res of signin user::", res);
			setUser(res);
		});
	}

	useEffect(() => {
		//fetch that brings loginid 
		fetch("http://localhost:8000/user/loginid", {
			method: "get",
			headers: {
				'Authorization': localStorage.getItem("Authorization")
			}
		}).then((res) => {
			console.log("rr", res);
			return res.text();
		}).then((res) => {
			console.log("Mypage useEffect, id of signin user::", res);

			fetch(`http://localhost:8000/userDetail/${res}`, {
				method: "get",
			}).then((res) => {
				return res.json();
			}).then((res) => {
				console.log("Mypage useEffect, res of signin user::", res);
				setUser(res);
			});
			//fetch to get the party information using the first login id 
			fetch(`http://localhost:8000/user/partyList/${res}`, {
				method: "post",
				headers: {
					'Authorization': localStorage.getItem("Authorization")
				}
			}).then((res) => {
				console.log("dddddd", res);
				return res.json();
			}).then((res) => {
				setPartys(res);
				setTeam(res.team)
				console.log("ooo", res);
			});
		});
	}, []);

	return (
		<Container>
			<SlideStyle>
				<MainCardStyle>

					<Jumbotron>
						<h4>
							<OverlayTrigger
								key='top'
								placement='top'
								overlay={
									<Tooltip id={`tooltip-top`}>Location</Tooltip>
								}>
								<Button variant="light">📍</Button>
							</OverlayTrigger>
							{user.location}
						</h4>
						<hr />

						<h4>
							<OverlayTrigger
								key='top'
								placement='top'
								overlay={
									<Tooltip id={`tooltip-top`}>포지션</Tooltip>
								}>
								<Button variant="light">🏃</Button>
							</OverlayTrigger>
							{user.position}
						</h4>
						<hr />

						<h4>
							<OverlayTrigger
								key='top'
								placement='top'
								overlay={
									<Tooltip id={`tooltip-top`}>닉네임</Tooltip>
								}>
								<Button variant="light">📄</Button>
							</OverlayTrigger>
							{user.nickname}
						</h4>
						<hr />

						<Accordion defaultActiveKey="0">
							<Card.Header>
								<Accordion.Toggle as={Button} variant="link" eventKey="0">
									<OverlayTrigger
										key='top'
										placement='top'
										overlay={
											<Tooltip id={`tooltip-top`}>
												{partys.map((res) => (//이 팀에 들어온 파티 번호 : {res.id}
													<Col md={3}> request from {res.team.name} </Col>
												))}
											</Tooltip>}>
										<Button variant="light"> 💡  나에게 들어온 팀 가입 요청</Button>
									</OverlayTrigger>
								</Accordion.Toggle>
							</Card.Header>

							<Accordion.Collapse eventKey="0">
								<Card.Body>
									{partys.map((res) => (//이 팀에 들어온 파티 번호 : {res.id}
										/* party length : 2 / 그러면 처음에는 party[0] 그 다음에는 party[1] */
										<Col md={12}>
											<SpanTagStyle imt="🔖" msg={res.team.name}></SpanTagStyle>&nbsp;&nbsp;&nbsp;
											<Button size="sm" variant="outline-secondary" onClick={() => acceptrequest(res.id)}>수락</Button>
										</Col>))}
								</Card.Body>
							</Accordion.Collapse>
						</Accordion>
					</Jumbotron>
				</MainCardStyle>
			</SlideStyle>
		</Container>
	);
};

export default Mypage;