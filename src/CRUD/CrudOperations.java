package CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import model.Person;
import utility.ConnectionManager;

public class CrudOperations {
    
    ConnectionManager cm = new ConnectionManager();
    Connection con;
    
    
    public void ExportToDB(List<Person> list, int row) throws Exception {
	 String firstName = null;
   	 String lastName = null;
   	 String gender = null;
   	 String country = null;
   	 String age = null;
   	 String date = null;
   	 String id = null;
	for(int i=0;i<=100;i++) {
	     firstName = list.get(i).getFirstName();
	     lastName = list.get(i).getLastName();
	     gender = list.get(i).getGender();
	     country = list.get(i).getCountry();
	     age = list.get(i).getAge();
	     date = list.get(i).getDate();
	     id = list.get(i).getId();
//	  System.out.println( list.get(i).getCountry());
	     updateDB(firstName,lastName,gender,country,age,date,id);
	}
	
    }
    
    
    public void updateDB(String firstName,String lastName,String gender, String country,String age, String date,String id) throws Exception {
	 	String sql = "insert into PersonDetails values(?,?,?,?,?,?,?)";
	 	con = cm.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, firstName);
		ps.setString(2,  lastName);
		ps.setString(3, gender );
		ps.setString(4,country);
		ps.setString(5,age);
		ps.setString(6,date);
		ps.setString(7,id);
		int x = ps.executeUpdate();
		if(x==1) {
		    System.out.println("DataBase Updated.");
		}
    }
    
    
}
