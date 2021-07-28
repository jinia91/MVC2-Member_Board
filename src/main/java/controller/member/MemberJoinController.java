package controller.member;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import beans.MemberDTO;
import controller.Controller;
import controller.HttpUtil;
import service.MemberService;


public class MemberJoinController implements Controller {

	// ȸ������ �帧�� ������ �ٷ�� ������Ʈ�ѷ�
	
	// ������ 1. ȸ������������ ���� ��ȿ�� ���� üũ
	// 2. üũ ����� �ش� ������ ȸ�� ��� ����
	// 3. ȸ�������� ���õ� �����Ͻ� ����(��) memberservice �̱��� ��ü�ּҰ��� �޾ƿ� ���� Join �޼ҵ带 ����
	// 4. ������� ��ȯ�޾� ������� ������
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ���� ����
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String birth = request.getParameter("birth");
		String gender = request.getParameter("gender");
		
		// 1. ��ȿ�� üũ - 1) ����˻� 2) ���� �߰����� ����Ʈ��/��� ��� �ʿ�
		if(id.isEmpty()||pwd.isEmpty()||email.isEmpty()||birth.isEmpty()) {
			
			request.setAttribute("error", "��� �׸��� �������� �����ֽñ� �ٶ��ϴ�!");
			HttpUtil.forward(request, response, "/MemberJoinInput.jsp");
			return;
		}
		
		// 2. ��� ������ ���ε�
		MemberDTO member = new MemberDTO();
		member.setId(id);
		member.setPwd(pwd);
		member.setEmail(email);
		member.setBirth(birth);
		member.setGender(gender);
		
		// 3. ���� ��ü �޼ҵ� ȣ��
		MemberService service = MemberService.getInstance();    // �̱������� ���� ��ü �ּ� ��ȯ
		service.MemberJoin(member);								// ���� �޼ҵ� ȣ��

		
		// 4. ������� ������
		request.setAttribute("id", id);
		HttpUtil.forward(request, response, "/WEB-INF/result/member/MemberJoinOutput.jsp");
		
	}

}
