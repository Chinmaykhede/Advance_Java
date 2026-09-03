package in.co.raya.jdbc.preparedstatement;

public class TestStudentModel {
	public static void main(String[] args) throws Exception {
		// testAdd();
		//testDelete();
		//testUpdate();
	}

	private static void testUpdate() throws Exception {
		StudentModel sm = new StudentModel();
		sm.update(105, "harshad@gmail.com");
		
	}

	private static void testDelete() throws Exception {
		StudentModel sm = new StudentModel();
		sm.delete(102);

	}

	private static void testAdd() throws Exception {
		StudentModel sm = new StudentModel();
		sm.add(107, "Harshit", 30, 80, "harshit@gmail.com");

	}

}
