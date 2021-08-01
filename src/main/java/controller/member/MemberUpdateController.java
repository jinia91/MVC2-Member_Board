package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.MemberDTO;
import controller.Controller;
import controller.HttpUtil;
import service.MemberService;

// 회원정보 변경의 정보와 흐름을 다루는 컨트롤러

// 1. 정보 추출
// 2. 유효성 체크후 dto에 바인딩 
// 3. update 로직으로 해당 정보 전달후 결과 반환
// 4. 뷰단으로 결과 전송


public class MemberUpdateController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		// 1. 추출
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String birth = request.getParameter("birth");
		
		// 2. 유효성 체크
		if(id.isEmpty()||pwd.isEmpty()||email.isEmpty()||birth.isEmpty()) {
			
			request.setAttribute("error", "모든 항목을 빠짐없이 적어주시기 바랍니다!"); 
			HttpUtil.forward(request, response, "/member/MemberUpdate.jsp");
			return;
		}
		
		// 데이터 빈즈에 바인딩
		MemberDTO member = new MemberDTO();
		member.setId(id);
		member.setPwd(pwd);
		member.setEmail(email);
		member.setBirth(birth);
		
		//3. 서비스 객체 메소드 호출
		MemberService service = MemberService.getInstance();    // 싱글톤으로 서비스 객체 주소 반환
		service.MemberUpdate(member);								// 가입 메소드 호출

		// 4. 뷰단으로 포워딩
		request.setAttribute("id", id);
		HttpUtil.forward(request, response, "/WEB-INF/result/member/MemberUpdate.jsp");


	}

}
