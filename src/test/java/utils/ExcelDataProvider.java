package utils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.ReadExcelData;

public class ExcelDataProvider {

	@DataProvider(name = "ExcelDataCredential")
	public Object[][] dataProviderCredentail() {

		ReadExcelData data = new ReadExcelData("./Data/data.xlsx", "Sheet1");
		int row = data.getRowCount();
		int col = data.getColumnCount();
		Object[][] excelData = new Object[row - 1][col];

		for (int i = 0; i < row - 1; i++) {
			for (int j = 0; j < col; j++) {
				excelData[i][j] = data.getCellData(i + 1, j);
			}
		}

		return excelData;

	}

}
