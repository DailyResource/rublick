package online.rubick.applications.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

/**
 * 读取excel单元格内容
 *
 */
public class ReadExceleUtils {

	/**
	 * 获取单元格的值
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {
		if (cell == null) {
			return null;
		}
		String val = "";
		switch (cell.getCellTypeEnum()) {
		case STRING:
			val = cell.getStringCellValue();
			break;
		case NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				val = DateUtil.formatDate(date);
			} else {
				double value = cell.getNumericCellValue();
				val = String.valueOf(value);
			}
			break;
		case BOOLEAN:
			boolean flag = cell.getBooleanCellValue();

			val = String.valueOf(flag);
			break;
		case FORMULA:
			String cellFormula = cell.getCellFormula() + "   ";
			val = cellFormula;
			break;
		case BLANK:
			break;
		case ERROR:
			break;
		default:
			break;
		}
		return val.trim();
	}

	/**
	 * 获取图片和位置 (xls)
	 * 
	 * @param sheet
	 * @return
	 * @throws IOException
	 */
	public static Map<String, List<HSSFPictureData>> getPictures(HSSFSheet sheet) throws IOException {
		Map<String, List<HSSFPictureData>> map = new HashMap<>();
		List<HSSFShape> list = sheet.getDrawingPatriarch().getChildren();
		for (HSSFShape shape : list) {
			String key = null;
			if (shape instanceof HSSFPicture) {
				HSSFPicture picture = (HSSFPicture) shape;
				HSSFClientAnchor cAnchor = picture.getClientAnchor();
				HSSFPictureData pdata = picture.getPictureData();
				key = cAnchor.getRow1() + "-" + cAnchor.getCol1(); // 行号-列号
				if (map.get(key) == null) {
					map.put(key, new ArrayList<>());
				}
				map.get(key).add(pdata);
			}
		}
		return map;
	}

	/**
	 * 获取图片和位置 (xlsx)
	 * 
	 * @param sheet
	 * @return
	 * @throws IOException
	 */
	public static Map<String, List<XSSFPictureData>> getPictures(XSSFSheet sheet) throws IOException {
		Map<String, List<XSSFPictureData>> map = new HashMap<>();
		List<POIXMLDocumentPart> list = sheet.getRelations();
		for (POIXMLDocumentPart part : list) {
			if (part instanceof XSSFDrawing) {
				XSSFDrawing drawing = (XSSFDrawing) part;
				List<XSSFShape> shapes = drawing.getShapes();
				String key = null;
				for (XSSFShape shape : shapes) {
					XSSFPicture picture = (XSSFPicture) shape;
					XSSFClientAnchor anchor = picture.getPreferredSize();
					CTMarker marker = anchor.getFrom();
					key = marker.getRow() + "-" + marker.getCol();// 行号-列号
					if (map.get(key) == null) {
						map.put(key, new ArrayList<>());
					}
					map.get(key).add(picture.getPictureData());
				}
			}
		}
		return map;
	}
}
