package ntqjteam.collectionframework.map;

public class Module {
	private String school;
	private String[] prefix;
	
	public Module(String school, String[] prefix) {
	    super();
	    this.school = school;
	    this.prefix = prefix;
    }
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String[] getPrefix() {
		return prefix;
	}
	public void setPrefix(String[] prefix) {
		this.prefix = prefix;
	}
}
