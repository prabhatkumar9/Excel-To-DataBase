package utility;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Person;

public class ExcelUtils {
	// object of workbook
	private XSSFWorkbook wb;
	
	private XSSFSheet sheet ;
    
	// constructor
	public ExcelUtils(String excelPath,String sheetName) throws IOException{
	    this.wb = new XSSFWorkbook(excelPath);
	    // get sheet from path
	    this.sheet = wb.getSheet(sheetName);
	}
    
    public  int getRowCount() throws Exception {
	// get row number
	int rowCount = sheet.getPhysicalNumberOfRows();
	System.out.println(rowCount);
	return rowCount;
    }
    
    public  int getColCount() throws Exception {
   	// get row number
   	int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
   	System.out.println(colCount);
   	return colCount;
       }
    
    public List<Person> getData(int col) {
	List<Person> list = new ArrayList<Person>();
	 String firstName = null;
    	 String lastName = null;
    	 String gender = null;
    	 String country = null;
    	 String age = null;
    	String date = null;
    	 String id = null;
//    	 int counter = 0;
	// data formatter for accepting any data type 
	DataFormatter formatter = new DataFormatter();
	// iterate over each row
	 Iterator<Row> rowIterator = sheet.iterator();
	//Skip header
         rowIterator.next();
         while (rowIterator.hasNext())
//         while(counter<100)
         {
             // get one row at a time
             XSSFRow row = (XSSFRow) rowIterator.next();
             // for iterating  over each column
             for(int j =1;j<=col;j++) {
        	 Object value = formatter.formatCellValue(row.getCell(j));
        	 switch(j) {
        	 case 1:
        	     firstName = (String) value;
        	     break;
        	 case 2:
        	     lastName = (String) value;
        	     break;
        	 case 3:
        	     gender = (String) value;
        	     break;
        	 case 4:
        	     country = (String) value;
        	     break;
        	 case 5:
        	    age = (String) value;
        	     break;
        	 case 6:
        	     date = (String) value;
        	     break;
        	 case 7:
        	     id = (String) value;
        	     break;
        	 }
 	    }
             list.add(new Person(firstName,lastName,gender,country,age,date,id));
//             Person per = new Person(firstName,lastName,gender,country,age,date,id);
//            System.out.println(per.toString());
//            System.out.println( "Person [firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", country="
//		    + country + ", age=" + age + ", date=" + date + ", id=" + id );
//            counter++;
//            System.out.println(counter);
         }
  
	return list;
    }

}

//// path of project 
//String projectPath = System.getProperty("user.dir");
//System.out.println(projectPath);


//// path of excel file
//String excelPath = "C:\\Users\\PrabhatVisvakarma\\Downloads\\Documents\\file.xlsx";
//// object of workbook
//// pass the path
//XSSFWorkbook wb = new XSSFWorkbook(excelPath);
// // get sheet from path
//XSSFSheet sheet = wb.getSheet("sheet1");

////get row and col
//String val = sheet.getRow(1).getCell(1).getStringCellValue();
//System.out.println(val);
//
//// get numeric data
//double value = sheet.getRow(1).getCell(0).getNumericCellValue();
//System.out.println(value);

