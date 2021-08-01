package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.MemberDTO;
import controller.Controller;
import controller.HttpUtil;
import service.MemberService;

// 회원 정보를 가져오는 흐름 제어 컨트롤러

// 역할은 1. 세션 아이디 추출과 유효성 체크
// 2. 체크된 정보 비지니스 로직으로 전송 및 회원정보 빈즈 반환
// 3. 로직결과를 뷰단으로 포워딩

public class MemberSearchAllController implements Controller{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 정보 추출
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("sessionID");	
		String pwd = request.getParameter("pwd");
		
		// 1. 유효성 체크
		if(id.isEmpty()||pwd.isEmpty()) {
			
			request.setAttribute("error", "세션 오류! 로그인을 다시해주세요!");
			HttpUtil.forward(request, response, "/member/MemberUpdate.jsp");
			return;
		}
		
		// 유효성체크(2) 비밀번호 일치 여부
		MemberService service = MemberService.getInstance();	
		int pwdResult = service.MemberLogin(id, pwd);
		
		if(pwdResult==0) { 	// 비밀번호 틀릴시 재입력 요구		
			request.setAttribute("error", "비밀번호가 틀렸습니다! 다시 입력해주세요!");	
			HttpUtil.forward(request, response, "/member/MemberUpdate.jsp");
			return;
		}
	
		
		// 2. 비밀번호 일치시 회원정보 모두 불러오기 로직 호출

		MemberDTO member = service.memberSearchAll(id, pwd);
		
		// 3. 반환값 요청정보에 실어서 뷰단으로 보내기
		
		request.setAttribute("member", member);
		HttpUtil.forward(request, response, "/member/MemberUpdate.jsp");
		
		
	}

}
