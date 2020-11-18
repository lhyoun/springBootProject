// ok 
import React from 'react';
import { Row, Col, Carousel } from 'react-bootstrap';
import styled from 'styled-components';

const SlideStyle = styled.div`
	margin-top:4%;
	top: 130px
	margin-bottom:4%;
`;

const ImageStyle =styled.image`
	min-width: 100%;
	height: 500px;
`;

const VideoStyle=styled.video`
	min-width: 100%;
	height: 500px;
`;

const Slide = () => {
	return (
		<SlideStyle>
			<Row>
				<Col md={1}></Col>
				<Col md={10}>
					<Carousel>
						<Carousel.Item>
						<VideoStyle className='videoTag' autoPlay loop muted>
   						 	<source src='neonbg.mp4' type='video/mp4' />
						</VideoStyle>
							<Carousel.Caption>
								<h1>FIELD HERO </h1>
								<h4>아마추어 축구팀간의 매칭, 팀 관리, 팀 기록, 나의 기록을 쉽고 편하게 즐겨보세요.</h4>
							</Carousel.Caption>
						</Carousel.Item>
						<Carousel.Item>
							<VideoStyle className='videoTag' autoPlay loop muted>
    							<source src='bouncebg.mp4' type='video/mp4' />
							</VideoStyle>
							<Carousel.Caption>
								<h3></h3>
								<h4>
									필드히어로에서 축구 실력을 업그레이드 하고 필드히어로에 있는 전국의 팀들과 게임을 즐기기만 하면 됩니다.
								</h4>
							</Carousel.Caption>
						</Carousel.Item>
					</Carousel>
				</Col>
				<Col md={1}></Col>
			</Row>
		</SlideStyle>
	);
};

export default Slide;