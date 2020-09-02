package com.aws.sqs;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

@Service
public class ReadExcelDemo {
    @Autowired
    QueueMessagingTemplate queueMessagingTemplate;
    @Autowired
    SendMessage sendMessage;
    public void read() {
        System.out.println("In excel Class ------&&&&&&");
        int i = 1;
        try {
            FileInputStream file = new FileInputStream(new File("/Users/dheerajsachan/Desktop/August WorkSheet/dd.xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {

                StringBuffer stringBuffer = new StringBuffer("##########   " + i+"   ");
                i++;

                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(stringBuffer.append(cell.getNumericCellValue()) + "t");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(stringBuffer.append(cell.getStringCellValue()) + "t");
                            break;
                    }
                }
               // snsClient.publish(stringBuffer);
                sendMessage.sendMessage(stringBuffer);
                System.out.println("****************");
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}