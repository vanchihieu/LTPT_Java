package demo;

import com.google.gson.Gson;

import entity.Lophoc;
import entity.Sinhvien;

public class SinhvienToJsonTask {
	private static final Gson GSON = new Gson();

	public static void main(String[] args) {
		Lophoc lh = new Lophoc("10A", "Lop 10A");
		Sinhvien sv = new Sinhvien("12343", "Lan", 14, lh);
		String json = GSON.toJson(sv);
		System.out.println(json);
	}
}
