package in.co.rays.jdbc.module;

public class TestCustomerModel {
	public static void main(String[] args) throws Exception {
		testadd();

	}

	private static void testadd() throws Exception {
		CustomerModel m = new CustomerModel();
		CustomerBean b = new CustomerBean();

		//b.setCustomerId(1);
		b.setCustomerName("Radha");
		b.setEmail("Radha@gmail.com");
		b.setPhoneNo("1234567812");
		b.setAddress("Mhow");
		m.add(b);

	}

}
