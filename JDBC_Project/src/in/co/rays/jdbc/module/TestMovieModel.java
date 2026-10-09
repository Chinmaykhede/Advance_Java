package in.co.rays.jdbc.module;

import java.util.Iterator;
import java.util.List;

public class TestMovieModel {
	public static final MovieModel model = new MovieModel();

	public static void main(String[] args) {
//		testadd();
//		testupdate();
//		testdelete();
//		testsearch();
//		testfindByPk();
	}

	private static void testfindByPk() {
		MovieBean bean = new MovieBean();
		bean = model.findByPk(5);
		System.out.println(bean.getMovieId());
		System.out.println(bean.getTitle());
		System.out.println(bean.getGenre());
		System.out.println(bean.getDuration());
		System.out.println(bean.getRating());
	}

	private static void testadd() {
		MovieBean bean = new MovieBean();
		// bean.setMovieId(1);
		bean.setTitle("3 Idiots");
		bean.setGenre("Comedy");
		bean.setDuration(170);
		bean.setRating(8.4);
		model.add(bean);

	}

	private static void testupdate() {
		MovieBean bean = new MovieBean();
		bean.setTitle("Javan");
		bean.setGenre("Action");
		bean.setDuration(169);
		bean.setRating(6.9);
		bean.setMovieId(6);
		model.update(bean);

	}

	private static void testdelete() {
		MovieBean bean = new MovieBean();
		bean.setMovieId(7);
		model.delete(bean);

	}

	private static void testsearch() {
		MovieBean bean = new MovieBean();
		List<MovieBean> list = model.search(bean, 1, 5);
		Iterator<MovieBean> it = list.iterator();
		while (it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getMovieId());
			System.out.println(bean.getTitle());
			System.out.println(bean.getGenre());
			System.out.println(bean.getDuration());
			System.out.println(bean.getRating());
			System.out.println("-------------------------");
		}
	}

}
