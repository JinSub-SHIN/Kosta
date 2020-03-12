package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.ModifyException;
import com.my.exception.NotFoundException;
import com.my.sql.MyConnection;
import com.my.vo.Customer;

public class CustomerDAO {

	public void insert(Customer customer) throws AddException {

		Connection con = null;
		PreparedStatement pstmt = null;

		// 1)DB����
		try {
			con = MyConnection.getConnection();

			// 2)SQL�۽�

			String insertSQL = "INSERT INTO customer(id, pwd, name, gender) VALUES (?,?,?,?)";

			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, customer.getId());
			pstmt.setString(2, customer.getPwd());
			pstmt.setString(3, customer.getName());
			pstmt.setString(4, customer.getGender());
			pstmt.executeUpdate();

		} catch (SQLException e) {
//			e.printStackTrace();
			if (e.getErrorCode() == 1) {
				throw new AddException("�̹� �����ϴ� ID�Դϴ�");
			}
			throw new AddException(e.getMessage());
		} catch (Exception e) {
//			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			MyConnection.close(con, pstmt, null);
		}

	}

	public void update(Customer customer) throws ModifyException {

		Connection con = null;
		Statement stmt = null;

		try {
			con = MyConnection.getConnection();
			String updateSQL1 = "UPDATE customer SET ";
			String updateSQL2 = " WHERE id='" + customer.getId() + "'";
			boolean flag = false;
			if (customer.getPwd() != null) {
				updateSQL1 += "pwd='" + customer.getPwd() + "'";
				flag = true;
			}
			if (customer.getName() != null) {
				if (flag) {
					updateSQL1 += ",";
				}
				updateSQL1 += "name='" + customer.getName() + "'";
				flag = true;
			}
			if (customer.getGender() != null) {
				if (flag) {
					updateSQL1 += ",";
				}
				updateSQL1 += "gender='" + customer.getGender() + "'";
				flag = true;
			}
			if (customer.getDate() != null) {
				if (flag) {
					updateSQL1 += ",";
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
				String dt = sdf.format(customer.getDate());
				updateSQL1 += "reg_date='" + dt + "'";
				flag = true;
			}

			if(customer.getStatus() != 0) {
				if(flag) {
					updateSQL1 += ",";
				}
				updateSQL1 += "status=" + customer.getStatus();
				flag = true;
				
			}
			
			if (flag) {
				stmt = con.createStatement();
				stmt.executeUpdate(updateSQL1 + updateSQL2);
			}

		} catch (Exception e) {
//			e.printStackTrace();
			throw new ModifyException("����Ұ�");
		} finally {
			MyConnection.close(con, stmt, null);
		}

	}

	/**
	 * ���̵� �ش� ���� �˻��Ͽ� ��ȯ�Ѵ�.
	 * 
	 * @param id ���̵�
	 * @return ����ü
	 * @throws NotFoundException DB��������̰ų� ���̵� �ش� ���� ������ ���ܹ߻��Ѵ�...
	 */
	public Customer selectById(String id) throws NotFoundException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = MyConnection.getConnection();

			String selectByIdSQL = "SELECT * FROM customer WHERE id=?";
			pstmt = con.prepareStatement(selectByIdSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				Date date = rs.getDate("reg_date");
				int status = rs.getInt("status");
				return new Customer(id, pwd, name, gender, date, status);
			} else {
				throw new NotFoundException("ID�� �ش��ϴ� ���� �����ϴ�.");
			}

		} catch (Exception e) {
//			e.printStackTrace();
			throw new NotFoundException(e.getMessage());
		} finally {
			MyConnection.close(con, pstmt, rs);
		}

	}

	/**
	 * �̸��� �ش�ܾ ������ ������ �˻��Ѵ�.
	 * 
	 * @param word
	 * @return
	 * @throws NotFoundException
	 */
	public List<Customer> seletByName(String word) throws NotFoundException {

		List<Customer> list = new ArrayList<Customer>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = MyConnection.getConnection();
			String seletByName = "SELECT * FROM customer where name Like ?";
			pstmt = con.prepareStatement(seletByName);
			pstmt.setString(1, "%" + word + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Customer(rs.getString("id"), rs.getString("pwd"), word, rs.getString("gender"),
						rs.getDate("reg_date"), rs.getInt("status")));
			}
			if (list.size() == 0) {
				throw new NotFoundException("�����������ϴ�");
			}

		} catch (Exception e) {
//			e.printStackTrace();
			throw new NotFoundException(e.getMessage());
		} finally {
			MyConnection.close(con, pstmt, rs);
		}

		return list;
	}

	public List<Customer> selectAll() throws NotFoundException {

		List<Customer> list = new ArrayList<>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = MyConnection.getConnection();

			String selectAllSQL = "SELECT * FROM customer";
			pstmt = con.prepareStatement(selectAllSQL);
//			pstmt.setString(1, "customer");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				Date date = rs.getDate("reg_date");
				int status = rs.getInt("status");
				Customer customer = new Customer(id, pwd, name, gender, date, status);
				list.add(customer);
			}
			if (list.size() == 0) {
				throw new NotFoundException("�����������ϴ�");
			}

		} catch (Exception e) {
//			e.printStackTrace();
			throw new NotFoundException(e.getMessage());

		} finally {
			MyConnection.close(con, pstmt, rs);
		}

		return list;

	}
}
