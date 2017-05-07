package in.expedite.core.utils;

public enum ProjectDocType {

	LINK("link"),
	IMAGE("image");
	
	private String value;
	private ProjectDocType(String value){
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
