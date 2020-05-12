package controller;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import CRUD.CrudOperations;
import utility.ExcelUtils;

public class Main {

    public static void main(String[] args) throws Exception {
	
	String excelPath = null;
	String sheetName = null;
	String excelName = null;
	String excelPathfileName = null;
	String tableName = null;

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("\t Enter Excel file path : : ");
	excelPath = br.readLine().trim();
	System.out.println("\t Enter Excel file name : : ");
	excelName = br.readLine().trim();
	excelPathfileName = excelPath + "\\" + excelName+".xlsx";
	System.out.println("\t Excel  fullname : : " + excelPathfileName);
	System.out.println("\t Enter Excel file sheetname : : ");
	sheetName =  br.readLine().trim();
	System.out.println("\t Excel file sheetname : : " + sheetName);
	System.out.println("\t Enter Database Table name  : : ");
	tableName =  br.readLine().trim();
	System.out.println("\t Database Table name  : : " +tableName );
	
	// excelColName
	List<String> excelColName = new ArrayList<String>();
	// db col names
	List<String> dbColName = new ArrayList<String>();
	List<String> excelData = new ArrayList<String>();
	CrudOperations crud = new CrudOperations();
	ExcelUtils ex = new ExcelUtils(excelPathfileName, sheetName);
	// row and col count variables
	int rowcount = 0;
	int colcount = 0;
	rowcount = ex.getRowCount();
	colcount = ex.getColCount();

	// excelColName
	excelColName = ex.getColumnNames(colcount);
	
	// if table not exists 
	boolean isTableExists = crud.checkTableExixts(tableName);
	if(!isTableExists) {
		String str = " varchar(50),";
		String tableHead = "";
		for( String col:excelColName) {
		    if(col.equals("date")) {
			col = "enroll_date";
		    	}
		    tableHead += col+str;
		}
		  // create table syntax
		  tableHead = tableHead.substring(0,tableHead.length()-14);
		  String createTable = "create table "+tableName+"("+tableHead+")";
		  System.out.println(createTable);
		  crud.createNewTable(tableName,createTable);
	}else {
	    // get colNames form database table
	    dbColName = crud.getColumnNames(tableName);
	    // compare each column name 
	    // boolean  isTableMatch = false;
	    int isTableMatch  = 0;
	    int IsFalse = 0;
	    for(int i=0;i<dbColName.size();i++) {
		String colDb = dbColName.get(i);
		String colE = excelColName.get(i);
			if(colDb.equals(colE)) {
			    	isTableMatch ++;
			    	//   isTableMatch = true;
			}else if(colDb.equals("enroll_date")){
			    	isTableMatch ++;
			}else {
			    	IsFalse++;
			    	//   isTableMatch = false;
				}
	   		}
		
	      // if number of columns in both records are same 
	      if(isTableMatch==colcount) {
		  excelData = ex.getData(colcount);
		  crud.ExportToDB(tableName,colcount,excelData);
	      }else {
		    System.out.println("dataBase column and Excel sheet Column are not matched");
		}
	}
    }
}
