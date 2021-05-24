package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	
	private static XSSFSheet excelWSheet;
	private static XSSFWorkbook excelWBook;
	private static XSSFCell cell;
	//private static XSSFRow row;

	public static Object[][] getTableArray(String sheetName) throws Exception {   
	
	   String[][] tabArray = null;
	
	   try {
	
		   FileInputStream excelFile = new FileInputStream("src\\test\\resources\\testData.xlsx");
	
		   // Access the required test data sheet
		   excelWBook = new XSSFWorkbook(excelFile);
		   excelWSheet = excelWBook.getSheet(sheetName);
		   int startRow = 1;
		   int startCol = 0;	
		   int ci,cj;
	
		   int totalRows = excelWSheet.getLastRowNum();
		   int totalCols = excelWSheet.getRow(1).getLastCellNum();
	
		   tabArray=new String[totalRows][totalCols];
		   ci=0;
		   for (int i=startRow;i<=totalRows;i++, ci++) {           	   
			  cj=0;
			   for (int j=startCol;j<totalCols;j++, cj++){
				   tabArray[ci][cj]=getCellData(i,j);
				   System.out.println(tabArray[ci][cj]);  
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
		return(tabArray);
		}

	public static String getCellData(int rowNum, int colNum) throws Exception {
		try{
			cell = excelWSheet.getRow(rowNum).getCell(colNum);
			if  (cell == null) {
				return "";
			}else{
				String CellData = cell.getStringCellValue();
				return CellData;
			}	
		} catch (Exception e){
			System.out.println(e.getMessage());
			throw (e);
		}
	}
}
