package controller.BBS;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.HttpUtil;
import service.MemberService;

public class BBSDeleteController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bbsId = request.getParameter("bbsId");
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("sessionID");
		
		if(id.isEmpty()||bbsId.isEmpty()) {
			request.setAttribute("error", "세션 오류! 로그인을 다시해주세요!");
			HttpUtil.forward(request, response, "index.jsp");
			return;
		}
		
		if(id != sessionId) {
			request.setAttribute("error", "본인이 작성한 글이 아닙니다!");
			HttpUtil.forward(request, response, "/WEB-INF/result/BBS/BBSViewOutput.jsp");
			return;
		}
		
		
		
		
	}
		
		
		
}
