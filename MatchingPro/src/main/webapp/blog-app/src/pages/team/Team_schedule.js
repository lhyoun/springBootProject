// ok
import React, { useEffect, useState } from 'react';
import { Jumbotron, Row, Container, Col, Button, Modal } from 'react-bootstrap';
import styled from 'styled-components';
import TitleH3TagStyle from '../constant/TitleH3TagStyle';
import SpanTagStyle from '../constant/SpanTagStyle';


const MainCardStyle = styled.div`
  width: 100%;
  margin: auto;
`;

const SlideStyle = styled.div`
	margin-top:15%;
	margin-bottom:4%;
`;

const Team_schedule = (props) => {
	const id = props.match.params.id;	// 해당 팀 id에 해당하는 팀의 schedule
	const [schedule, setSchedule] = useState([]);
	const [teaminfo, setTeaminfo] = useState([]);	// 해당 page 팀에 대한 정보

	const [team1, setTeam1] = useState({
		user1: null,
		user2: null,
		user3: null,
		user4: null,
		user5: null,
		user6: null,
		user7: null,
		user8: null,
		user9: null,
		user10: null,
		user11: null
	}); // request team member

	const [team2, setTeam2] = useState({
		user1: null,
		user2: null,
		user3: null,
		user4: null,
		user5: null,
		user6: null,
		user7: null,
		user8: null,
		user9: null,
		user10: null,
		user11: null
	}); // response team member

	useEffect(() => {
		// 해당 id의 팀 battle list
		fetch(`http://localhost:8000/battleList/${id}`, {
			method: "get",
		}).then((res) => res.json())
			.then((res) => {
				console.log("team schedule fetch second then res [json type]", res);
				setSchedule(res);
				if (res[0] != null) {
					setTeam1({
						user1: res[0].teamInfo1.user1.nickname,
						user2: res[0].teamInfo1.user2.nickname,
						user3: res[0].teamInfo1.user3.nickname,
						user4: res[0].teamInfo1.user4.nickname,
						user5: res[0].teamInfo1.user5.nickname,
						user6: res[0].teamInfo1.user6.nickname,
						user7: res[0].teamInfo1.user7.nickname,
						user8: res[0].teamInfo1.user8.nickname,
						user9: res[0].teamInfo1.user9.nickname,
						user10: res[0].teamInfo1.user10.nickname,
						user11: res[0].teamInfo1.user11.nickname,
					});
					setTeam2({
						user1: res[0].teamInfo2.user1.nickname,
						user2: res[0].teamInfo2.user2.nickname,
						user3: res[0].teamInfo2.user3.nickname,
						user4: res[0].teamInfo2.user4.nickname,
						user5: res[0].teamInfo2.user5.nickname,
						user6: res[0].teamInfo2.user6.nickname,
						user7: res[0].teamInfo2.user7.nickname,
						user8: res[0].teamInfo2.user8.nickname,
						user9: res[0].teamInfo2.user9.nickname,
						user10: res[0].teamInfo2.user10.nickname,
						user11: res[0].teamInfo2.user11.nickname,
					});
				}else{
					alert("no schedule");
					props.history.push("/");
				}
			});
		fetch(`http://localhost:8000/teamDetail/${id}`, {
			method: "get",
		}).then((res) => res.json())
			.then((res) => setTeaminfo(res));
	}, []);

	return (
		<Container>
			<SlideStyle>
				<MainCardStyle>
					<Jumbotron>
						<TitleH3TagStyle imt="🗓" msg={teaminfo.name} msg2=" 경기일정"></TitleH3TagStyle>
						<hr />
						{schedule.map((res) => (
							<Row>
								<Col md={4}></Col>
								<Col md={8}>
									<SpanTagStyle imt={res.requestTeam.name} msg=" ⚔ " msg2={res.responseTeam.name} ></SpanTagStyle>
								</Col>
								{/* 상대하는 두 팀 */}
								<Col md={12}><hr /></Col>

								<Col md={6}>
									<SpanTagStyle imt="📍 " msg={res.location}></SpanTagStyle>
								</Col>
								{/* 장소 */}
								<Col md={6}>
									<SpanTagStyle imt="🕰 " msg={res.matchDate}></SpanTagStyle>
								</Col>
								{/* 시간 */}
								<Col md={12}><hr /></Col>
								{/* 선 */}

								<Col md={6}>
									<SpanTagStyle imt="👭 " msg={res.requestTeam.name} msg2=" 참가선수"></SpanTagStyle>
								</Col>
								<Col md={6}>
									<SpanTagStyle imt="👭 " msg={res.responseTeam.name} msg2=" 참가선수"></SpanTagStyle>
								</Col>
								<Col md={2}>1. {team1.user1}</Col>
								<Col md={2}>2. {team1.user2}</Col>
								<Col md={2}>3. {team1.user3}</Col>
								<Col md={2}>1. {team2.user1}</Col>
								<Col md={2}>2. {team2.user2}</Col>
								<Col md={2}>3. {team2.user3}</Col>

								<Col md={2}>4. {team1.user4}</Col>
								<Col md={2}>5. {team1.user5}</Col>
								<Col md={2}>6. {team1.user6}</Col>
								<Col md={2}>4. {team2.user4}</Col>
								<Col md={2}>5. {team2.user5}</Col>
								<Col md={2}>6. {team2.user6}</Col>

								<Col md={2}>7. {team1.user7}</Col>
								<Col md={2}>8. {team1.user8}</Col>
								<Col md={2}>9. {team1.user9}</Col>
								<Col md={2}>7. {team2.user7}</Col>
								<Col md={2}>8. {team2.user8}</Col>
								<Col md={2}>9. {team2.user9}</Col>

								<Col md={2}>10. {team1.user10}</Col>
								<Col md={2}>11. {team1.user11}</Col>
								<Col md={2} />
								<Col md={2}>10. {team2.user10}</Col>
								<Col md={2}>11. {team2.user11}</Col>
								<Col md={12}><hr /></Col>
							</Row>
						))}
						{/* 오른쪽 정렬하려고 모달 푸터 사용 */}
						<Modal.Footer>
							<Button onClick={() => props.history.push("/")} variant="secondary">Close</Button>
						</Modal.Footer>
					</Jumbotron>
				</MainCardStyle>
			</SlideStyle>
		</Container >
	);
};

export default Team_schedule;