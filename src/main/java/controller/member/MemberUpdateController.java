package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.MemberDTO;
import controller.Controller;
import controller.HttpUtil;
import service.MemberService;

// ȸ������ ������ ������ �帧�� �ٷ�� ��Ʈ�ѷ�

// 1. ���� ����
// 2. ��ȿ�� üũ�� dto�� ���ε� 
// 3. update �������� �ش� ���� ������ ��� ��ȯ
// 4. ������� ��� ����


public class MemberUpdateController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		// 1. ����
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String birth = request.getParameter("birth");
		
		// 2. ��ȿ�� üũ
		if(id.isEmpty()||pwd.isEmpty()||email.isEmpty()||birth.isEmpty()) {
			
			request.setAttribute("error", "��� �׸��� �������� �����ֽñ� �ٶ��ϴ�!"); 
			HttpUtil.forward(request, response, "/member/MemberUpdate.jsp");
			return;
		}
		
		// ������ ��� ���ε�
		MemberDTO member = new MemberDTO();
		member.setId(id);
		member.setPwd(pwd);
		member.setEmail(email);
		member.setBirth(birth);
		
		//3. ���� ��ü �޼ҵ� ȣ��
		MemberService service = MemberService.getInstance();    // �̱������� ���� ��ü �ּ� ��ȯ
		service.MemberUpdate(member);								// ���� �޼ҵ� ȣ��

		// 4. ������� ������
		request.setAttribute("id", id);
		HttpUtil.forward(request, response, "/WEB-INF/result/member/MemberUpdate.jsp");


	}

}
