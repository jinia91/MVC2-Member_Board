package controller.BBS;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.HttpUtil;
import service.BBSService;

public class BBSDeleteController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bbsId = request.getParameter("bbsId");
		String Id = request.getParameter("id");
		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("sessionID");
		
		if(Id.isEmpty()||bbsId.isEmpty()) {
			request.setAttribute("error", "세션 오류! 로그인을 다시해주세요!");
			HttpUtil.forward(request, response, "/MVC2Board/index.jsp");
			return;
		}
		
		
		BBSService service = BBSService.getInstance();
		service.BBSDelete(bbsId);
		HttpUtil.forward(request, response, "/WEB-INF/result/BBS/BBSDeleteOutput.jsp");
		
		
	}
		
		
		
}
