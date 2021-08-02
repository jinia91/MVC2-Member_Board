package service;

import dao.BBSDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.BBSDTO;

// �Խ��� ���� ������ ����ϴ� ��
// 1. ��ü �Խù� ǥ��
// 2. �Խù� �ۼ�


public class BBSService {

	// �̱��� ���� ����//
		// ���ǹ��� ��ü �������� �޸� �����ϰ� ���°� ���ϱ� ����, ��ü�� �ϳ� �����ϰ� �̸� ��Ȱ���ϴ� ������
		
		private static BBSService BBSserv = new BBSService();  // static���ΰ�ü �ϳ��� �޸𸮿� �÷���
			private BBSService() {}									// ���� static ���� �ʿ��� ������
		public static BBSService getInstance() {  		// �ܺο��� �� ���� �ּҰ��� �ʿ��Ҷ� �����ϴ� �޼ҵ�. �̸� ���� ��ü�������� static���� �����
			return BBSserv;   							// �̱��水ü �ϳ��� ��� ��Ȱ���� �� ����. ���� ��������� ��ȭ�� ������ �ɾ����°� �����Ƿ� 
															// �߰����� ��ü������ �ǹ̾���
		}
		
		// �̱��� ���� ����//
		
		
		public BBSDAO dao = BBSDAO.getInstance();					 // 	
		
		
		
		// ��ü �Խù� �ҷ�����
		public ArrayList<BBSDTO> BBSList(){
			
			ArrayList<BBSDTO> list = null;
			
			try {
			 list = dao.BBSallList();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return list;
		}
		
		
		
		// �Խù� �ۼ�
		public void BBSWrite(BBSDTO dto) {
			
		 dao.BBSWrite(dto);	
			
		}

	
}


