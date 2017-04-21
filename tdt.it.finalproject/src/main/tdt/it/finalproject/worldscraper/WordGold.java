package main.tdt.it.finalproject.worldscraper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

public class WordGold {
	public static void main(String[] args) throws IOException {

		// Document doc =
		// Jsoup.connect("http://www.usagold.com/reference/goldprices/1995.html").get();
		// //
		// Elements aElements =
		// doc.select("table[rules='all'] tbody td,table.tableFormat tbody tr:not(.headerText) td,table tbody tr:not(.headerText) td span.txt12arial222");
		// for (Element aElement : aElements) {
		// String text = aElement.text();
		// System.out.println(text);
		//C:/Users/TIEN/Desktop/Data.xls
		// }
//		try {
//			Workbook workbook = Workbook.getWorkbook(new File(
//					"C:/Users/TIEN/Desktop/Data.xls"));
//			Sheet sheet = workbook.getSheet(0);
//			int rows = sheet.getRows();
//			int cols = sheet.getColumns();
//			for (int row = 0; row < rows; row++) {
////				if (cols == 1) {
//					for (int col = 0; col < cols; col++) {
//						Cell cell = sheet.getCell(col, row);
//						if(cell.getType(cell.getType().DATE){
//							System.out.println(cell.getContents());
//						}
//					}
////				}
//
//				System.out.println("n");
//			}
//			workbook.close();
//		} catch (BiffException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		  // Đọc một file XSL.
	       FileInputStream inputStream = new FileInputStream(new File("C:/Users/TIEN/Desktop/Data.xls"));
	  
	       // Đối tượng workbook cho file XSL.
	       HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
	 
	  
	       // Lấy ra sheet đầu tiên từ workbook
	       HSSFSheet sheet = workbook.getSheetAt(0);
	 
	  
	       // Lấy ra Iterator cho tất cả các dòng của sheet hiện tại.
	       Iterator<Row> rowIterator = sheet.iterator();
	 
	       while (rowIterator.hasNext()) {
	           Row row = rowIterator.next();
	     
	           // Lấy Iterator cho tất cả các cell của dòng hiện tại.
	           Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();
	 
	           while (cellIterator.hasNext()) {
	               Cell cell = cellIterator.next();
	  
	               // Đổi thành getCellType() nếu sử dụng POI 4.x
	               CellType cellType = cell.getCellTypeEnum();
	               String cellValue = "";
	               switch (cellType) {
	               case _NONE:
	                   System.out.print("");
	                   System.out.print("\t");
	                   break;
	               case BOOLEAN:
	                   System.out.print(cell.getBooleanCellValue());
	                   System.out.print("\t");
	                   break;
	               case BLANK:
	                   System.out.print("");
	                   System.out.print("\t");
	                   break;
	               case FORMULA:
	       
	                   // Công thức
	                   System.out.print(cell.getCellFormula());
	                   System.out.print("\t");
	                    
	                   FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
	         
	                   // In ra giá trị từ công thức
	                   System.out.print(evaluator.evaluate(cell).getNumberValue());
	                   break;
	               case NUMERIC:
	            	   if (DateUtil.isCellDateFormatted(cell)) {
	           			// ??????
	            		   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
	            		   String  s =  sdf.format(cell.getDateCellValue());
	            		   System.out.print(s);
	           		} else {
	           			// ????
	           			System.out.print((Double) cell.getNumericCellValue());
	           		}
	                   
	                   System.out.print("\t");
	                   break;
	               case STRING:
	                   System.out.print(cell.getStringCellValue());
	                   System.out.print("\t");
	                   break;
	               case ERROR:
	                   System.out.print("!");
	                   System.out.print("\t");
	                   break;
	               }
	 
	           }
	           System.out.println("");
	       }
	}
}
