package controller.BBS;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BBSDTO;
import controller.Controller;
import controller.HttpUtil;
import service.BBSService;


// 게시물 작성시 정보흐름을 담당하는 컨트롤러


// 1. write 폼에서 게시물 제목과 내용 추출
// 2. 유효성검증 (세션 아이디 확인, 제목과 내용 비었는지 확인)
// 3. dto에 바인드
// 4. dao로 전송하고 반환값받기
// 5. 반환값 뷰단으로 전송

public class BBSWriteController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		// 데이터 추출
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("sessionID");	
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
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
		dto.setId(id);
		dto.setBbsTitle(title);
		dto.setBbsContent(content);
		
		// 게시물 작성 서비스 로직 호출 및 뷰단 호출
		BBSService service = BBSService.getInstance();
		service.BBSWrite(dto);
		HttpUtil.forward(request, response, "/WEB-INF/result/BBS/BBSWriteOutput.jsp");			
	}

}
