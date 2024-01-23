package entity;
//{
//    "StateName": "Alabama",
//    "Abbreviation": "AL",
//    "Capital": "Montgomery",
//    "Statehood": 1819,
//    "ID": 1
//  }
public class State {
	private String stateName;
	private String abbreviation;
	private String capital;
	private int statehood;
	private int id;
	
	public State() {
	}

	public State(String stateName, String abbreviation, String capital, int statehood, int id) {
		super();
		this.stateName = stateName;
		this.abbreviation = abbreviation;
		this.capital = capital;
		this.statehood = statehood;
		this.id = id;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public int getStatehood() {
		return statehood;
	}

	public void setStatehood(int statehood) {
		this.statehood = statehood;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "State [stateName=" + stateName + ", abbreviation=" + abbreviation + ", capital=" + capital
				+ ", statehood=" + statehood + ", id=" + id + "]";
	}
	
	
	
}
