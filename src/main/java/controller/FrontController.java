package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.BBS.BBSDeleteController;
import controller.BBS.BBSListController;
import controller.BBS.BBSUpdateInputController;
import controller.BBS.BBSUpdateResultController;
import controller.BBS.BBSViewController;
import controller.BBS.BBSWriteController;
import controller.member.MemberDeleteController;
import controller.member.MemberJoinController;
import controller.member.MemberLoginController;
import controller.member.MemberLogoutController;
import controller.member.MemberSearchAllController;
import controller.member.MemberSearchController;
import controller.member.MemberUpdateController;


public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 91111L; // 직렬화의 고유번호라고 보면 됨
	private String charset = null;
	private HashMap<String, Controller> list = null; // 서브 컨트롤러 리스트 선언

	@Override
	public void init(ServletConfig sc) throws ServletException { // 최초실행시 프론트 컨트롤러의 동작정의
		
		// 1. 인풋되는 정보 인코딩 방식 정의
		charset = sc.getInitParameter("charset"); 
		
		// 2. 서브 컨트롤러들 객체 생성
		list = new HashMap<String,Controller>();

		// 회원가입 관련
		list.put("MemberJoin.do", new MemberJoinController()); 
		list.put("MemberLogin.do", new MemberLoginController());
		list.put("MemberLogout.do", new MemberLogoutController());
		list.put("MemberSearch.do", new MemberSearchController());
		list.put("MemberSearchAll.do", new MemberSearchAllController());
		list.put("MemberUpdate.do", new MemberUpdateController());
		list.put("MemberDelete.do", new MemberDeleteController());
		// 게시판 관련
		list.put("BBSList.do", new BBSListController());
		list.put("BBSWrite.do", new BBSWriteController());
		list.put("BBSView.do", new BBSViewController());
		list.put("BBSUpdateInput.do", new BBSUpdateInputController());
		list.put("BBSUpdateResult.do", new BBSUpdateResultController());
		list.put("BBSDelete.do", new BBSDeleteController());
		

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
