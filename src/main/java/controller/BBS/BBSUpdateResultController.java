package controller.BBS;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BBSDTO;
import controller.Controller;
import controller.HttpUtil;
import service.BBSService;

public class BBSUpdateResultController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 데이터 추출
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("sessionID");	
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int bbsid = Integer.valueOf(request.getParameter("bbsId"));
		
		// 유효성 검사
		
		if(id.isEmpty()) {
			request.setAttribute("error", "세션오류! 다시 로그인해주세요!");
			HttpUtil.forward(request, response, "/index.jsp");
			return;
		}
		
		if(title.isEmpty()||content.isEmpty()) {
				
			request.setAttribute("error", "모든 항목을 빠짐없이 적어주시기 바랍니다!");
			HttpUtil.forward(request, response, "/BBS/BBSWrite.jsp");
			return;
		}
		
		
		// dto에 데이터 바인드
		BBSDTO dto = new BBSDTO();
		dto.setBbsId(bbsid);
		dto.setBbsTitle(title);
		dto.setBbsContent(content);
		
		// 게시물 작성 서비스 로직 호출 및 뷰단 호출
		BBSService service = BBSService.getInstance();
		service.BBSUpdate(dto);
		HttpUtil.forward(request, response, "/WEB-INF/result/BBS/BBSUpdateOutput.jsp");			

		
		
	}

}
