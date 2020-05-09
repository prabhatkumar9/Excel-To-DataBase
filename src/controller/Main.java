package controller;
import java.util.ArrayList;
import java.util.List;

import CRUD.CrudOperations;
import model.Person;
import utility.ExcelUtils;

public class Main {

    public static void main(String[] args) throws Exception {
	
	List<Person> personList = new ArrayList<Person>();
	
	CrudOperations crud = new CrudOperations();
	
	// path of excel file
	String excelPath = "C:\\Users\\PrabhatVisvakarma\\Downloads\\Documents\\file.xlsx";
	// sheet name
	String sheetName = "sheet1";
	// row and col count variables
	int rowcount = 0;
	int colcount = 0;
	
	// call excelutils class with help of object
	ExcelUtils ex = new ExcelUtils(excelPath, sheetName);
	
	// row count
	rowcount = ex.getRowCount();
//	System.out.println(rowcount);
	// cell count
	colcount = ex.getColCount();
//	System.out.println(colcount);
	

	personList = ex.getData(colcount);
//	System.out.println(personList.get());
	
	crud.ExportToDB(personList,rowcount);
	
    }

}
