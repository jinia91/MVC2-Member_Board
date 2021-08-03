package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import controller.BBS.BBSDeleteController;
import controller.BBS.BBSListController;
import controller.BBS.BBSUpdateController;
import controller.BBS.BBSViewController;
import controller.BBS.BBSWriteController;
import controller.member.MemberDeleteController;
import controller.member.MemberJoinController;
import controller.member.MemberLoginController;
import controller.member.MemberLogoutController;
import controller.member.MemberSearchAllController;
import controller.member.MemberSearchController;
import controller.member.MemberUpdateController;

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
		list = new HashMap<String,Controller>();

		// ȸ������ ����
		list.put("MemberJoin.do", new MemberJoinController()); 
		list.put("MemberLogin.do", new MemberLoginController());
		list.put("MemberLogout.do", new MemberLogoutController());
		list.put("MemberSearch.do", new MemberSearchController());
		list.put("MemberSearchAll.do", new MemberSearchAllController());
		list.put("MemberUpdate.do", new MemberUpdateController());
		list.put("MemberDelete.do", new MemberDeleteController());
		// �Խ��� ����
		list.put("BBSList.do", new BBSListController());
		list.put("BBSWrite.do", new BBSWriteController());
		list.put("BBSView.do", new BBSViewController());
		list.put("BBSUpdate.do", new BBSUpdateController());
		list.put("BBSDelete.do", new BBSDeleteController());
		

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
