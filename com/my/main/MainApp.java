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
				System.out.println("========프로그램 종료========");
				System.exit(0);
			default:
				System.out.println("####올바른 번호를 입력하세요#####");
			}
		}

	}

}
