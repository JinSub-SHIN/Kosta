package com.my.controller;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.ModifyException;
import com.my.exception.NotFoundException;
import com.my.service.CustomerService;
import com.my.session.Session;
import com.my.session.SessionSet;
import com.my.view.View;
import com.my.vo.Customer;

public class CustomerController {

	public static void loginControl(String id, String pwd) {

		try {
			CustomerService.getInstance().login(id, pwd);
//			View.sucessView("�α��μ���");
			SessionSet ss = SessionSet.getInstance();
			Session session = new Session(id);
			ss.add(session);
		} catch (NotFoundException e) {
//			e.printStackTrace();
			View.failView(e.getMessage());
		}
	}

	public static void findByIdControl(String id) {

		try {
			Customer customer = CustomerService.getInstance().findById(id);
			View.sucessView("��ȸ�Ͻ�" + id + " �� �ش������Դϴ�");
			View.sucessView(customer.toString());
		} catch (NotFoundException e) {
//			e.printStackTrace();
			View.failView(e.getMessage());
		}
	}

	public static void findAllControl() {
		try {
			List<Customer> list = CustomerService.getInstance().findAll();
			View.sucessView("��ü�˻��Դϴ�");
			for (Customer c : list) {
				View.sucessView(c.toString());
			}
		} catch (NotFoundException e) {
//			e.printStackTrace();
			View.failView(e.getMessage());
		}
	}

	public static void findByNameControl(String word) {

		try {
			List<Customer> list = CustomerService.getInstance().findByName(word);
			View.sucessView(word + "�� ���Ե� �����Դϴ�");
			for (Customer c : list) {
				View.sucessView(c.toString());
			}
		} catch (NotFoundException e) {
//			e.printStackTrace();
			View.failView(e.getMessage());
		}
	}

	public static void insertControl(Customer customer) {

		try {
			CustomerService.getInstance().register(customer);
			View.sucessView("ȸ�������� ȯ���մ�" + "��");
		} catch (AddException e) {
//			e.printStackTrace();
			View.failView(e.getMessage());
		}
	}

	public static void updateControl(Customer customer) {
		try {
			CustomerService.getInstance().update(customer);
			View.sucessView("===================================================");
			View.sucessView("         < < <��������Ϸ�Ǿ����ϴ� > > >         ");
			View.sucessView("===================================================");
		} catch (ModifyException e) {
//			e.printStackTrace();
			View.failView(e.getMessage());
		}
	}

	public static void logout(String id) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);
		ss.remove(session);

	}

	public static void withdraw(String id) {
		try {
			CustomerService.getInstance().withdraw(id);
			View.sucessView("===================================================");
			View.sucessView("         < < <Ż���۾��Ϸ�Ǿ����ϴ� > > >         ");			
		} catch (ModifyException e) {
//			e.printStackTrace();
			View.failView(e.getMessage());
		}
	}

}
