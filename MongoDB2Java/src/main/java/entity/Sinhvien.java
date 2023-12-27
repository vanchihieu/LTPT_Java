package entity;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class Sinhvien {
	@BsonId
	private String mssv;
	@BsonProperty("ho_sv")
	private String hoSV; // ho_sv
	private String ten;
	private int tuoi;
	public Sinhvien() {
		super();
	}
	public Sinhvien(String mssv, String ho, String ten, int tuoi) {
		super();
		this.mssv = mssv;
		this.hoSV = ho;
		this.ten = ten;
		this.tuoi = tuoi;
	}
	public String getMssv() {
		return mssv;
	}
	public void setMssv(String mssv) {
		this.mssv = mssv;
	}
	public String getHo() {
		return hoSV;
	}
	public void setHo(String ho) {
		this.hoSV = ho;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public int getTuoi() {
		return tuoi;
	}
	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}
	@Override
	public String toString() {
		return "Sinhvien [mssv=" + mssv + ", ho=" + hoSV + ", ten=" + ten + ", tuoi=" + tuoi + "]";
	}
	
	
}
