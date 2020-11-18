// ok (image - x) 
import React, { useState } from 'react';
import { ListGroup, Card, ListGroupItem, Row, Col } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import SpanTagStyle from '../../pages/constant/SpanTagStyle';
import styled from 'styled-components';


const CardStyle = styled.div`
opacity: 1;


`;
const TeamCard = (props) => {
	console.log("TeamCard:: props data: ", props);

	const { id, explaintation, name, owner, image } = props.team;
	const nickname = owner.nickname;
	const url1 = "/Team_detail/" + id;
	const url2 = "/Team_schedule/" + id;

	const imageStr = `http://localhost:8000/image/${image}`

	return (
		<CardStyle>
			<Card style={{ width: '18rem' }}>
				{/* <Card.Img variant="top" src="image" /> */}
				<Card.Img variant="top" src={imageStr}/>
				<Card.Body>
					<Card.Title>{name}</Card.Title>
					<Card.Text>
						{explaintation}
					</Card.Text>
				</Card.Body>
				<ListGroup className="list-group-flush">
					<ListGroupItem><Row><Col md={2}>👑</Col>{nickname}</Row></ListGroupItem>
					<ListGroupItem><Row><Col md={2}>👭</Col>{props.team.users.length}/20</Row></ListGroupItem>
					<ListGroupItem><Link to={url2}><SpanTagStyle msg={<Row><Col md={2}>🗓</Col>경기일정</Row>}></SpanTagStyle></Link></ListGroupItem>
					<ListGroupItem><Link to={url1}><SpanTagStyle msg={<Row><Col md={2}>✔</Col>상세보기</Row>}></SpanTagStyle></Link></ListGroupItem>
				</ListGroup>
			</Card>
			<br />
		</CardStyle>
	);
};

export default TeamCard;