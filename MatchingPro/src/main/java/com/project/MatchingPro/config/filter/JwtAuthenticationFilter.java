package com.project.MatchingPro.config.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.domain.user.UserRepository;


public class JwtAuthenticationFilter implements Filter{

	private UserRepository userRepository;
	
	public JwtAuthenticationFilter(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("JwtAuthenticationFilter 작동");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out = resp.getWriter();
		
		String method = req.getMethod();
		System.out.println(method);
		if(!method.equals("POST")) {
			out.print("POST요청이 아님.");
			out.flush();
		}else {
			ObjectMapper om = new ObjectMapper();
			try {
				User person = om.readValue(req.getInputStream(), User.class);
				System.out.println(person);
				if(userRepository.countByLoginid(person.getLoginid())==0) {
				//	System.out.println("============="+person);
					
					out.print("아이디x");
					out.flush();
				}else {
					User personEntity = 
							userRepository.findByLoginidAndPassword(person.getLoginid(), person.getPassword());
	
					if(personEntity == null) {
						out.print("비번x");
						out.flush();
					}else {
						System.out.println(personEntity.getNickname()+"님이 로그인 하심.");
						
						String jwtToken = 
								JWT.create()
								.withSubject("토큰제목")
								.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*24))
								.withClaim("id", personEntity.getId())
								.sign(Algorithm.HMAC512(JwtProps.secret));
						
						resp.addHeader(JwtProps.header, JwtProps.auth+jwtToken);
						out.print("ok");
						out.flush();
					}
				}
			} catch (Exception e) {
				System.out.println("오류 : "+e.getMessage());
			}
		}
	}
}
