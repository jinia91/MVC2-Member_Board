package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.HttpUtil;
import service.MemberService;
	

//회원정보 삭제의 정보와 흐름을 다루는 컨트롤러

//1. 정보 추출
//2. 비밀번호 유효성 체크
//3. delete 로직으로 해당 정보 전달후 결과 반환
//4. 뷰단으로 결과 전송

	

public class MemberDeleteController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 1. 삭제위한 아이디 비밀번호 추출
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("sessionID");
		String pwd = request.getParameter("pwd");
		
		// 2. 유효성 체크
		if(id.isEmpty()||pwd.isEmpty()) {
					
		request.setAttribute("error", "세션 오류! 로그인을 다시해주세요!");
		HttpUtil.forward(request, response, "/member/MemberDelete.jsp");
		return;
		}
		
		// 유효성체크(2) 비밀번호 일치 여부
		MemberService service = MemberService.getInstance();	
		int pwdResult = service.MemberLogin(id, pwd);
				
		if(pwdResult==0) { 	// 비밀번호 틀릴시 재입력 요구		
			request.setAttribute("error", "비밀번호가 틀렸습니다! 다시 입력해주세요!");	
			HttpUtil.forward(request, response, "/member/MemberDelete.jsp");
			return;
		}
			
		
		// 서비스 객체 메소드 호출
		service.MemberDelete(id,pwd);
		
		// 4. 뷰단으로 포워딩
		request.setAttribute("id", id);
		session.invalidate();
		HttpUtil.forward(request, response, "/WEB-INF/result/member/MemberDeleteOutput.jsp");
				
	}

}
