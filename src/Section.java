import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class Section {
private String Course,Day;
private int CRN,Credit;
private ComboBox<String> State;
public Section() {
	this("Course_ID",101,"UTR",1);
}
public Section(String Course, int CRN, String Day, int Credit) {
	this.Course=Course;
	this.CRN=CRN;
	this.Day=Day;
	this.Credit=Credit;
    ObservableList<String> options = 
    	    FXCollections.observableArrayList(
    	        "Drop"
    	    );
 this.State = new ComboBox<String>(options);
}
public String getCourse() {
	return Course;
}
public String getDay() {
	return this.Day;
}
public ComboBox<String> getState() {
	return State;
}
public int getCredit() {
	return Credit;
}
public int getCRN() {
	return CRN;
}

}
