package assignmentSelenium.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadingTestData {
	
	public static String getProps(String propName)
	{
		FileInputStream fis;
		Properties prop = null;
		try {
			fis = new FileInputStream(new File("src/test/resources/config/projectInfo.properties"));
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty(propName);
	}

	public static Object[][] getData() 
	{
		String path = "src/test/resources/testData/TestData.csv";
		Object data[][] = null;
		try (CSVReader reader = new CSVReader(new FileReader(path))) 
		{
			List<String[]> r = reader.readAll();
			int i=r.size()-1;int j=r.get(1).length;
			data = new Object[i][j];
			for(int row=1;row<=i;row++)
			{
				String a=Arrays.toString(r.get(row));
				String [] b= a.split(",");
				for(int col=0;col<b.length;col++)
				{
						b[col]=b[col].replace("[", "");
						b[col]=b[col].replace("]", "");
						data[row-1][col]=b[col];
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
}


