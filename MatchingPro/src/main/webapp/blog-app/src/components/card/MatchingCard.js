import React from 'react';
import { ListGroup, Card, ListGroupItem, Row, Col } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const LinkStyle = styled.span`
    color : black;
  `;

const MatchingCard = (props) => {
	return (
		<div>
			<Card style={{ width: '18rem' }}>
				<Card.Img variant="top" src="1slideepic.png" />
				<Card.Body>
					<Card.Title>zz</Card.Title>
					<Card.Text>
						zz
					</Card.Text>
				</Card.Body>
				<ListGroup className="list-group-flush">
					<ListGroupItem><Row><Col md={2}>👑</Col>zz</Row></ListGroupItem>
					<ListGroupItem><Row><Col md={2}>👭</Col>zz</Row></ListGroupItem>
					<ListGroupItem><Link to=""><LinkStyle><Row><Col md={2}>🗓</Col>경기일정</Row></LinkStyle></Link></ListGroupItem>
					<ListGroupItem><Link to=""><LinkStyle><Row><Col md={2}>✔</Col>상세보기</Row></LinkStyle></Link></ListGroupItem>
				</ListGroup>
				{/* <Card.Body>
				<Link to={url}><LinkStyle>✔상세보기</LinkStyle></Link>
			</Card.Body> */}
			</Card>
			<br />
		</div>
	);
};

export default MatchingCard;