import React from 'react';
import styled from 'styled-components';

const Background_videoStlye = styled.video`
	min-weight: 100%
 	min-height: 100%
 	left:0;
 	right:0;
 	top:0;
  	float: left;
  	padding: none;
  	position: fixed; /* optional depending on what you want to do in your app */
  	z-index: -1;
`;

const Background = () => {
	return (
		<div>
			<Background_videoStlye id="background-video" loop autoPlay>
				<source src="soccerstadium.mp4" type="video/ogg" />
        		Your browser does not support the video tag.
      		</Background_videoStlye>
		</div>
	);
};

export default Background;