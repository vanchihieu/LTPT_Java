package app;

import java.util.Map;

import dao.ZipDao;

public class FindStateByMaxPop {
	public static void main(String[] args) {
		ZipDao dao = new ZipDao();
		Map<String, Integer> result = dao.findStateByMaxPop();
		System.out.println(result);
	}
}
