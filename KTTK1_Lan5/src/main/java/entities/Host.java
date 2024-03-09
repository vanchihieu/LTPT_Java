package entities;

import java.util.List;

public class Host {
	private int hostId;
	private String hostUrl;
	private String hostName;
	private List<String> hostVerifications;

	public Host() {
	}

	public Host(int hostId, String hostUrl, String hostName, List<String> hostVerifications) {
		this.hostId = hostId;
		this.hostUrl = hostUrl;
		this.hostName = hostName;
		this.hostVerifications = hostVerifications;
	}

	public int getHostId() {
		return hostId;
	}

	public void setHostId(int hostId) {
		this.hostId = hostId;
	}

	public String getHostUrl() {
		return hostUrl;
	}

	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public List<String> getHostVerifications() {
		return hostVerifications;
	}

	public void setHostVerifications(List<String> hostVerifications) {
		this.hostVerifications = hostVerifications;
	}

	@Override
	public String toString() {
		return "Host [hostId=" + hostId + ", hostUrl=" + hostUrl + ", hostName=" + hostName + ", hostVerifications="
				+ hostVerifications + "]";
	}

}
