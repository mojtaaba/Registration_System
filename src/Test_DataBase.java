

public class Test_DataBase {

	public static void main(String[] args) throws Exception {
		DataBase Connecter=new DataBase();
		Student obj=Connecter.getStudentInfo(201600000);
		System.out.println(obj.getID());
		System.out.println(obj.getName());
		System.out.println(obj.getMajor());
		System.out.println(obj.getStatus());
		System.out.println(obj.getGPA());
		/*Student nullobj=Connecter.getStudentInfo(2016555500);
		System.out.println(nullobj.getID());
		System.out.println(nullobj.getName());
		System.out.println(nullobj.getMajor());
		System.out.println(nullobj.getStatus());
		System.out.println(nullobj.getGPA());*/
		/*int UserID=201600000;
		String Passwrod="1234";
		int CRN1=111;
		int CRN2=105;
		int Current_Term=181;
		
		System.out.println("Login State:  "+Connecter.Login(UserID,Passwrod));
		System.out.println(CRN1+" is Section FUll:  "+Connecter.isSectionFull(CRN1));
		System.out.println("Number of  Prerequisites:  "+Connecter.GetCoursePre(CRN1).length);
		System.out.println("Adding  "+CRN1+"  :  "+Connecter.AddCourse(UserID,CRN1,Current_Term));
		System.out.println("Droping"+CRN1+"  :   "+Connecter.DropCourse(UserID,CRN1));
		System.out.println("Number of Enrolled Courses for "+UserID+"  :   "+Connecter.GetStudentCourses(UserID,Current_Term).length);
		
		System.out.println("Registarion Status   :" +Connecter.GetRegistarionStatus(UserID));
		Connecter.Save();*/
	}

}
