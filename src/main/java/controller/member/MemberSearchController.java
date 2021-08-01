package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.HttpUtil;
import service.MemberService;

// 회원 정보 찾기에 관련된 정보 흐름 제어 컨트롤러

// 역할은 1. 유효성 체크
// 2. 체크된 정보 비지니스 로직으로 전송 및 회원정보 빈즈 반환
// 3. 로직결과를 뷰단으로 포워딩

public class MemberSearchController implements Controller{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 정보 추출
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		
		// 1. 유효성 체크
		if(id.isEmpty()||email.isEmpty()) {
			
			request.setAttribute("error", "모든 항목을 빠짐없이 적어주시기 바랍니다!");
			HttpUtil.forward(request, response, "/member/MemberSearch.jsp");
			return;
		}
		
		// 2. 비지니스 로직 호출, 회원 비밀번호 반환
		MemberService service = MemberService.getInstance();		
		String pwd = service.memberSearch(id,email);
		
		// 3. 결과값에 따른 뷰단에 포워딩
		
		if(pwd==null) {
			request.setAttribute("error", "검색된 정보가 없습니다.");
			HttpUtil.forward(request, response, "/member/MemberSearch.jsp");
			
		}
		else {
			request.setAttribute("id", id);
			request.setAttribute("pwd", pwd);
			HttpUtil.forward(request, response, "/WEB-INF/result/member/MemberSearchResult.jsp");
		}
		
		
	}

}
