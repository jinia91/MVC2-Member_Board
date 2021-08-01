package service;

import dao.MemberDAO;
import beans.MemberDTO;

// ȸ�������� ���� ������ ����ϴ� ��
// 1. ȸ������ ����
// 2. �α��� ����
// 3. ȸ����й�ȣ ã�� ����
// 4. ȸ������ ���� �ҷ����� ����
// 5. ȸ������ ���� ����
// 6. ȸ������ ���� ����

public class MemberService {

	// �̱��� ���� ����//
	// ���ǹ��� ��ü �������� �޸� �����ϰ� ���°� ���ϱ� ����, ��ü�� �ϳ� �����ϰ� �̸� ��Ȱ���ϴ� ������
	
	private static MemberService Memberserv = new MemberService();  // static���ΰ�ü �ϳ��� �޸𸮿� �÷���
		private MemberService() {}									// ���� static ���� �ʿ��� ������
	public static MemberService getInstance() {  		// �ܺο��� �� ���� �ּҰ��� �ʿ��Ҷ� �����ϴ� �޼ҵ�. �̸� ���� ��ü�������� static���� �����
		return Memberserv;   							// �̱��水ü �ϳ��� ��� ��Ȱ���� �� ����. ���� ��������� ��ȭ�� ������ �ɾ����°� �����Ƿ� 
														// �߰����� ��ü������ �ǹ̾���
	}
	
	// �̱��� ���� ����//
	
	
	public MemberDAO dao = MemberDAO.getInstance();					 // ȸ������ ������ �ʿ��� DAO ȣ��, ���ó� ���� �̱��� ����	
	
	//1. ȸ������ ����
	public void MemberJoin(MemberDTO member) {
		dao.memberJoin(member);						// ȸ�������� DB�� �����ϴ� DAO�� �޼ҵ� ȣ��
	}
	
	//2. �α��� ����
	public int MemberLogin(String id, String pwd) {
		int loginResult = dao.memberLogin(id,pwd);  // 1,0,-1�� ���� ����� ��ȯ
		
		return loginResult;
	}
	
	//3. ȸ�� ��й�ȣ ã��
	public String memberSearch(String id, String email) {
		String pwd = dao.memberSearch(id, email);
		return pwd;
	}
	
	
	//4. ȸ�� ���� ���� �ҷ�����
	public MemberDTO memberSearchAll(String id, String pwd) {
		MemberDTO member = dao.memberSearchAll(id, pwd);
		return member;
	}
	
	//5. ȸ������ �����ϱ�
	public void MemberUpdate(MemberDTO member) {
		dao.memberUpdate(member);
	}
	
	//6. ȸ������ ����
	public void MemberDelete(String id, String pwd) {
		
		dao.memberDelete(id,pwd);
		
	}
	
}
