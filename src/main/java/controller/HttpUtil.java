package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;


// 앱 전반에 사용되는 static 메소드 유틸 기능들 모음;

public class HttpUtil {
	
	// 1. 화면 출력시 포워딩
	// 2. 추가예정
	
	// 1. 포워딩
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path){
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			System.out.println("forward 오류" + e);
		}
	}
	
}