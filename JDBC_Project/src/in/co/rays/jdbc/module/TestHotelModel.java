package in.co.rays.jdbc.module;

public class TestHotelModel {
	public static HotelModel model = new HotelModel();

	public static void main(String[] args) {
//		testadd();
//		testupdate();
//		testdelete();
		testsearch();
	}

	private static void testadd() {
		HotelBean bean = new HotelBean();
		bean.setRoomNo(105);
		bean.setRoomType(" Super Duper Duplex Room");
		bean.setFloor(5);
		bean.setPricePerNight(10000.0);
		bean.setAvailability(false);

		model.add(bean);
	}

	private static void testupdate() {
		HotelBean bean = new HotelBean();
		bean.setRoomType("Non-AC Room");
		bean.setFloor(1);
		bean.setPricePerNight(2500);
		bean.setAvailability(true);
		bean.setRoomNo(101);
		model.update(bean);
	}

	private static void testdelete() {
		HotelBean bean = new HotelBean();
		bean.setRoomNo(105);
		model.delete(bean);

	}

	private static void testsearch() {

	}

}
