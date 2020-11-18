import React from 'react';
import { Row, Col, Carousel } from 'react-bootstrap';
import styled from 'styled-components';

const SlideStyle = styled.div`
	margin-top:4%;
	top: 130px;
	margin-bottom:4%;
	
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
						<	VideoStyle className='videoTag' autoPlay loop muted>
    <source src='neonbg.mp4' type='video/mp4' />
</VideoStyle>
							<Carousel.Caption>
								<h1>WELCOME TO MATCH HERO </h1>
								<h4>아마추어 축구팀간의 매칭, 팀 관리, 팀 기록, 나의 기록을 쉽고 편하게 즐겨보세요.</h4>
							</Carousel.Caption>
						</Carousel.Item>
						<Carousel.Item>
					<	VideoStyle className='videoTag' autoPlay loop muted>
    <source src='bouncebg.mp4' type='video/mp4' />
</VideoStyle>
							<Carousel.Caption>
								<h3></h3>
								<h1>

									필드히어로에서 축구 실력을 업그레이드 하고 필드히어로에 있는 전국의 팀들과 게임을 즐기기만 하면 됩니다.
								</h1>
							</Carousel.Caption>
						</Carousel.Item>
						<Carousel.Item>
							<img
								className="d-block w-100"
								src="3slideground.jpg"
								alt="Third slide"
							/>
							<Carousel.Caption>
								<h3>Third slide label</h3>
								<p>아마추어 축구의 새로운 문화를 만들어 갑니다! 
									어렵게 잡은 매치! 팀원들에게 일일히 설명하고 참여 여부 체크하고 장소안내 너무 귀찮으시죠?  새로운 팀원이나 용병을 일일이 구하기 너무 힘드시죠?

									아마추어 축구인들을 위한 축구 매칭 플랫폼 필드 히어로 입니다. 

									간편하게 매치를 잡고	경기기록을 보고 관리하세요!</p>
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