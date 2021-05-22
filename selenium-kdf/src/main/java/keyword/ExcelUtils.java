package keyword;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private XSSFSheet excelWSheet;
	private XSSFWorkbook excelWBook;
	private XSSFCell cell;
	DataFormatter fmt;
	private String FILE_PATH = "src\\test\\resources\\testData.xlsx";
	//private static XSSFRow row;
	
	public ExcelUtils() throws IOException {
		FileInputStream excelFile = new FileInputStream(FILE_PATH);
		excelWBook = new XSSFWorkbook(excelFile);
		// Only need one of these
		fmt = new DataFormatter();


	}
	
	public void setWSheet(String sheetName) {
		excelWSheet = excelWBook.getSheet(sheetName);
	}

	public String[][] getTableArray(String sheetName) throws Exception {   
	
	   String[][] tabArray = null;
	
	   try {
		   excelWSheet = excelWBook.getSheet(sheetName);
	
		   // Access the required test data sheet
		   
		   int startRow = 1;
		   int startCol = 0;	
		   int ci,cj;
	
		   int totalRows = excelWSheet.getLastRowNum();
		   int totalCols = excelWSheet.getRow(0).getLastCellNum();
	
		   tabArray=new String[totalRows-startRow + 1][totalCols-startCol + 1];
		   ci=0;
		   for (int i=startRow;i<=totalRows;i++, ci++) {           	   
			  cj=0;
			   for (int j=startCol;j<totalCols;j++, cj++){
				   tabArray[ci][cj]=getCellData(i,j);
				   System.out.println(tabArray[ci][cj]);
				   //System.out.println(tabArray[ci][cj]);  
					}
				}
			}
		catch (FileNotFoundException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
			}
		catch (IOException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
			}
		return tabArray;
	}
	
	public String getCellData(int rowNum, int colNum) throws Exception {
		try{
			cell = excelWSheet.getRow(rowNum).getCell(colNum);
			if  (cell == null) {
				return "";
			}else{
				if(cell.getCellType()==CellType.STRING) 
				    return cell.getStringCellValue(); 
				else if(cell.getCellType()==CellType.NUMERIC) {
					String value = fmt.formatCellValue(cell);
				    return String.valueOf(value);
				} else {
					return "";
				}
			}	
		} catch (Exception e){
			System.out.println(e.getMessage());
			return "";
			//throw (e);
		}
	}
	
	public boolean checkWorkSheet(String sheetName) {
		if (excelWBook.getSheetIndex(sheetName) == -1) {
			return false;
		} else {
			return true;
		}
	}
	
	
}
