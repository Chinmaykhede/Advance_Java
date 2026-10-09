package in.co.rays.jdbc.module;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class TestVehicleModel {
	public final static VehicleModel model = new VehicleModel();

	public static void main(String[] args) throws SQLException {

//		testadd();
//		testupdate();
//		testdelete();
		testsearch();
	}

	private static void testadd() {
		VehicleBean bean = new VehicleBean();
		bean.setBrand("Honda");
		bean.setModel("City");
		bean.setColor("White");
		bean.setYear(2021);
		model.add(bean);
	}

	private static void testupdate() {
		VehicleBean bean = new VehicleBean();
		bean.setBrand("Mercedes-Benz");
		bean.setModel("A-Class");
		bean.setColor("Black");
		bean.setYear(2023);
		bean.setVehicleId(10);
		model.update(bean);
	}

	private static void testdelete() {
		VehicleBean bean = new VehicleBean();
		bean.setVehicleId(10);
		model.delete(bean);
	}

	private static void testsearch() throws SQLException {
		VehicleBean bean = new VehicleBean();
		List list = model.search(bean, 1, 5);
		Iterator<VehicleBean> it = list.iterator();
		while (it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getVehicleId());
			System.out.println(bean.getBrand());
			System.out.println(bean.getModel());
			System.out.println(bean.getColor());
			System.out.println(bean.getYear());
			System.out.println("-------------------------");
		}
	}

}
