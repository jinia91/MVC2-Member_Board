package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import controller.member.MemberJoinController;
import controller.member.MemberLoginController;

import java.util.*;



public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 91111L; // 직렬화의 고유번호라고 보면 됨
	private String charset = null;
	private HashMap<String, Controller> list = null; // 서브 컨트롤러 리스트 선언

	@Override
	public void init(ServletConfig sc) throws ServletException { // 최초실행시 프론트 컨트롤러의 동작정의
		
		// 1. 인풋되는 정보 인코딩 방식 정의
		charset = sc.getInitParameter("charset"); 
		
		// 2. 서브 컨트롤러들 객체 생성 서브컨트롤러들도 사실상 싱글톤객체가 됨
		list = new HashMap<String,Controller>();
		
		list.put("MemberJoin.do", new MemberJoinController()); 
		list.put("MemberLogin.do", new MemberLoginController()); 

	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding(charset); // 요청 정보 인코딩
		
		String url = request.getRequestURI(); // 요청시 uri 추출(/MVC2Board/~~~.do)
		String path[] = url.split("/"); // ~~~~.do 추출		
		
		Controller subController = list.get(path[path.length-1]); // 추출한 값은 서브컨트롤러 list의 키로 해당 서비스 컨트롤러 주소값을 받아서
		subController.execute(request, response); // 서브 컨트롤러 실행메소드

	}

}
