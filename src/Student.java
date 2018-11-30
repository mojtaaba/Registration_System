

public class Student {
private int ID;
private String Name,Major;
private Double GPA;
private int Status;
private int totalCredit;
public Student() {
	this(0000,"null","null",4,1,29);
}
public Student(int ID,String Name,String Major,double GPA,int Status,int totalCredit) {
	this.ID=ID;
	this.Name=Name;
	this.Major=Major;
	this.GPA=GPA;
	this.Status=Status;
	this.totalCredit=totalCredit;
}
public int getID() {
	return ID;
}
public int gettotalCredit() {
	return this.totalCredit;
}
public String getName() {
	return Name;
}
public String getMajor() {
	return Major;
}
public Double getGPA() {
	return GPA;
}
public int getStatus() {
	return Status;
}
public void settotalCredit(int i) {
	totalCredit=i;
}

}
