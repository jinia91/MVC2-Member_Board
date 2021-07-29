package controller.member;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import beans.MemberDTO;
import controller.Controller;
import controller.HttpUtil;
import service.MemberService;


// 로그인 흐름과 정보를 다루는 컨트롤러

//역할은 1. 로그인에 대한 아이디, 비밀번호 유효성 정보체크 
// 2. 유효성 체크 통과시 빈즈에 바인딩
// 3. 비지니스 로직에 해당 로그인 정보 전달
// 4. 로직 처리 결과를 뷰단으로 포워딩

public class MemberLoginController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 정보추출
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		// 1. 유효성 체크 - 1) 공백검사 2) 추후 추가개발 프론트단/백단 모두 필요
		if(id.isEmpty()||pwd.isEmpty()) {
			
			request.setAttribute("error", "모든 항목을 빠짐없이 적어주시기 바랍니다!"); 
			HttpUtil.forward(request, response, "/member/MemberLogin.jsp");
			return;
		
		}
		
		// 2. 빈즈에 데이터 바인딩
		MemberDTO member = new MemberDTO();
		member.setId(id);
		member.setPwd(pwd);
		

		// 3. 서비스 객체 메소드 호출
		MemberService service = MemberService.getInstance();    // 싱글톤으로 서비스 객체 주소 반환
		int loginResult = service.MemberLogin(member.getId(),member.getPwd());	// 로그인 메소드 호출하여 결과값 반환
		
		
		
		// 4. 결과값에 따른 뷰단 흐름 제어
		// 1 == 로그인 성공, 0 == 비번틀림, -1 == 아이디 없음
		
		if(loginResult==1) {
			request.setAttribute("loginResult", loginResult);
			HttpSession session = request.getSession();
			session.setAttribute("sessionID", id);
			HttpUtil.forward(request, response, "/WEB-INF/result/member/LoginSuc.jsp");
			
		}
		else {
			request.setAttribute("loginResult", loginResult);
			HttpUtil.forward(request, response, "/member/MemberLogin.jsp");
		}
			
	}

}
