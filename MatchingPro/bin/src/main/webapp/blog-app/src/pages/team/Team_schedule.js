// ok
import React, { useEffect, useState } from 'react';
import { Jumbotron, Row, Container, Col } from 'react-bootstrap';
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

	useEffect(() => {
		// 해당 id의 팀 battle list
		fetch(`http://localhost:8000/battleList/${id}`, {
			method: "get",
		}).then((res) => res.json())
			.then((res) => {
				console.log("team schedule fetch second then res [json type]", res);
				setSchedule(res);
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
								<Col md={3}>
									<SpanTagStyle imt={res.responseTeam.name} msg="⚔" msg2={res.requestTeam.name} ></SpanTagStyle>
								</Col>
								<Col md={9}></Col>
								{/* 상대하는 두 팀 */}
								<Col md={3}></Col>
								<Col md={3}>
									<SpanTagStyle imt="장소 " msg={res.location}></SpanTagStyle>
								</Col>
								<Col md={6}></Col>
								{/* 장소 */}
								<Col md={3}></Col>
								<Col md={3}>
									<SpanTagStyle imt="시간 " msg={res.matchDate}></SpanTagStyle>
								</Col>
								<Col md={6}></Col>
								{/* 시간 */}
								<Col md={3}></Col>
								<Col md={9}><hr /></Col>
								{/* 선 */}
							</Row>
						))}
					</Jumbotron>
				</MainCardStyle>
			</SlideStyle>
		</Container>
	);
};

export default Team_schedule;