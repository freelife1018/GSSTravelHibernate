package controller;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Excel {
	static File saveFolder;

	public Excel(File saveFolder) {
		this.saveFolder = saveFolder;
		if (!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
	}

	public void travelExcel(String traNo, String traName, String traLoc, String traOn, String traOff, String traBeg,
			String traEnd, String traTotal, String traMax, String traIntr, String traCon, String traAttr,
			String traFile) {
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(new File(saveFolder + "/" + traFile + ".xls"));
			WritableSheet sheet = workbook.createSheet("MySheet", 0);

			WritableFont myFont = new WritableFont(WritableFont.createFont("標楷體"), 12);

			WritableCellFormat cellFormatTitle = new WritableCellFormat();
			cellFormatTitle.setFont(myFont); // 字體
			cellFormatTitle.setBorder(Border.ALL, BorderLineStyle.MEDIUM); // 邊框
			cellFormatTitle.setAlignment(Alignment.CENTRE); // 水平對齊方式
			cellFormatTitle.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直對齊方式
			cellFormatTitle.setWrap(true); // 換行
			cellFormatTitle.setBackground(Colour.PALE_BLUE); // 背景顏色

			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setFont(myFont); // 字體
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
							sheet.addCell(new Label(j, i, name[i], cellFormatTitle));
						} else {
							sheet.setRowView(i, rowSize);
							sheet.setColumnView(j, columnSizeA);
							sheet.addCell(new Label(j, i, name[i], cellFormatTitle));
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

	public void detailExcel(int count, String[] detNo, String traNo, String[] empNo, String[] famNo, String[] famRel,
			String[] famName, String[] famSex, String[] famID, String[] famBdate, String[] famPhone, String[] famEat,
			String[] famCar, String[] famBaby, String[] famKid, String[] famDis, String[] famMom, String[] famBen,
			String[] famBenRel, String[] famEmg, String[] famEmgPhone, String[] detDate, String[] detCanDate,
			String[] famNote, String[] detCanNote) {
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(new File(saveFolder + "/報名明細-" + traNo + ".xls"));
			WritableSheet sheet = workbook.createSheet("MySheet", 0);

			WritableFont myFont = new WritableFont(WritableFont.createFont("標楷體"), 12);

			WritableCellFormat cellFormatTitle = new WritableCellFormat();
			cellFormatTitle.setFont(myFont); // 字體
			cellFormatTitle.setBorder(Border.ALL, BorderLineStyle.MEDIUM); // 邊框
			cellFormatTitle.setAlignment(Alignment.CENTRE); // 水平對齊方式
			cellFormatTitle.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直對齊方式
			cellFormatTitle.setWrap(true); // 換行
			cellFormatTitle.setBackground(Colour.PALE_BLUE); // 背景顏色

			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setFont(myFont); // 字體
			cellFormat.setBorder(Border.ALL, BorderLineStyle.MEDIUM); // 邊框
			cellFormat.setAlignment(Alignment.CENTRE); // 水平對齊方式
			cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直對齊方式
			cellFormat.setWrap(true); // 換行

			String[] name = { "報名流水號", "活動代碼", "員工編號", "親屬編號", "身份", "姓名", "性別", "身份證字號", "生日", "手機", "用餐", "車位",
					"特殊身份-幼童", "特殊身份-兒童", "特殊身份-身心障礙", "特殊身份-孕婦", "保險受益人", "與受益人關係", "緊急聯絡人", "緊急聯絡人手機", "報名時間", "取消日期",
					"備註", "取消原因" };
			for (int i = 0; i < count; i++) {
				if (famCar[i].equals("true")) {
					famCar[i] = "是";
				} else {
					famCar[i] = "否";
				}
				if (famBaby[i].equals("true")) {
					famBaby[i] = "是";
				} else {
					famBaby[i] = "否";
				}
				if (famKid[i].equals("true")) {
					famKid[i] = "是";
				} else {
					famKid[i] = "否";
				}
				if (famDis[i].equals("true")) {
					famDis[i] = "是";
				} else {
					famDis[i] = "否";
				}
				if (famMom[i].equals("true")) {
					famMom[i] = "是";
				} else {
					famMom[i] = "否";
				}
				if (famNote[i] == null) {
					famNote[i] = "無";
				}
				if (detCanNote[i] == null) {
					detCanNote[i] = "無";
				}
			}

			int rowSize = 500;
			int columnSize = 30;

			for (int i = 0; i < count; i++) {
				for (int j = 0; j < name.length; j++) { // 0-23
					if (i == 0) {
						sheet.setRowView(i, rowSize);
						sheet.setColumnView(j, columnSize);
						sheet.addCell(new Label(j, i, name[j], cellFormatTitle));
					}
				}
			}

			for (int i = 0; i < count; i++) {
				for (int j = 0; j < name.length; j++) { // 0-23
					sheet.setRowView(i + 1, rowSize);
					sheet.setColumnView(j, columnSize);
					if (j == 0) {
						sheet.addCell(new Label(j, i + 1, detNo[i], cellFormat));
					} else if (j == 1) {
						sheet.addCell(new Label(j, i + 1, traNo, cellFormat));
					} else if (j == 2) {
						sheet.addCell(new Label(j, i + 1, empNo[i], cellFormat));
					} else if (j == 3) {
						sheet.addCell(new Label(j, i + 1, famNo[i], cellFormat));
					} else if (j == 4) {
						sheet.addCell(new Label(j, i + 1, famRel[i], cellFormat));
					} else if (j == 5) {
						sheet.addCell(new Label(j, i + 1, famName[i], cellFormat));
					} else if (j == 6) {
						sheet.addCell(new Label(j, i + 1, famSex[i], cellFormat));
					} else if (j == 7) {
						sheet.addCell(new Label(j, i + 1, famID[i], cellFormat));
					} else if (j == 8) {
						sheet.addCell(new Label(j, i + 1, famBdate[i], cellFormat));
					} else if (j == 9) {
						sheet.addCell(new Label(j, i + 1, famPhone[i], cellFormat));
					} else if (j == 10) {
						sheet.addCell(new Label(j, i + 1, famEat[i], cellFormat));
					} else if (j == 11) {
						sheet.addCell(new Label(j, i + 1, famCar[i], cellFormat));
					} else if (j == 12) {
						sheet.addCell(new Label(j, i + 1, famBaby[i], cellFormat));
					} else if (j == 13) {
						sheet.addCell(new Label(j, i + 1, famKid[i], cellFormat));
					} else if (j == 14) {
						sheet.addCell(new Label(j, i + 1, famDis[i], cellFormat));
					} else if (j == 15) {
						sheet.addCell(new Label(j, i + 1, famMom[i], cellFormat));
					} else if (j == 16) {
						sheet.addCell(new Label(j, i + 1, famBen[i], cellFormat));
					} else if (j == 17) {
						sheet.addCell(new Label(j, i + 1, famBenRel[i], cellFormat));
					} else if (j == 18) {
						sheet.addCell(new Label(j, i + 1, famEmg[i], cellFormat));
					} else if (j == 19) {
						sheet.addCell(new Label(j, i + 1, famEmgPhone[i], cellFormat));
					} else if (j == 20) {
						sheet.addCell(new Label(j, i + 1, detDate[i], cellFormat));
					} else if (j == 21) {
						sheet.addCell(new Label(j, i + 1, detCanDate[i], cellFormat));
					} else if (j == 22) {
						sheet.addCell(new Label(j, i + 1, famNote[i], cellFormat));
					} else if (j == 23) {
						sheet.addCell(new Label(j, i + 1, detCanNote[i], cellFormat));
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

	public void totalAmountExcel(int count, String traNo, String traName, String[] deptNo, String[] No, String[] Name,
			String[] yearsMoney, String[] personMoney, String[] Money, String[] detNote, String[] detNoteMoney,
			String[] taMoney) {
		try {
			WritableWorkbook workbook = Workbook
					.createWorkbook(new File(saveFolder + "/旅費統計-" + traNo + "-" + traName + ".xls"));
			WritableSheet sheet = workbook.createSheet("MySheet", 0);

			WritableFont myFont = new WritableFont(WritableFont.createFont("標楷體"), 12);

			WritableCellFormat cellFormatTitle = new WritableCellFormat();
			cellFormatTitle.setFont(myFont); // 字體
			cellFormatTitle.setBorder(Border.ALL, BorderLineStyle.MEDIUM); // 邊框
			cellFormatTitle.setAlignment(Alignment.CENTRE); // 水平對齊方式
			cellFormatTitle.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直對齊方式
			cellFormatTitle.setWrap(true); // 換行
			cellFormatTitle.setBackground(Colour.PALE_BLUE); // 背景顏色

			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setFont(myFont); // 字體
			cellFormat.setBorder(Border.ALL, BorderLineStyle.MEDIUM); // 邊框
			cellFormat.setAlignment(Alignment.CENTRE); // 水平對齊方式
			cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直對齊方式
			cellFormat.setWrap(true); // 換行

			String[] name = { "部門代碼", "員工(/眷屬親友)編號", "姓名(/隸屬於哪位員工)", "年度可補助金額", "個人可補助金額", "個人團費", "其他增減費用明細說明",
					"其他增減費用總額", "應補團費" };

			for (int i = 0; i < count; i++) {
				if (detNote[i] == "") {
					detNote[i] = "無";
				}
			}
			
			int rowSize = 500;
			int columnSize = 30;

			for (int i = 0; i < count; i++) {
				for (int j = 0; j < name.length; j++) {
					if (i == 0) {
						sheet.setRowView(i, rowSize);
						sheet.setColumnView(j, columnSize);
						sheet.addCell(new Label(j, i, name[j], cellFormatTitle));
					}
				}
			}

			for (int i = 0; i < count; i++) {
				for (int j = 0; j < name.length; j++) { // 0-8
					sheet.setRowView(i + 1, rowSize);
					sheet.setColumnView(j, columnSize);
					if (j == 0) {
						sheet.addCell(new Label(j, i + 1, deptNo[i], cellFormat));
					} else if (j == 1) {
						sheet.addCell(new Label(j, i + 1, No[i], cellFormat));
					} else if (j == 2) {
						sheet.addCell(new Label(j, i + 1, Name[i], cellFormat));
					} else if (j == 3) {
						sheet.addCell(new Label(j, i + 1, yearsMoney[i], cellFormat));
					} else if (j == 4) {
						sheet.addCell(new Label(j, i + 1, personMoney[i], cellFormat));
					} else if (j == 5) {
						sheet.addCell(new Label(j, i + 1, Money[i], cellFormat));
					} else if (j == 6) {
						sheet.addCell(new Label(j, i + 1, detNote[i], cellFormat));
					} else if (j == 7) {
						sheet.addCell(new Label(j, i + 1, detNoteMoney[i], cellFormat));
					} else if (j == 8) {
						sheet.addCell(new Label(j, i + 1, taMoney[i], cellFormat));
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
