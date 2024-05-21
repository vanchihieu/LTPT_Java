package app;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import dao.NhanVienDAO;
import dao.TaiKhoanDAO;

public class Test {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		String url = "rmi://192.168.2.248:2008/";
		TaiKhoanDAO taiKhoanDao = (TaiKhoanDAO)Naming.lookup(url + "taiKhoanDao");
		NhanVienDAO nhanVienDao = (NhanVienDAO)Naming.lookup(url + "nhanVienDao");
//		nhanVienDao.getAllNhanVien().forEach(nv -> System.out.println(nv.getQuanLy()==null?"null":nv.getQuanLy().getTenNhanVien()));
//		nhanVienDao.getAllNhanVienQuanLy().forEach(nv -> System.out.println(nv.getTenNhanVien()));
	}
}
