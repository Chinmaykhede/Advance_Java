package in.co.rays.jdbc.module;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestEventModel {
	public final static EventModel model = new EventModel();
	public static void main(String[] args) throws ParseException {
//		testadd();
//		testupdate();
//		testdelete();
	}

	private static void testadd() throws ParseException {
		EventBean bean = new EventBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//bean.setEventId(1);
		bean.setEventName("Coding Competition");
		bean.setEventDate(new java.sql.Date(sdf.parse("2026-08-14").getTime()));
		bean.setVenue("Computer Lab");
		bean.setOrganizer("IT Department");
		model.add(bean);
	}

	private static void testupdate() throws ParseException {
		EventBean bean = new EventBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        bean.setEventName("Java Workshop");
		bean.setEventDate(new java.sql.Date(sdf.parse("2026-10-01").getTime()));
		bean.setVenue("Seminar Hall A");
		bean.setOrganizer("Rays Tech");
		bean.setEventId(5);
		model.update(bean);
	}

	private static void testdelete() {
		EventBean bean = new EventBean();
		bean.setEventId(7);
		model.delete(bean);
	}

}
