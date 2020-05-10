package CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utility.ConnectionManager;

public class CrudOperations {
    
    ConnectionManager cm = new ConnectionManager();
    Connection con;
    
    
    public void ExportToDB(String tableName, int row, Map<String,String> listmap, List<String> excelData) throws Exception {
	 int counter=0;
	 String value = null;
	for(int i=1;i<=row;i++) {
		for(Map.Entry<String,String> entry : listmap.entrySet()) {
		    value =  excelData.get(counter);
		    entry.setValue(value);
		    counter++;    
		}
		 updateDB(listmap,tableName);
	}	
	System.out.println("DataBase Updated");
    }
    
    
    // insert into table
    public void updateDB(Map<String,String> listmap,String tableName) throws Exception {
	 	String sql = "insert into "+tableName+" values(?,?,?,?,?,?,?)";
	 	con = cm.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        int counter = 1;
	        for(Map.Entry<String,String> entry : listmap.entrySet()) {
		    entry.getValue();
		    ps.setString(counter, entry.getValue());
		    ps.executeUpdate();
		    counter++;    
		}
    }
    
    
    // function for colnames
    public Map<String, String> getColumnNames(String tableName) throws Exception {
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
	  String columnName = metadata.getColumnName(i);
	  colname.add(columnName);
	}
	// map with variables
	Map<String,String> dv = new HashMap<String,String>();
	for (int i=0; i<columnCount; i++) {
	 dv.put("m"+i,colname.get(i));
	}
	return dv;
    }
    
    
    
}
