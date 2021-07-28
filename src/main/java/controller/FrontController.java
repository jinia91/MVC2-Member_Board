package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import controller.member.MemberJoinController;

import java.util.*;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 91111L; // ����ȭ�� ������ȣ��� ���� ��
	private String charset = null;
	private HashMap<String, Controller> list = null; // ���� ��Ʈ�ѷ� ����Ʈ ����

	@Override
	public void init(ServletConfig sc) throws ServletException { // ���ʽ���� ����Ʈ ��Ʈ�ѷ��� ��������
		
		// 1. ��ǲ�Ǵ� ���� ���ڵ� ��� ����
		charset = sc.getInitParameter("charset"); 
		
		// 2. ���� ��Ʈ�ѷ��� ��ü ����
		list = new HashMap<String, Controller>();
		
		list.put("MemberJoin.do", new MemberJoinController()); 

	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding(charset); // ��û ���� ���ڵ�
		
		String url = request.getRequestURI(); // ��û�� uri ����(/MVC2Board/~~~.do)
		String path[] = url.split("/"); // ~~~~.do ����		
		
		Controller subController = list.get(path[path.length-1]); // ������ ���� ������Ʈ�ѷ� list�� Ű�� �ش� ���� ��Ʈ�ѷ� �ּҰ��� �޾Ƽ�
		subController.execute(request, response); // ���� ��Ʈ�ѷ� ����޼ҵ�

	}

}
