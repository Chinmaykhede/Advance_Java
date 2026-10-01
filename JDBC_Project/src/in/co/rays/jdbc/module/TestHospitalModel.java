package in.co.rays.jdbc.module;

import java.util.Iterator;
import java.util.List;

public class TestHospitalModel {
	public static HospitalModel model = new HospitalModel();

	public static void main(String[] args) {

//		testadd();
//		testupdate();
		testdelete();
//		testsearch();
	}

	private static void testadd() {
		HospitalBean bean = new HospitalBean();
		bean.setPatientId(1);
		bean.setName("Thor");
		bean.setAge(1000);
		bean.setBloodGroup("Alrounder");
		bean.setDisease("Lazey");
		model.add(bean);

	}

	private static void testupdate() {
		HospitalBean bean = new HospitalBean();
		bean.setName("Rahul");
		bean.setPatientId(4);
		model.update(bean);

	}

	private static void testdelete() {
		HospitalBean bean = new HospitalBean();
		bean.setPatientId(5);
		model.delete(bean);

	}

	private static void testsearch() {
    HospitalBean bean = new HospitalBean();
    List list = model.search(bean, 1, 5);
    Iterator it = list.iterator();
    while(it.hasNext()) {
    	bean = (HospitalBean)it.next();
    	
    }
	}

}
