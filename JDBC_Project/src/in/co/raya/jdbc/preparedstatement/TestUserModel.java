package in.co.raya.jdbc.preparedstatement;

import java.text.SimpleDateFormat;

public class TestUserModel {
	public static void main(String[] args) throws Exception {
		// testcreate();
		// testadd();
		// testdelete();
		// testupdate();
		// testFindByPk();
		// testFindByLogin();
		//authenticate();
	}

	private static void authenticate() throws Exception {
		UserModel model = new UserModel();
		UserBean bean = new UserBean();
		bean = model.authenticate("amitverma@gmail.com", "Amit@123");
		if (bean != null) {
			System.out.println("Login Successful");
			System.out.println("Login Id : " + bean.getLoginId());
		} else {
			System.out.println("Invalid Login Id or Password");
		}

	}

	private static void testFindByLogin() throws Exception {
		UserModel model = new UserModel();
		UserBean bean = new UserBean();
		bean = model.findByLogin("amitverma@gmail.com");
		System.out.println(bean.getId());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getPassword());
		System.out.println(bean.getLoginId());
		System.out.println(bean.getDob());

	}

	private static void testFindByPk() throws Exception {
		UserModel model = new UserModel();
		UserBean bean = new UserBean();

		bean = model.findByPk(13);

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getPassword());
			System.out.println(bean.getLoginId());
			System.out.println(bean.getDob());
		} else {
			System.out.println("user not found");
		}

	}

	private static void testupdate() throws Exception {
		UserModel um = new UserModel();
		UserBean ub = new UserBean();
		ub.setFirstName("Priyaa");
		ub.setId(2);
		um.update(ub);

	}

	private static void testdelete() throws Exception {
		UserModel um = new UserModel();
		UserBean ub = new UserBean();
		ub.setId(15);
		um.delete(ub);

	}

	private static void testadd() throws Exception {
		UserModel um = new UserModel();
		UserBean ub = new UserBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ub.setId(15);
		ub.setFirstName("Hema");
		ub.setLastName("Singh");
		ub.setLoginId("hema15@gmail.com");
		ub.setPassword("Hema@123");
		ub.setDob(sdf.parse("1998-10-27"));
		um.add(ub);

	}

	private static void testcreate() throws Exception {
		UserModel um = new UserModel();
		um.createTable();
	}

}
