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

// �Խ��� ���� ȭ�� �������� �����ϴ� ��Ʈ�ѷ�

// 1. �Խ��� ����� �ҷ��� ���� �޼ҵ� ȣ��
// 2. ����� list�� ��� ������� ����


public class BBSListController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BBSService service = BBSService.getInstance();
		ArrayList<BBSDTO> list = service.BBSList();
		
		request.setAttribute("BBSList", list);
		HttpUtil.forward(request, response, "/WEB-INF/result/BBS/BBS.jsp");
		
		
		
	}

}
