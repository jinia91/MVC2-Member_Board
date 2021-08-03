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

		// ������ ����
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("sessionID");	
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int bbsid = Integer.valueOf(request.getParameter("bbsId"));
		
		// ��ȿ�� �˻�
		
		if(id.isEmpty()) {
			request.setAttribute("error", "���ǿ���! �ٽ� �α������ּ���!");
			HttpUtil.forward(request, response, "/index.jsp");
			return;
		}
		
		if(title.isEmpty()||content.isEmpty()) {
				
			request.setAttribute("error", "��� �׸��� �������� �����ֽñ� �ٶ��ϴ�!");
			HttpUtil.forward(request, response, "/BBS/BBSWrite.jsp");
			return;
		}
		
		
		// dto�� ������ ���ε�
		BBSDTO dto = new BBSDTO();
		dto.setBbsId(bbsid);
		dto.setBbsTitle(title);
		dto.setBbsContent(content);
		
		// �Խù� �ۼ� ���� ���� ȣ�� �� ��� ȣ��
		BBSService service = BBSService.getInstance();
		service.BBSUpdate(dto);
		HttpUtil.forward(request, response, "/WEB-INF/result/BBS/BBSUpdateOutput.jsp");			

		
		
	}

}
