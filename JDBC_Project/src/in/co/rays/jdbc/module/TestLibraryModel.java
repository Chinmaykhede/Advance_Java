package in.co.rays.jdbc.module;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class TestLibraryModel {
	public static final LibraryModel model = new LibraryModel();

	public static void main(String[] args) throws SQLException {
//		testadd();
//		testupdate();
//		testdelete();
		testsearch();
	}

	private static void testadd() {
		LibraryBean bean = new LibraryBean();
		bean.setBookId(1);
		bean.setTitle("The Hobbit");
		bean.setAuthor("J.R.R.Tolkien");
		bean.setPrice(600.0);
		bean.setAvailability(false);
		model.add(bean);

	}

	private static void testupdate() {
		LibraryBean bean = new LibraryBean();
		bean.setTitle("");
		bean.setAuthor("");
		bean.setPrice(0);
		bean.setAvailability(true);
		bean.setBookId(0);
		model.update(bean);
	}

	private static void testdelete() {
		LibraryBean bean = new LibraryBean();
		bean.setBookId(0);
		model.delete(bean);

	}

	private static void testsearch() throws SQLException {
		LibraryBean bean = new LibraryBean();
		List list = model.search(bean, 1, 5);
		Iterator<LibraryBean> it = list.iterator();
		while (it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getBookId());
			System.out.println(bean.getTitle());
			System.out.println(bean.getAuthor());
			System.out.println(bean.getPrice());
			System.out.println(bean.isAvailability());
			System.out.println("----------------------------");

		}
	}

}
