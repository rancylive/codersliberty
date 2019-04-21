package codersliberty.pojo;

public class Student extends Person {
	Course course;
	private String institute;
	int roll;
	Integer batch;
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public String getInstitute() {
		return institute;
	}
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	public int getRoll() {
		return roll;
	}
	public void setRoll(int roll) {
		this.roll = roll;
	}
	public Integer getBatch() {
		return batch;
	}
	public void setBatch(Integer batch) {
		this.batch = batch;
	}
	
	
}
