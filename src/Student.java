
public class Student {
private int ID;
private String Name,Major;
private Double GPA;
private boolean Status;
public Student() {
	this(0,"","",4,true);
}
public Student(int ID,String Name,String Major,double GPA,boolean Status) {
	this.ID=ID;
	this.Name=Name;
	this.Major=Major;
	this.GPA=GPA;
	this.Status=Status;
}
public int getID() {
	return ID;
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
public boolean isStatus() {
	return Status;
}

}
