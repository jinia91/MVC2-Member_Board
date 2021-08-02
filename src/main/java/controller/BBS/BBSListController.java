package controller.BBS;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BBSDTO;
import controller.Controller;
import controller.HttpUtil;
import service.BBSService;

// 게시판 전면 화면 렌더링을 제어하는 컨트롤러

// 1. 게시판 목록을 불러올 로직 메소드 호출
// 2. 목록을 list로 묶어서 뷰단으로 전송


public class BBSListController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BBSService service = BBSService.getInstance();
		ArrayList<BBSDTO> list = service.BBSList();
		
		request.setAttribute("BBSList", list);
		HttpUtil.forward(request, response, "/WEB-INF/result/BBS/BBS.jsp");
		
		
		
	}

}
