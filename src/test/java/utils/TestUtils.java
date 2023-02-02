package utils;



public class TestUtils  {
	
	public static void main(String[] args) {
		
		ReadExcelData data=new ReadExcelData("./Data/data.xlsx", "Sheet1");
		System.out.println(data.getRowCount());
		System.out.println(data.getColumnCount());
		System.out.println(data.getCellData(1, 3));
	}

}
