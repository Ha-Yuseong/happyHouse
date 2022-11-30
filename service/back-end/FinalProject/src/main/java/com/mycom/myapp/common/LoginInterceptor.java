package com.mycom.myapp.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mycom.myapp.user.dto.UserDto;

@Component
public class LoginInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		// 이전에는 비동기 요청 + 페이지 전환 두가지 중에 뭐가 들어올지 몰라서 헤더에  비동기라고 표시하는걸
		// 추가했는데 이제는 쭉 비동기만해서 if 문으로 확인할 필요가 없다.
		
		
		// CORS put, delete 요청에 대한 허락
		if(request.getMethod().equals("OPTIONS")) {
			return true;
		}
		
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		if( userDto == null) {
			Gson gson = new Gson();
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("result", "login");
			String jsonStr = gson.toJson(jsonObject);
			response.getWriter().write(jsonStr);
			
			return false;
		}
		
		return true; // 통과 // client 가 가고자 하는 요청으로 넘어간다.
		
		
	}
}
