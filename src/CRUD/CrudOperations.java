package CRUD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utility.ConnectionManager;

public class CrudOperations {
    
    ConnectionManager cm = new ConnectionManager();
    Connection con;
    
    public void ExportToDB(String tableName,  int colcount, List<String> excelData) throws Exception {
	con = cm.getConnection();
 	con.setAutoCommit(false);
 	Statement st = con.createStatement();

 	int counter = 0;
 	String value = null;
	 List<String> colList = new ArrayList<String>();
	 // run for number of rows
	for(int i=0;i<excelData.size();i++) {
	    // remove null pointer exception
	    if(counter<excelData.size()) {
	    // run for number of columns
		for(int j=0;j<colcount;j++) {
		    value =  excelData.get(counter);
		    counter++;
		    // each string store in value
		    // list container one row at a time
		    colList.add(value);   
		}
		// after inner loop list gets a row 		
		// framing into sql query 
		 String sql = "" ;
		 String coma = ",";
		  for(int k=0;k<colList.size();k++) {
		      sql +="'"+colList.get(k)+"'"+coma;
		  }
		  sql = sql.substring(0,sql.length()-4);
		  // System.out.println(sql);
		 String insertSql = "insert into "+tableName+" values("+sql+")";
		 colList.clear();
		 // System.out.println(insertSql);
		 st.addBatch(insertSql);
	    }	
	}

	int[] count = st.executeBatch();
	//System.out.println(count.length);
	if(count.length>0) {
	  System.out.println("Data transfer successfull.");
	}
	con.commit();
    }
    

    // function for colnames
    public List<String> getColumnNames(String tableName) throws Exception {
	     String sql = "select * from "+tableName;
	     con = cm.getConnection();
	     Statement st = con.createStatement();
	     ResultSet rs = st.executeQuery(sql);
	// Get resultset metadata
	ResultSetMetaData metadata = rs.getMetaData();
	int columnCount = metadata.getColumnCount();
	
	// column list
	List<String> colname = new ArrayList<String>();
	// Get the column names; column indices start from 1
	for (int i=1; i<=columnCount; i++) {
	  String columnName = metadata.getColumnName(i).toLowerCase();
	  colname.add(columnName);
	}
	return colname;
    }
    

    // check for table in database
    public boolean checkTableExist(String tableName) throws Exception {
	try {
	    String sql = "select * from "+tableName;
	     con = cm.getConnection();
	     Statement st = con.createStatement();
	     ResultSet rs = st.executeQuery(sql);
	     return true;
	}catch(Exception e) {
	    System.out.println("Table not present in Database..");
	    return false;
	}
    }
    
    
    // create new table in database
    public void createNewTable(String tableName,String sql) throws Exception,SQLException {
	con = cm.getConnection();
	Statement st = con.createStatement();
	try {
	    int x = st.executeUpdate(sql);
//	    System.out.println(x+"  table Created...");
	    if(x==0) {
		    System.out.println(tableName+"  table Created...");
		}
	}catch(Exception e) {
	    System.out.println(" Error while Creating table ...");
	}
    }

}
