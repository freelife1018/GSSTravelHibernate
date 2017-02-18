package controller;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class writeExcel {
	static File saveFolder;

	public writeExcel(File saveFolder) {
		this.saveFolder = saveFolder;
		if (!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
	}

	public void excel(String traNo, String traName, String traLoc, String traOn, String traOff, String traBeg,
			String traEnd, String traTotal, String traMax, String traIntr, String traCon, String traAttr,
			String traFile) {
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(new File(saveFolder + "/" + traFile + ".xls"));
			WritableSheet sheet = workbook.createSheet("MySheet", 0);

			WritableFont myFont = new WritableFont(WritableFont.createFont("標楷體"), 12);
			
			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setFont(myFont);
			cellFormat.setBorder(Border.ALL, BorderLineStyle.MEDIUM); // 邊框
			cellFormat.setAlignment(Alignment.CENTRE); // 水平對齊方式
			cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直對齊方式
			cellFormat.setWrap(true); // 換行

			String[] name = { "活動代碼", "活動名稱", "活動地點", "活動開始日", "活動結束日", "活動報名開始日", "活動報名結束日", "活動總人數", "活動報名上線人數(個人)",
					"活動說明", "活動內容", "活動注意事項" };
			String[] content = { traNo, traName, traLoc, traOn, traOff, traBeg, traEnd, traTotal, traMax, traIntr,
					traCon, traAttr };

			int rowSize = 500;
			int rowSize11 = 3000;
			int columnSizeA = 30;
			int columnSizeB = 100;

			for (int i = 0; i < content.length; i++) { // 0-11
				for (int j = 0; j < 2; j++) { // 0,1
					if (j == 0) {
						if (i == 10) {
							sheet.setRowView(i, rowSize11);
							sheet.setColumnView(j, columnSizeA);
							sheet.addCell(new Label(j, i, name[i], cellFormat));
						} else {
							sheet.setRowView(i, rowSize);
							sheet.setColumnView(j, columnSizeA);
							sheet.addCell(new Label(j, i, name[i], cellFormat));
						}
					} else if (j == 1) {
						if (i == 10) {
							sheet.setRowView(i, rowSize11);
							sheet.setColumnView(j, columnSizeB);
							sheet.addCell(new Label(j, i, content[i], cellFormat));
						} else {
							sheet.setRowView(i, rowSize);
							sheet.setColumnView(j, columnSizeB);
							sheet.addCell(new Label(j, i, content[i], cellFormat));
						}
					}
				}
			}
			workbook.write();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
}
