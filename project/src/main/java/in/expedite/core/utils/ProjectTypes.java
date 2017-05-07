package in.expedite.core.utils;

public enum ProjectTypes {

	PROJECT("project"),
	STORY("story"),
	PROGRAM("program");
	
	private String value;
	
	private ProjectTypes(String value) {
		this.value=value;
	}
	
	public String getValue(){
		return this.value;
	}
	
	@Override
	public String toString(){
		return this.value;
	}

	public String getName(){
		return this.name();
	}
}
