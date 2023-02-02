package utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {
	
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	
	public ReadExcelData(String filePath, String sheetName) {
		try {
			workbook=new XSSFWorkbook(filePath);
			sheet=workbook.getSheet(sheetName);
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		
	}
	
	public int getRowCount() {
		return sheet.getPhysicalNumberOfRows();
	}
	
	public int getColumnCount() {
		return sheet.getRow(0).getLastCellNum();
	}
	
	public Object getCellData(int row,int col) {
		return sheet.getRow(row).getCell(col).toString();
	}

}
