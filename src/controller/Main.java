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
	Map<String,String> dbColName = new HashMap<String,String>();
	List<String> excelData = new ArrayList<String>();

	CrudOperations crud = new CrudOperations();
	ExcelUtils ex = new ExcelUtils(excelPath, sheetName);
	
	// row and col count variables
	int rowcount = 0;
	int colcount = 0;
	rowcount = ex.getRowCount();
	colcount = ex.getColCount();

//	// excelColName
	excelColName = ex.getColumnNames(colcount);
	dbColName = crud.getColumnNames(tableName);
	
	// compare each column name 
	int counter=0;
	boolean isTableMatch  = false;
	for(Map.Entry<String,String> entry : dbColName.entrySet()) {
	   String colDb = entry.getValue().toLowerCase();
	   String colE = excelColName.get(counter).toLowerCase();
	   counter++;
	   if(colDb.equals(colE)) {
	       isTableMatch = true;
	   }else {
	       isTableMatch = false;
	   }
	}
	
	if(isTableMatch) {
	    excelData = ex.getData(colcount);
	    crud.ExportToDB(tableName,rowcount,dbColName,excelData);
	}else {
	    System.out.println("dataBase column and Excel sheet Column are not matched");
	}
	
    }

}
