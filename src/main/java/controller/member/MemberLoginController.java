package controller.member;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import beans.MemberDTO;
import controller.Controller;
import controller.HttpUtil;
import service.MemberService;


// �α��� �帧�� ������ �ٷ�� ��Ʈ�ѷ�

//������ 1. �α��ο� ���� ���̵�, ��й�ȣ ��ȿ�� ����üũ 
// 2. ��ȿ�� üũ ����� ��� ���ε�
// 3. �����Ͻ� ������ �ش� �α��� ���� ����
// 4. ���� ó�� ����� ������� ������

public class MemberLoginController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ��������
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		// 1. ��ȿ�� üũ - 1) ����˻� 2) ���� �߰����� ����Ʈ��/��� ��� �ʿ�
		if(id.isEmpty()||pwd.isEmpty()) {
			
			request.setAttribute("error", "��� �׸��� �������� �����ֽñ� �ٶ��ϴ�!"); 
			HttpUtil.forward(request, response, "/member/MemberLogin.jsp");
			return;
		
		}
		
		// 2. ��� ������ ���ε�
		MemberDTO member = new MemberDTO();
		member.setId(id);
		member.setPwd(pwd);
		

		// 3. ���� ��ü �޼ҵ� ȣ��
		MemberService service = MemberService.getInstance();    // �̱������� ���� ��ü �ּ� ��ȯ
		int loginResult = service.MemberLogin(member.getId(),member.getPwd());	// �α��� �޼ҵ� ȣ���Ͽ� ����� ��ȯ
		
		
		
		// 4. ������� ���� ��� �帧 ����
		// 1 == �α��� ����, 0 == ���Ʋ��, -1 == ���̵� ����
		
		if(loginResult==1) {
			request.setAttribute("loginResult", loginResult);
			HttpSession session = request.getSession();
			session.setAttribute("sessionID", id);
			HttpUtil.forward(request, response, "/WEB-INF/result/member/LoginSuc.jsp");
			
		}
		else {
			request.setAttribute("loginResult", loginResult);
			HttpUtil.forward(request, response, "/member/MemberLogin.jsp");
		}
			
	}

}
