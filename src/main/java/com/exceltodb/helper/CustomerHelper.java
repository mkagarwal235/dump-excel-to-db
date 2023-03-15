package com.exceltodb.helper;

import com.exceltodb.entity.Customer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomerHelper
{
    public static boolean checkFormat(MultipartFile file)
    {
        String contentType=file.getContentType();
        if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
        {
            return true;
        }
        return false;
    }
    public static List<Customer> convertExcelToListOfCustomer(InputStream is)
    {
        List<Customer>list=new ArrayList<>();
        try
        {
            XSSFWorkbook workbook=new XSSFWorkbook(is);
            XSSFSheet sheet=workbook.getSheet("Mall_Customer");
            int rowNumber=0;
            Iterator<Row> iterator=sheet.iterator();
            while(iterator.hasNext())
            {
                Row row=iterator.next();
                if(rowNumber==0)
                {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell>cellIterator=row.iterator();
                int cid=0;
                Customer customer=new Customer();
                while(cellIterator.hasNext())
                {
                    Cell cell=cellIterator.next();
                    switch (cid)
                    {
                        case 0:
                            customer.setCustomerId((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            customer.setGeneral((int) cell.getNumericCellValue());
                            break;
                        case 2:
                            customer.setAge((int) cell.getNumericCellValue());
                            break;
                        case 3:
                            customer.setAnnual_Income((int)cell.getNumericCellValue());
                            break;
                        case 4:
                            customer.setSpending_Score((int)cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                list.add(customer);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

}
