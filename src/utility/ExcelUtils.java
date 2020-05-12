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

public class ExcelUtils {
	private XSSFWorkbook wb;
	private XSSFSheet sheet ;
	// constructor
	public ExcelUtils(String excelPath,String sheetName) throws IOException{
	    this.wb = new XSSFWorkbook(excelPath);
	    this.sheet = wb.getSheet(sheetName);
	}
    
	//function
    public  int getRowCount() throws Exception {
	int rowCount = sheet.getPhysicalNumberOfRows();
	return rowCount;
    }
    
    // function
    public  int getColCount() throws Exception {
   	int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
   	return colCount;
       }
    
    // function get columnNames 
    public List<String> getColumnNames(int col){
	List<String> excelColName = new ArrayList<String>();
	 Iterator<Row> rowIterator = sheet.iterator();
	 DataFormatter formatter = new DataFormatter();
	// 1st row contains name of column
	       Row firstRow = rowIterator.next();
	       for(int i=1;i<col+1;i++) {
		   Object value = formatter.formatCellValue(firstRow.getCell(i));

		   excelColName.add(((String) value).replaceAll("\\s", "").toLowerCase());
	       }
	       return excelColName;
    }
    
    
    /// function
    public List<String> getData(int col) {
	List<String> excelData = new ArrayList<String>();
	// data formatter for accepting any data type 
	DataFormatter formatter = new DataFormatter();
	// iterate over each row
	 Iterator<Row> rowIterator = sheet.iterator();
	//Skip header
	 rowIterator.next();
         while (rowIterator.hasNext())
         {
             // get one row at a time
             XSSFRow row = (XSSFRow) rowIterator.next();
             // for iterating  over each column
             for(int j =1;j<=col;j++) {
        	 Object value = formatter.formatCellValue(row.getCell(j));
        	excelData.add((String) value);
        	 }
 	    }
            return excelData;
           
         }
    

}

//// path of project 
//String projectPath = System.getProperty("user.dir");
//System.out.println(projectPath);




