
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
	 Connection  c;
	 Statement Session;

	// Connect to The DataBase
	DataBase() throws Exception {
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:DataBase.db");
	}

	public Student getStudentInfo(int ID) throws SQLException{
		Session = c.createStatement();

		String Query = String.format("SELECT * FROM Student where ID=%d;", ID);
		ResultSet Result =Session.executeQuery(Query);
		if (!Result.next())
			return new Student();
			String Name=Result.getString("FullName");
			double GPA=Result.getDouble("GPA");
			String Major=Result.getString("Major");
			int Status =Result.getInt("Status");
			int totalCredit=Result.getInt("TotalCredit");
			Student student=new Student(ID,Name,Major,GPA,Status,totalCredit);
		return student;		
	}
	public  boolean Login(int ID, String Passwrod) throws Exception {
		Session = c.createStatement();

		String Query = String.format("SELECT * FROM Student where ID=%d;", ID);
		ResultSet Result =Session.executeQuery(Query);
		if (!Result.next())
			return false;
		// Return if the Password match the specific one or note
		return Session.executeQuery(Query).getString("Password").equals(Passwrod);
	}

	
	public boolean isSectionFull(int CRN) throws Exception {
			this.Session = c.createStatement();
			String Query = String.format("SELECT * FROM Sections where CRN=%d;", CRN);
			
		//return Section row with the Specified CRN
			ResultSet Result = Session.executeQuery(Query);
		
		//Throw Exception if The Section Was not Found
			if (!Result.next())
				throw new Exception("The Section with CRN   "+CRN+" was Not Found");
		
		//Return if The Section is Full or note
			return Result.getInt("Num_Enrolled") >= Result.getInt("Size");
	}

	public String[] GetCoursePre(int CRN) throws Exception {
		this.Session = c.createStatement();
		String Query = String.format(
				"select Courses.Prerequisites from Courses,Sections where Courses.Course_ID=Sections.Course_ID and Sections.CRN=%d;",
				CRN);
		// return Prerequisite Column with the Specific CRN
		ResultSet Result = Session.executeQuery(Query);
		// Throw Exception if The Course Was not Found
		if (!Result.next())
			throw new Exception("The Section with CRN   "+CRN+" was Not Found");

		return Result.getString("Prerequisites").split(" ");

	}
	


	public String[] GetStudentCourses() throws Exception {
		this.Session = c.createStatement();
		String Query = String.format(
				"select Sections.Course_ID from Sections,Enrolled where Sections.CRN=Enrolled.CRN and Enrolled.ID=%d and Enrolled.Term<%d;",
				Login._User.getID(), Login.Current_term);
		// return Courses Column with the Specific userID
		ResultSet Result = Session.executeQuery(Query);
		String Course_IDS = "";
		while (Result.next())
			Course_IDS += Result.getString("Course_ID") + " ";
		return Course_IDS.split(" ");

	}
	
	public String[] GetStudentMainCourses() throws Exception {
		this.Session = c.createStatement();
		String Query = String.format(
				"select Sections.Course_ID from Sections,Enrolled where Sections.CRN=Enrolled.CRN and Enrolled.ID=%d and Enrolled.Term>=%d;",
				Login._User.getID(), Login.Current_term);
		// return Courses Column with the Specific userID
		ResultSet Result = Session.executeQuery(Query);
		String Course_IDS = "";
		while (Result.next())
			Course_IDS += Result.getString("Course_ID") + " ";
		return Course_IDS.split(" ");

	}
	

	public boolean AddCourse(int CRN) throws Exception {
		if(Login._User.getStatus()==0)
			throw new Exception("Your registraion state is false");

		
		// Check if Section Full
		if (isSectionFull(CRN))
			throw new Exception("Section With CRN  "+CRN+"is Full");
		//Check if not reached maximum credit
				String Qurey_Credit = String.format("Select Courses.Credit from Courses,Sections where Courses.Course_ID=Sections.Course_ID and Sections.CRN=%d;",CRN);
				 int Credit= Session.executeQuery(Qurey_Credit).getInt("Credit");
				 String Qurey_Term_Credit = String.format("select sum(Courses.Credit) as total from Courses,Enrolled,Sections where Sections.Course_ID=Courses.Course_ID and Sections.CRN=Enrolled.CRN and Enrolled.Term=%d and Enrolled.ID=%d;",Login.Current_term,Login._User.getID());
				 int Term_Credit= Session.executeQuery(Qurey_Term_Credit).getInt("total");
				 
		if(Term_Credit+Credit>19)
			throw new Exception("Your Reached the maximum allowed Credit");
		String[] Pres = this.GetCoursePre(CRN);
		String[] finshed = this.GetStudentCourses();
		String[] MainTerm = this.GetStudentMainCourses();
		
		// Throw Exception if he didn't finish any course and there are Prerequisites
		if (finshed[0].isEmpty() && !Pres[0].isEmpty())
			throw new Exception("Not all Prerequisites for CRN "+CRN+" are Complet");

		else {
			// Check if he Didn't Take The Course Before
			this.Session = c.createStatement();
			String TargetCourse = Session.executeQuery("Select Course_ID from Sections where CRN=" + CRN + ";")
					.getString("Course_ID");
			// Throw Exception if he did Take The Course Before
			for (String Achived : MainTerm) {
				if (Achived.equals(TargetCourse))
					throw new Exception("The Course "+TargetCourse+" have been Already taken");
			}
			for (String Achived : finshed) {
				if (Achived.equals(TargetCourse))
					throw new Exception("The Course "+TargetCourse+" have been Already taken");
			}
			
			// Check if all Prerequisites Are Achieved
			if (!Pres[0].isEmpty())
				for (String Pre : Pres) {
					boolean Achived_pre = false;
					for (int i = 0; i < finshed.length; i++) {
						if (finshed[i].equals(Pre))
							Achived_pre = true;
					}
					if (!Achived_pre)
						throw new Exception(Pre + " is not complete Prerequisites for CRN  "+CRN);
				}
		}

		// Check Time Conflict
		ResultSet Result = getTimeConflictResult(CRN);
		//Throw Exception if there is Conflict
		if (Result.next())
			throw new Exception("There is Conflict with CRN : " + Result.getString("CRN"));
		//if no conflict found add the Course and return true
		String Query_ernoll = String.format("insert into Enrolled (ID,CRN,Term)values(%d,%d,%d);", Login._User.getID(), CRN,
				Login.Current_term);
		String Query_Increase_Num_Enrolled = String
				.format("update Sections set Num_Enrolled=Num_Enrolled+1 where CRN=%d;", CRN);
		Session.executeUpdate(Query_ernoll);
		Session.executeUpdate(Query_Increase_Num_Enrolled);
		String Query_Increase_Credit = String.format("update Student set TotalCredit=TotalCredit + %d where ID=%d;", Credit,Login._User.getID());
		Session.executeUpdate(Query_Increase_Credit);
		Login._User.settotalCredit(Login._User.gettotalCredit()+Credit);
		return true;
	}
	
	
	
	//Return query with conflicted courses
	private ResultSet getTimeConflictResult(int CRN) throws SQLException {
		ResultSet Result = Session.executeQuery("Select * from Sections where CRN=" + CRN + ";");
		Double Start_Time = Result.getDouble("Start_Time");
		Double End_Time = Result.getDouble("End_Time");
		String[] Days = Result.getString("Days").split(" ");
		String Day = "";
		for (int i = 0; i < Days.length - 1; i++)
			Day += "Days LIKE '%" + Days[i] + "%' or ";
		Day += "Days LIKE '%" + Days[Days.length - 1] + "%'";

		String Qurey_days = String.format(
				"select  Sections.CRN FROM (select CRN from Enrolled where ID=%d and Term=%d) as C,Sections,Courses  WHERE Sections.CRN=C.CRN and Sections.Course_ID=Courses.Course_ID and (Sections.Start_Time>=%.2f and Sections.Start_Time<=%.2f and Sections.End_Time>=%.2f and Sections.End_Time<=%.2f) and (%s);",
				Login._User.getID(), Login.Current_term, Start_Time, End_Time, Start_Time, End_Time, Day);
		Result = Session.executeQuery(Qurey_days);
		return Result;
	}
	
	public boolean DropCourse( int CRN) throws Exception {
		if(Login._User.getStatus()==0)
			throw new Exception("Your registraion state is false");
		this.Session = c.createStatement();
		
		//Check if he is taking that course or not
		String Query_Check = String.format("select * from Enrolled where ID=%d and CRN=%d;", Login._User.getID(), CRN);
		ResultSet Check_Result = Session.executeQuery(Query_Check);
		if (!Check_Result.next())
			throw new Exception("you don't have that course in your registered courses");
		
		//Get the Course_ID
		String Course_ID=Session.executeQuery("select Course_ID from Sections where Sections.CRN="+CRN+";").getString("Course_ID");
		
		//Find All the Courses that your Course is a Prerequisite for them to delete
		String Query_Must_delete ="select  Sections.CRN FROM (select CRN from Enrolled where ID="+Login._User.getID()+") as C,Sections,Courses  WHERE Sections.CRN=C.CRN and Sections.Course_ID=Courses.Course_ID and Prerequisites LIKE '%"+Course_ID+"%'  ;";
		ResultSet Result = Session.executeQuery(Query_Must_delete);
		//Warning!! Recursion
		while (Result.next())
			DropCourse(Result.getInt("CRN"));
		
		String Query_Delete = String.format("delete from Enrolled where ID=%d and CRN=%d;", Login._User.getID(), CRN);
		
		String Query_decrease_Num_Enrolled = String.format("update Sections set Num_Enrolled=Num_Enrolled-1 where CRN=%d;", CRN);
		Session.executeUpdate(Query_Delete);
		Session.executeUpdate(Query_decrease_Num_Enrolled);
		//get course credit
		String Qurey_Credit = String.format("Select Courses.Credit from Courses,Sections where Courses.Course_ID=Sections.Course_ID and Sections.CRN=%d;",CRN);
		 int Credit= Session.executeQuery(Qurey_Credit).getInt("Credit");
		String Query_decrease_Credit = String.format("update Student set TotalCredit=TotalCredit - %d where ID=%d;", Credit,Login._User.getID());
		Session.executeUpdate(Query_decrease_Credit);
		Login._User.settotalCredit(Login._User.gettotalCredit()-Credit);
		return true;
	}

	// Save All Changes in the DataBase
	public void Save() throws Exception {
		c.close();
	}


	
	public Section[] GetStudentTermSection() throws Exception {
		this.Session = c.createStatement();
		String Query = String.format(
				"select Courses.Course_ID,Sections.CRN,Sections.Days,Courses.Credit from Sections,Enrolled,Courses where Sections.CRN=Enrolled.CRN and (Courses.Course_ID=Sections.Course_ID) and (Enrolled.ID=%d and Enrolled.Term=%d);",
				Login._User.getID(), Login.Current_term);
		int Size = GetStudentSectionsNumber();
		if(Size>0) {
		ResultSet Result = Session.executeQuery(Query);
		Section[] obj=new Section[Size];
		for(int i=0;i<obj.length&&Result.next();i++) {
			String Course_ID=Result.getString("Course_ID");
			int CRN=Result.getInt("CRN");
			String Days=Result.getString("Days");
			int Credit=Result.getInt("Credit");
			obj[i]=new Section(Course_ID,CRN,Days,Credit);
		}
		return obj;
		}
		else
		return null;

	}

	public int GetStudentSectionsNumber() throws SQLException {
		String Query_size = String.format(
				"select count(*) as size  from Sections,Enrolled,Courses where Sections.CRN=Enrolled.CRN and (Courses.Course_ID=Sections.Course_ID) and (Enrolled.ID=%d and Enrolled.Term=%d);",
				Login._User.getID(), Login.Current_term);
		int Size= Session.executeQuery(Query_size).getInt("size");
		return Size;
	}

}
