package in.co.raya.jdbc.preparedstatement;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestOrderModel {
	public static void main(String[] args) throws ParseException {
		//testadd();
		testdelete();
	}

	private static void testdelete() {
		OrderModel om = new OrderModel();
		OrderBean bean = new OrderBean();
		bean.setOrderId(13);
		om.delete(bean);
		
	}

	private static void testadd() throws ParseException {
		OrderModel om = new OrderModel();
		OrderBean bean = new OrderBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//bean.setOrderId(2);
		bean.setOrderDate(sdf.parse("2026-02-20"));
		bean.setAmount(2100.75);
		bean.setStatus("Completed");
		bean.setCustomerId(513L);
		om.add(bean);
		
	}

}
