package com.mycom.myapp.common;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mycom.myapp.dto.UserDto;

@Component
public class LoginInterceptor implements HandlerInterceptor{
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println(">>>>> " + request.getRequestURI());
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        
        // CORS 에서  put, delete 등 오류 해결 코드
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        // 
        if( userDto == null ) {
        	System.out.println("값이 잘못넘어온듯 userDto 널입니다 - 인터셉터");
        	
			Gson gson = new Gson();

			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("result", "login");
			
			String jsonStr = gson.toJson(jsonObject);
			response.getWriter().write(jsonStr);
			
        	return false;
        }

        return true;
    }
}
