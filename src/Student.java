

public class Student {
private int ID;
private String Name,Major;
private Double GPA;
private int Status;
public Student() {
	this(0,"null","null",4,1);
}
public Student(int ID,String Name,String Major,double GPA,int Status) {
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
public int getStatus() {
	return Status;
}

}
