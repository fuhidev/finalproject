package main.tdt.it.finalproject.worldscraper;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class WordGold {
	public static void main(String[] args) throws IOException {

//		Document doc = Jsoup.connect("http://www.usagold.com/reference/goldprices/1995.html").get();
//		//
//		Elements aElements = doc.select("table[rules='all'] tbody td,table.tableFormat tbody tr:not(.headerText) td,table tbody tr:not(.headerText) td span.txt12arial222");
//		for (Element aElement : aElements) {
//			String text = aElement.text();
//			System.out.println(text);	
//		}
		try {
			Workbook workbook = Workbook.getWorkbook(new File("C:/Users/TIEN/Desktop/Data.xls"));
			Sheet sheet = workbook.getSheet(0);
			int rows =sheet.getRows();
			int cols =sheet.getColumns();
			for (int row = 0; row < rows; row++) {
			    for (int col = 0; col < cols; col++) {
			        Cell cell = sheet.getCell(col, row);
			        System.out.print(cell.getContents() + "t");
			    }
			    System.out.println("n");
			}
			workbook.close();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
