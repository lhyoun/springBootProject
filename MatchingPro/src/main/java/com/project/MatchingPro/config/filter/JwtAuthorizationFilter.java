package com.project.MatchingPro.config.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.project.MatchingPro.domain.user.User;
import com.project.MatchingPro.domain.user.UserRepository;


public class JwtAuthorizationFilter implements Filter {

	private UserRepository userRepository;

	public JwtAuthorizationFilter(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("JwtAuthorizationFilter 작동");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setContentType("text/html; charset=UTF-8"); 
		
		String jwtToken = req.getHeader(JwtProps.header);

		if (jwtToken == null) {
			PrintWriter out = resp.getWriter();
			out.print("jwtToken not found");
			out.flush();
		} else {
			
			try {
				jwtToken = jwtToken.replace(JwtProps.auth, "");
				int personId = JWT.require(Algorithm.HMAC512(JwtProps.secret)).build().verify(jwtToken).getClaim("id").asInt();
				System.out.println("personID: "+personId);
				HttpSession session = req.getSession();
				User personEntity = (User)(userRepository.findById(personId).orElseThrow(()-> new IllegalArgumentException("는 존재하지 않습니다.")));
				session.setAttribute("principal", personEntity);
			}catch(Exception e){
				PrintWriter out = resp.getWriter();
				out.print("verify fail");
				out.flush();
				e.printStackTrace();
			}
			
			
			try {
				chain.doFilter(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
