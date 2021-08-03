package controller.BBS;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BBSDTO;
import controller.Controller;
import controller.HttpUtil;
import service.BBSService;

public class BBSViewController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BBSDTO dto = null;
		
		BBSService service = BBSService.getInstance();
		String bbsId = request.getParameter("bbsId");
		dto = service.BBSView(bbsId);
		
		request.setAttribute("BBS", dto);
		HttpUtil.forward(request, response, "/WEB-INF/result/BBS/BBSViewOutput.jsp");
		
		
		
	}

}
