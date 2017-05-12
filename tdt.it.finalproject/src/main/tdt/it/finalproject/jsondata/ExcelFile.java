package main.tdt.it.finalproject.jsondata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import main.tdt.it.finalproject.util.DateTimeUtil;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class ExcelFile {
	// C:/Users/TIEN/Desktop/Data.xls
	public List<WorldGold> readFileFromExcel(String path) {
		FileInputStream inputStream;
		HSSFWorkbook workbook=null;
		List<WorldGold> rs = new ArrayList<WorldGold>();
		try {
			inputStream = new FileInputStream(new File(path));
			// Đối tượng workbook cho file XSL.
			workbook = new HSSFWorkbook(inputStream);
			// Lấy ra sheet đầu tiên từ workbook
			HSSFSheet sheet = workbook.getSheetAt(0);

			// Lấy ra Iterator cho tất cả các dòng của sheet hiện tại.
			Iterator<Row> rowIterator = sheet.iterator();
			// gan co de bo qua dong dau tien trong sheet
			boolean isFirst = true;
			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();
				// kiem tra neu nhu la dong dau tien
				if (isFirst) {
					// xac dinh la dong dau tien
					isFirst = false;
					continue;

				}
//				// Lấy Iterator cho tất cả các cell của dòng hiện tại.
//				Iterator<Cell> cellIterator = row.cellIterator();

//				
//
//				while (cellIterator.hasNext()) {
//					Cell cell = cellIterator.next();
//
//					// Đổi thành getCellType() nếu sử dụng POI 4.x
//					CellType cellType = cell.getCellTypeEnum();
//					switch (cellType) {
//					case _NONE:
//						System.out.print("");
//						System.out.print("\t");
//						break;
//					case BOOLEAN:
//						System.out.print(cell.getBooleanCellValue());
//						System.out.print("\t");
//						break;
//					case BLANK:
//						System.out.print("");
//						System.out.print("\t");
//						break;
//					case FORMULA:
//						// Công thức
//						System.out.print(cell.getCellFormula());
//						System.out.print("\t");
//
//						FormulaEvaluator evaluator = workbook
//								.getCreationHelper().createFormulaEvaluator();
//
//						// In ra giá trị từ công thức
//						System.out.print(evaluator.evaluate(cell)
//								.getNumberValue());
//						break;
//					case NUMERIC:
//						if (DateUtil.isCellDateFormatted(cell)) {
//							// ??????
//							SimpleDateFormat sdf = new SimpleDateFormat(
//									"dd/MM/yyy");
//							String s = sdf.format(cell.getDateCellValue());
//							System.out.print(s);
//						} else {
//							// ????
//							System.out.print((Double) cell
//									.getNumericCellValue());
//						}
//
//						System.out.print("\t");
//						break;
//					case STRING:
//						System.out.print(cell.getStringCellValue());
//						System.out.print("\t");
//						break;
//					case ERROR:
//						System.out.print("!");
//						System.out.print("\t");
//						break;
//					}
//
//				}
				if(row.getCell(0).getCellTypeEnum() == CellType._NONE || 
						row.getCell(0).getCellTypeEnum() == CellType.BLANK ||
						row.getCell(0).getCellTypeEnum() == CellType.ERROR){
					break;
				}
				double usPrice = row.getCell(2)
						.getNumericCellValue();
				double vnPrice = row.getCell(1)
						.getNumericCellValue();
				Date dateTime = row
						.getCell(0).getDateCellValue();
				WorldGold wg = new WorldGold(usPrice, vnPrice, dateTime);
				rs.add(wg);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(workbook!=null){
				try {
					workbook.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return rs;
	}

}
