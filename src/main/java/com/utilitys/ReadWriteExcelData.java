package com.utilitys;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWriteExcelData {

	private String file_path; // = ".\\src\\test\\java\\testData\\TestData.xlsx";
	private FileInputStream fileIn;
	private FileOutputStream fileOut;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	private XSSFCellStyle cellStyle = null;
	public int rowCont;
	public int colCont;
	public String cellValue;

	public ReadWriteExcelData(String filePath) {

		this.file_path = filePath;

		try {
			this.fileIn = new FileInputStream(this.file_path);
			this.workbook = new XSSFWorkbook(fileIn);
			this.sheet = workbook.getSheetAt(0);
			this.fileIn.close();

		} catch (Exception ioException) {
			ioException.printStackTrace();
			System.out.println(ioException);
//			System.exit(1);
			throw new RuntimeException("Read file path is missing");

		}

	}

	public int getRowCont(String sheetName) {
		// this.workbook = new XSSFWorkbook(this.fileIn);
		this.sheet = this.workbook.getSheet(sheetName);
		return this.rowCont = sheet.getLastRowNum();

	}

	public int getColCont(String sheetName, int rowNumber) {
		this.sheet = this.workbook.getSheet(sheetName);
		return this.colCont = this.sheet.getRow(rowNumber).getLastCellNum();

	}

	public String getCellData(String sheetName, int rowNum, int colNum) {

		this.sheet = this.workbook.getSheet(sheetName);
		return this.cellValue = this.sheet.getRow(rowNum).getCell(colNum).getStringCellValue();

	}

	public boolean writeData(String sheetName, int rowNum, int colNum, String setValue) {
		try {
			this.fileIn = new FileInputStream(this.file_path);
			this.workbook = new XSSFWorkbook(fileIn);
			if (rowNum <= 0)
				return false;
			if (colNum <= 0)
				return false;
			if (sheetName == null)
				return false;
			if (setValue == null)
				return false;
			this.sheet = this.workbook.getSheet(sheetName);
			this.cellStyle = this.workbook.createCellStyle();
			this.cellStyle.setBorderTop(BorderStyle.THIN);
			this.cellStyle.setBorderBottom(BorderStyle.THIN);
			this.cellStyle.setBorderLeft(BorderStyle.THIN);
			this.cellStyle.setBorderRight(BorderStyle.THIN);
			if (this.sheet == null)
				return false;
			this.row = this.sheet.getRow(rowNum);
			this.cell = row.createCell(colNum);
			this.cell.setCellValue(setValue);
			this.cell.setCellStyle(cellStyle);
			this.fileOut = new FileOutputStream(this.file_path);
			this.workbook.write(fileOut);
			this.fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

}
