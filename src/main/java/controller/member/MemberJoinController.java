package controller.member;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import beans.MemberDTO;
import controller.Controller;
import controller.HttpUtil;
import service.MemberService;


public class MemberJoinController implements Controller {

	// 회원가입 흐름과 정보를 다루는 서브컨트롤러
	
	// 역할은 1. 회원가입정보에 대한 유효성 정보 체크
	// 2. 체크 통과시 해당 정보를 회원 빈즈에 저장
	// 3. 회원정보와 관련된 비지니스 로직(모델) memberservice 싱글톤 객체주소값을 받아와 모델의 Join 메소드를 동작
	// 4. 결과값을 반환받아 뷰단으로 포워딩
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 정보 추출
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String birth = request.getParameter("birth");
		String gender = request.getParameter("gender");
		
		// 1. 유효성 체크 - 1) 공백검사 2) 추후 추가개발 프론트단/백단 모두 필요
		if(id.isEmpty()||pwd.isEmpty()||email.isEmpty()||birth.isEmpty()) {
			
			request.setAttribute("error", "모든 항목을 빠짐없이 적어주시기 바랍니다!");
			HttpUtil.forward(request, response, "/MemberJoinInput.jsp");
			return;
		}
		
		// 2. 빈즈에 데이터 바인딩
		MemberDTO member = new MemberDTO();
		member.setId(id);
		member.setPwd(pwd);
		member.setEmail(email);
		member.setBirth(birth);
		member.setGender(gender);
		
		// 3. 서비스 객체 메소드 호출
		MemberService service = MemberService.getInstance();    // 싱글톤으로 서비스 객체 주소 반환
		service.MemberJoin(member);								// 가입 메소드 호출

		
		// 4. 뷰단으로 포워딩
		request.setAttribute("id", id);
		HttpUtil.forward(request, response, "/WEB-INF/result/member/MemberJoinOutput.jsp");
		
	}

}
