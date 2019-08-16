package com.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.utility.AES;
import com.utility.ApplicationConstant;
import com.utility.JWTUtils;

@Component
public class ProtectedApiFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain arg2)
			throws ServletException,IOException {

		String authHeader = request.getHeader("Authorization");
		String userDatail = AES.decrypt(authHeader, "ssshhhhhhhhhhh!!!!");
		
		JSONObject jUser = new JSONObject();
		try {
			jUser = new JSONObject(userDatail);
			jUser.put("loggedIn", true);
			System.out.println("User data ="+jUser);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String jwt =  JWTUtils.createJWT(JWTUtils.getRandomUUID(5), ApplicationConstant.JWT_ISSUER, jUser.toString(), ApplicationConstant.DEFAULT_JWT_TIME_OUT);
		System.out.println("Token generated = "+jwt);
		response.addHeader(ApplicationConstant.AUTHORIZATION_HEADER, ApplicationConstant.AUTHORIZATION_BEARER+jwt);
	}
}