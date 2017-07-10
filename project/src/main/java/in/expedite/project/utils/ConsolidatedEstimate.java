package in.expedite.project.utils;

public class ConsolidatedEstimate {

	private Long teamId;
	private String teamName;
	private Integer estimate;
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Integer getEstimate() {
		return estimate;
	}
	public void setEstimate(Integer estimate) {
		this.estimate = estimate;
	}
	@Override
	public String toString() {
		return "ConsolidatedEstimate [teamId=" + teamId + ", teamName=" + teamName + ", estimate=" + estimate + "]";
	}
	
	public ConsolidatedEstimate(Long teamId, String teamName, Integer estimate) {
		super();
		this.teamId = teamId;
		this.teamName = teamName;
		this.estimate = estimate;
	}
	
	
}
