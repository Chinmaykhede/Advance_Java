package in.co.raya.jdbc.preparedstatement;

import java.util.Iterator;
import java.util.List;

public class TestCustomerModel {
	public static void main(String[] args) throws Exception {
		// testadd();
		// testdelete();
		// testupdate();
		search();
	}

	private static void search() {
		CustomerModel cm = new CustomerModel();
		CustomerBean cb = new CustomerBean();
		List list = cm.search(cb, 1, 5);
		Iterator it = list.iterator();
		while (it.hasNext()) {
			cb = (CustomerBean) it.next();
			System.out.println(cb.getCustomerId());
			System.out.println(cb.getCustomerName());
			System.out.println(cb.getEmail());
			System.out.println(cb.getPhoneNo());
			System.out.println(cb.getAddress());
			System.out.println("--------------------------");

		}
	}

	private static void testupdate() {
		CustomerModel cm = new CustomerModel();
		CustomerBean cb = new CustomerBean();
		cb.setCustomerId(6);
		cb.setAddress("Dewas");
		cm.update(cb);

	}

	private static void testdelete() {
		CustomerModel cm = new CustomerModel();
		cm.delete(10);

	}

	private static void testadd() throws Exception {
		CustomerModel cm = new CustomerModel();
		CustomerBean cb = new CustomerBean();
		// cb.setCustomerId(3);
		cb.setCustomerName("Rohan");
		cb.setEmail("rohan@gmail.com");
		cb.setPhoneNo("9856779404");
		cb.setAddress("Delhi");
		cm.add(cb);

	}

}
