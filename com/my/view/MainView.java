package com.my.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.my.controller.CustomerController;
import com.my.session.Session;
import com.my.session.SessionSet;
import com.my.vo.Customer;

public class MainView {

	private static Scanner sc = new Scanner(System.in);
	
	public static void startMenu() {
		System.out.println("===================================================");
		System.out.println("==============1.����  2.�α���  9.����=============");
		System.out.println("===================================================");
		System.out.println("�����Ͻ� �۾��� ����>");
	}

	public static void loginMenu() {
		System.out.println("===================================================");
		System.out.println("=============1.����������  9.�α׾ƿ�==============");
		System.out.println("�����Ͻ� �۾��� ����>");
	}

	public static void updateMenu() {
		System.out.println("===================================================");
		System.out.println("=========1.�����ϱ�  2.Ż���ϱ�  9.�ڷΰ���========");
		System.out.println("�����Ͻ� �۾��� ����>");
	}

	public static void adminMenu() {
		System.out.println("===================================================");
		System.out.println("==1.���̵�˻�  2.�̸��˻�  3.��ü�˻� 9.�ڷΰ���==");
		System.out.println("===================================================");
		System.out.println("�����Ͻ� �۾��� ����>");
	}
	
	public static void backMenu() {
		System.out.println("===================================================");
		System.out.println("       < < < < �����޴��ε��ư��ϴ� > > > >        ");
	}

	public static void login() {
		System.out.println("================ << �α����۾� >> =================");
		System.out.println("ID: >");
		String id = sc.nextLine();
		System.out.println("��й�ȣ: >");
		String pwd = sc.nextLine();

		CustomerController.loginControl(id, pwd);

		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);

		if (session != null) { // �α��μ�������
			if ("admin".equals(id)) {
				System.out.println("================ << �����ڸ�� >> =================");
				while (true) {
					adminMenu();
					switch (sc.nextLine()) {
					case "1":
						findId();
						break;
					case "2":
						findName();
						break;
					case "3":
						findAll();
						break;
					case "9":
						CustomerController.logout(id);
						backMenu();
						return;
					}
				}

			} else {
				System.out.println("================ << �α��μ��� >> =================");
				while (true) {
					loginMenu();
					switch (sc.nextLine()) {
					case "1":
						CustomerController.findByIdControl(id);
						updateMenu();
						switch (sc.nextLine()) {
						case "1":
							update(id);
							break;
						case "2":
							withdraw(id);
							break;
						case "9":
							backMenu();
							break;
						}
						break;
					case "9":
//				ss.remove(session);
						CustomerController.logout(id);
						return;
					}
				}
			}
		}

	}

	public static void findId() {
		System.out.println("===================================================");
		System.out.println("              < < < �� �� �� �� �� > > >           ");
		System.out.println("===================================================");
		System.out.println("ID: >");
		String id = sc.nextLine();
		CustomerController.findByIdControl(id);
	}

	public static void findName() {
		System.out.println("===================================================");
		System.out.println("               < < < �� �� �� �� > > >             ");
		System.out.println("< < <�ش� ���ڰ� ���Ե� ������ ��� ��ȸ�˴ϴ�> > >");
		System.out.println("===================================================");
		System.out.println("Name/Word: >");
		String word = sc.nextLine();
		CustomerController.findByNameControl(word);

	}

	public static void findAll() {
		CustomerController.findAllControl();
	}

	public static void insert() {
		System.out.println("===================================================");
		System.out.println("               < < < ȸ �� �� �� > > >             ");
		System.out.println("===================================================");
		System.out.println("���� ID: >");
		String id = sc.nextLine();
		System.out.println("��й�ȣ: >");
		String pwd = sc.nextLine();
		System.out.println("�̸�: >");
		String name = sc.nextLine();
		System.out.println("����: >");
		String gender = sc.nextLine();

		Customer cu = new Customer(id, pwd, name, gender, null, 1);
		CustomerController.insertControl(cu);

	}

	public static void update(String id) {
		System.out.println("===================================================");
		System.out.println("               < < < �� �� �� �� > > >             ");
		System.out.println("  < < < �������� �����÷��� ENTER �Է��ϼ��� > > > ");
		System.out.println("===================================================");
		System.out.println("���� ��й�ȣ: >");
		String pwd = sc.nextLine();
		System.out.println("���� �̸�: >");
		String name = sc.nextLine();
		System.out.println("���� ����[M/F]: >");
		String gender = sc.nextLine();
		System.out.println("��������[YY-MM-DD]: >");
		String reg_dtS = sc.nextLine();

		Customer c = new Customer();
		c.setId(id);
		if (!"".equals(pwd)) {
			c.setPwd(pwd);
		}
		if (!"".equals(name)) {
			c.setName(name);
		}
		if (!"".equals(gender)) {
			c.setGender(gender);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		if (!"".equals(reg_dtS)) {
			Date reg_dt;
			try {
				reg_dt = sdf.parse(reg_dtS);
				c.setDate(reg_dt);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		CustomerController.updateControl(c);
	}

	public static void withdraw(String id) {
		System.out.println("===================================================");
		System.out.println("               < < < Ż �� �� �� > > >             ");
		System.out.println("===================================================");
		System.out.println("#######������ Ż���Ͻ÷��� 1���� �����ּ���########");
		System.out.println("#########�ڷ� ���÷��� �ƹ�Ű�� �����ּ���#########");
		System.out.println("�Է� : >");
		String right = sc.nextLine();
		switch (right) {
		case "1":
			CustomerController.withdraw(id);
			break;
		default:
			break;
		}

	}

}
