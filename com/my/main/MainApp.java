package com.my.main;

import java.util.Scanner;

import com.my.view.MainView;

public class MainApp {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		while (true) {
			MainView.startMenu();
			String startmenu = sc.nextLine();
			switch (startmenu) {
			case "1":
				MainView.insert();
				break;
			case "2":
				MainView.login();
				break;
			case "9":
				System.out.println("========���α׷� ����========");
				System.exit(0);
			default:
				System.out.println("####�ùٸ� ��ȣ�� �Է��ϼ���#####");
			}
		}

	}

}
