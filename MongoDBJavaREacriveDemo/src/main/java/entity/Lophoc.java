package entity;

public class Lophoc {
	private String malop;
	private String tenlop;
	
	public Lophoc() {
		// TODO Auto-generated constructor stub
	}
	
	public Lophoc(String malop, String tenlop) {
		super();
		this.malop = malop;
		this.tenlop = tenlop;
	}
	public String getMalop() {
		return malop;
	}
	public void setMalop(String malop) {
		this.malop = malop;
	}
	public String getTenlop() {
		return tenlop;
	}
	public void setTenlop(String tenlop) {
		this.tenlop = tenlop;
	}
	@Override
	public String toString() {
		return "Lophoc [malop=" + malop + ", tenlop=" + tenlop + "]";
	}
	
	
	
}
