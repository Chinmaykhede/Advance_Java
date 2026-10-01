package in.co.rays.jdbc.module;

public class TestBankingModel {
	public static BankingModel model = new BankingModel();

	public static void main(String[] args) {
//		testadd();
//		testupdate();
		testdelete();
//		testsearch();
	}

	private static void testadd() {
		BankingBean bean = new BankingBean();
		bean.setAccountNo(9876543214l);
		bean.setHolderName("Karuna");
		bean.setAccountType("Current");
		bean.setBalance(5067.00);
		bean.setBranch("Indore");
		model.add(bean);
	}

	private static void testupdate() {
		BankingBean bean = new BankingBean();
		bean.setAccountNo(9876543214l);
		bean.setHolderName("Karuna Yadav");
		bean.setAccountType("Current");
		bean.setBalance(5067.00);
		bean.setBranch("Indore");
		bean.setId(6);
	}

	private static void testdelete() {
		BankingBean bean = new BankingBean();
		bean.setId(3);
		model.delete(bean);
	}

	
}
