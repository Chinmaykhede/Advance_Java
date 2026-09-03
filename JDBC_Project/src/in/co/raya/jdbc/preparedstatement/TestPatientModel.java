package in.co.raya.jdbc.preparedstatement;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestPatientModel {
	public static void main(String[] args) throws Exception {
		// testcreate();
		// testAdd();
		// testdelete();
		// testupdate();
	}

	private static void testupdate() throws Exception {
		PatientModel pm = new PatientModel();
		BeanPatient bp = new BeanPatient();
		bp.setPatientName("Yuvraj Patel");
		bp.setPatientId(1006);
		pm.update(bp);

	}

	private static void testdelete() throws Exception {
		PatientModel pm = new PatientModel();
		BeanPatient bp = new BeanPatient();
		bp.setPatientId(1006);
		pm.delete(bp);

	}

	private static void testcreate() throws Exception {
		PatientModel pm = new PatientModel();
		pm.create();

	}

	private static void testAdd() throws Exception {
		PatientModel pm = new PatientModel();
		BeanPatient bp = new BeanPatient();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		bp.setPatientId(1007);
		bp.setPatientName("Vivek Yadav");
		bp.setDisease("Malaria");
		bp.setDoctorName("Dr.Hema Sharma");
		bp.setAdmissionDate(sdf.parse("2026-09-011"));
		pm.add(bp);
	}

}
