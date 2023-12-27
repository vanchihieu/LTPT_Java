package entity;

public class Lophoc {
	private String maLop;
	private String tenLop;
	private int khoaHoc;
	private String maNganh;

	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	public String getTenLop() {
		return tenLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	public int getKhoaHoc() {
		return khoaHoc;
	}

	public void setKhoaHoc(int khoaHoc) {
		this.khoaHoc = khoaHoc;
	}

	public String getMaNganh() {
		return maNganh;
	}

	public void setMaNganh(String maNganh) {
		this.maNganh = maNganh;
	}

	public Lophoc(String maLop, String tenLop, int khoaHoc, String maNganh) {
		super();
		this.maLop = maLop;
		this.tenLop = tenLop;
		this.khoaHoc = khoaHoc;
		this.maNganh = maNganh;
	}

	public Lophoc() {
		super();
	}

	@Override
	public String toString() {
		return "Lophoc [maLop=" + maLop + ", tenLop=" + tenLop + ", khoaHoc=" + khoaHoc + ", maNganh=" + maNganh + "]";
	}

}
