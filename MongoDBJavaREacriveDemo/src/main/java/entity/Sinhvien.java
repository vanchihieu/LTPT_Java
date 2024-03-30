package entity;

public class Sinhvien {
	private String masv;
	private String ten;
	private int tuoi;
	
	private Lophoc lophoc;
	
	public Sinhvien() {
	}

	public Sinhvien(String masv, String ten, int tuoi, Lophoc lophoc) {
		super();
		this.masv = masv;
		this.ten = ten;
		this.tuoi = tuoi;
		this.lophoc = lophoc;
	}

	public String getMasv() {
		return masv;
	}

	public void setMasv(String masv) {
		this.masv = masv;
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

	public Lophoc getLophoc() {
		return lophoc;
	}

	public void setLophoc(Lophoc lophoc) {
		this.lophoc = lophoc;
	}

	@Override
	public String toString() {
		return "Sinhvien [masv=" + masv + ", ten=" + ten + ", tuoi=" + tuoi + ", lophoc=" + lophoc + "]";
	}

	
}
