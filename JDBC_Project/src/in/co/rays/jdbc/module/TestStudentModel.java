package in.co.rays.jdbc.module;

public class TestStudentModel {
	public static void main(String[] args) {
		//testadd();
		//testupdate();
		// testdelete();
		//search(); 
	}

	private static void search() {
		
	}

	private static void testdelete() {
		StudentModel sm = new StudentModel();
		StudentBean sb = new StudentBean();
		sb.setStudentId(6);
		sm.delete(sb);
		
	}

	private static void testupdate() {
		StudentModel sm = new StudentModel();
		StudentBean sb = new StudentBean();
		sb.setName("Ramu");
		sb.setStudentId(1);
		sm.update(sb);
		
	}

	private static void testadd() {
		StudentModel sm = new StudentModel();
		StudentBean sb = new StudentBean();
		//sb.setStudentId(1);
		sb.setName("Rohit");
		sb.setEmail("rohit@gmail.com");
		sb.setMobileNo("9876543214");
		sb.setCourse("Java Script");
		sm.add(sb);

	}

}
