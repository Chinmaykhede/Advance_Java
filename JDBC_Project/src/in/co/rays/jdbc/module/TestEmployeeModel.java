package in.co.rays.jdbc.module;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestEmployeeModel {
	public static void main(String[] args) throws Exception {
		//create();
		//Add();
		//update();
		//delete();
		
	}

	private static void delete() {
		EmployeeModel em = new EmployeeModel();
		EmployeeBean eb = new EmployeeBean();
		eb.setEmployeeId(108);
		em.delete(eb);
	}

	private static void update() {
		EmployeeModel em = new EmployeeModel();
		EmployeeBean eb = new EmployeeBean();
		eb.setName("Imran");
		eb.setEmployeeId(108);
		em.update(eb);
	}

	private static void Add() throws ParseException {
		EmployeeModel em = new EmployeeModel();
		EmployeeBean eb = new EmployeeBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//eb.setEmployeeId(107);
		eb.setName("Harshit");
		eb.setDesignation("Data Engineer");
		eb.setSalary(65000);
		eb.setJoiningDate(sdf.parse("2025-01-03"));
		em.add(eb);
		
	}

	private static void create() throws Exception {
		EmployeeModel em = new EmployeeModel();
		em.create();
		
	}

}
