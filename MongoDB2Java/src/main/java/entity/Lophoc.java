package entity;

public class Lophoc {
	private String msLop;
	private String tenLop;
	private int sisoDukien;

	public Lophoc() {
		super();
	}

	public Lophoc(String msLop, String tenLop, int sisoDukien) {
		super();
		this.msLop = msLop;
		this.tenLop = tenLop;
		this.sisoDukien = sisoDukien;
	}

	public String getMsLop() {
		return msLop;
	}

	public void setMsLop(String msLop) {
		this.msLop = msLop;
	}

	public String getTenLop() {
		return tenLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	public int getSisoDukien() {
		return sisoDukien;
	}

	public void setSisoDukien(int sisoDukien) {
		this.sisoDukien = sisoDukien;
	}

	@Override
	public String toString() {
		return "Lophoc [msLop=" + msLop + ", tenLop=" + tenLop + ", sisoDukien=" + sisoDukien + "]";
	}

}
