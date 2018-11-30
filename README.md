# Online registrar system
is a system that serve the university community to manage the courses registration.it helps the students to add/drop etc... their courses. And the faculty to assign grades. And the course management to handle the whole registration operation.

# Use-Cases
we are going to implement the  following four Cases
* Login
* Check Registration Status
* Add Course
* Drop Course




# Prerequisites:-
- Java   [here](https://www.java.com/en/download/)
- Eclipse   [here](https://www.eclipse.org/downloads/)
- sqlite-jdbc   [here](https://bitbucket.org/xerial/sqlite-jdbc/downloads/)  

# Instructions:
1-Install java.   
2-Install Eclipse.   
3-Create and Eclipse project.   
3-Clone This  Project files into The Eclipse project you had created in step 3.   
4-in Eclipse, Right-Click on The Eclipse Project then click properties.   
5-from the left options chose  java Build Path Then Click on Libraries.    
6-Click on Add External JARS... from the right side.     
7-Chose the sqlite-jdbc.jar you have downloaded from the Prerequisites.    
8-Click Apply and Close and that's it!!!.    
# Data Base:    
## Student  
| Column        | Type    |
| :-------      | :-------|
| ID            | Integer |
| FUllName      | Text    |
| GPA           | Numeric |
| Major         | Text    |
| Statues       | Integer |
| Password      | Text    |   
| TptalCredit   | Integer |
## Sections
| Column        | Type    |
| :-------      | :-------|
| CRN           | Integer |
| Course_ID     | Text    |
| Section_Number| Integer |
| Num_Enrolled  | Integer |
| Size          | Integer |
| Start_Time    | Numeric |
| End_Time      | Numeric |
| Days          | Text    |   
## Enrolled
| Column        | Type    |
| :-------      | :-------|
| ID            | Integer |
| CRN           | Integer |
| Term          | Integer |  
## Courses  
| Column        | Type    |
| :-------      | :-------|
| Course_ID     | Text    |
| Title         | Text    |
| Prerequisites | Text    |      
| Credit        | Integer |  
## DataBase Class
*   Login(int ID, String Passwrod):boolean        
         Return if the Password Correct.   

*   getStudentInfo(int ID):student
          Return The student info
*   isSectionFull(int CRN):boolean   
         Return if the Section isFull.

*  GetCoursePre(int CRN):String[]   
         Return The Course  Prerequisites.

*  GetStudentCourses():String[]
         Return The Student Enrolled and pre Courses.  

*  GetStudentTermCourses():String[]  
         Return only The Student Enrolled  Courses in this term.

*  AddCourse(int CRN):boolean   
         Check if there is no conflict then add the Course and return true.  

*  DropCourse(int CRN):boolean
         Check if the student is already Enrolled in the Course and delete all the Courses based on it Then delete it and Return True.   

*  Save()   
         Save all changes to the data base and close it.

*  GetRegistarionStatus(int userID):boolean   
         Return The registration Statues.  

*  GetStudentTermSection():Section[]
          Return array of all information about the current term Section_Number

*  GetStudentSectionsNumber():integer
          Return the number of enrolled section in the current Term
