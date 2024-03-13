package app;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import dao.LophocDao;
import dao.SinhvienDao;
import dao.SinhvienUtil;
import entity.Lophoc;
import entity.Sinhvien;

public class App {
	public static void main(String[] args) {
		MongoClient client = MongoClients.create();
		
		SinhvienDao sinhvienDao = new SinhvienDao(client);
		LophocDao lophocDao = new LophocDao(client);
		SinhvienUtil util = new SinhvienUtil(client);
		
		Sinhvien sv = new Sinhvien("523", "Van", "Hieu", 20);
		Sinhvien sv1 = new Sinhvien("126", "Phuong", "Huyen", 30);
		Sinhvien sv2 = new Sinhvien("635", "Nguyen", "Mai", 25);
		Sinhvien sv3 = new Sinhvien("987", "Be", "Em", 18);

		List<Sinhvien> dssv = Arrays.asList(sv,sv1,sv2,sv3);
		
		Map<Lophoc, Integer> map = util.getLophocSisoMax();
		map.entrySet().iterator().forEachRemaining(entry -> {
			System.out.println("lop hoc: "+entry.getKey());
			System.out.println("Si so thuc: "+entry.getValue());

		});
		
//		lophocDao.getDSLophocCLC()
//			.forEach(lh -> System.out.println(lh));
		
//		 sinhvienDao.getDSinhvien(2, 3)
//		 .forEach(s -> System.err.println(s));
		
//		boolean kq =sinhvienDao.xoaSinhvienByMssv("845");
//		System.out.println(kq);
		
//		sinhvienDao.capnhatSinhvien(sv2);
		
//		boolean kq =sinhvienDao.capnhapTuoiSV2("845", 39);
//		System.out.println(kq);
		
//		sinhvienDao.themDSSinhvien(dssv);
//		sinhvienDao.themSinhvien(sv );
		
		
	}
}
