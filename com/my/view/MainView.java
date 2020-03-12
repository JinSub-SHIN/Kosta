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
		System.out.println("==============1.가입  2.로그인  9.종료=============");
		System.out.println("===================================================");
		System.out.println("수행하실 작업의 숫자>");
	}

	public static void loginMenu() {
		System.out.println("===================================================");
		System.out.println("=============1.내정보보기  9.로그아웃==============");
		System.out.println("수행하실 작업의 숫자>");
	}

	public static void updateMenu() {
		System.out.println("===================================================");
		System.out.println("=========1.수정하기  2.탈퇴하기  9.뒤로가기========");
		System.out.println("수행하실 작업의 숫자>");
	}

	public static void adminMenu() {
		System.out.println("===================================================");
		System.out.println("==1.아이디검색  2.이름검색  3.전체검색 9.뒤로가기==");
		System.out.println("===================================================");
		System.out.println("수행하실 작업의 숫자>");
	}
	
	public static void backMenu() {
		System.out.println("===================================================");
		System.out.println("       < < < < 이전메뉴로돌아갑니다 > > > >        ");
	}

	public static void login() {
		System.out.println("================ << 로그인작업 >> =================");
		System.out.println("ID: >");
		String id = sc.nextLine();
		System.out.println("비밀번호: >");
		String pwd = sc.nextLine();

		CustomerController.loginControl(id, pwd);

		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);

		if (session != null) { // 로그인성공상태
			if ("admin".equals(id)) {
				System.out.println("================ << 관리자모드 >> =================");
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
				System.out.println("================ << 로그인성공 >> =================");
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
		System.out.println("              < < < 아 이 디 검 색 > > >           ");
		System.out.println("===================================================");
		System.out.println("ID: >");
		String id = sc.nextLine();
		CustomerController.findByIdControl(id);
	}

	public static void findName() {
		System.out.println("===================================================");
		System.out.println("               < < < 이 름 검 색 > > >             ");
		System.out.println("< < <해당 글자가 포함된 정보가 모두 조회됩니다> > >");
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
		System.out.println("               < < < 회 원 가 입 > > >             ");
		System.out.println("===================================================");
		System.out.println("가입 ID: >");
		String id = sc.nextLine();
		System.out.println("비밀번호: >");
		String pwd = sc.nextLine();
		System.out.println("이름: >");
		String name = sc.nextLine();
		System.out.println("성별: >");
		String gender = sc.nextLine();

		Customer cu = new Customer(id, pwd, name, gender, null, 1);
		CustomerController.insertControl(cu);

	}

	public static void update(String id) {
		System.out.println("===================================================");
		System.out.println("               < < < 정 보 변 경 > > >             ");
		System.out.println("  < < < 변경하지 않으시려면 ENTER 입력하세요 > > > ");
		System.out.println("===================================================");
		System.out.println("변경 비밀번호: >");
		String pwd = sc.nextLine();
		System.out.println("변경 이름: >");
		String name = sc.nextLine();
		System.out.println("변경 성별[M/F]: >");
		String gender = sc.nextLine();
		System.out.println("가입일자[YY-MM-DD]: >");
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
		System.out.println("               < < < 탈 퇴 하 기 > > >             ");
		System.out.println("===================================================");
		System.out.println("#######정말로 탈퇴하시려면 1번을 눌러주세요########");
		System.out.println("#########뒤로 가시려면 아무키나 눌러주세요#########");
		System.out.println("입력 : >");
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
