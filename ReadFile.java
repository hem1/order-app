package Till;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ReadFile {
	private String filename;
	private String path = System.getProperty("user.home")+"/Documents/workspace/Java/Till/src/Till/";

	
	
	public ReadFile(String filename)
	{
		this.filename = filename;
				
	}
	
	public String checkPassword(String pass)
	{
		String usr = "";
		try
		{
			FileReader file = new FileReader(path +filename);
			BufferedReader buff = new BufferedReader(file);
			//StringBuilder  sb = new StringBuilder();
			String line = buff.readLine();

			while (line != null)
			{
				String[] ele =line.split(",");
				//System.out.println(ele[1].charAt(0)+" "+pass.charAt(0));
				//System.out.println(pass.equals(ele[1]));
				//System.out.println(pass.equals(ele[1]));

				if(pass.equals(ele[1]))
				{
					usr = ele[0];
					break;
				}
				//sb.append(line);
				//sb.append(System.lineSeparator());
				//System.out.println(line);

				line= buff.readLine();
			}
			buff.close();	

		}catch (IOException e)
		{
			System.out.println(e);
		}
		return usr;
	}
	
	//reads "FileMap.csv" and returns hashmap<item_type, filename>
	public HashMap<String,String> getFileMap()
	{
		HashMap<String, String> file_map = new HashMap<String,String>();
		try
		{
			FileReader file = new FileReader(this.path + this.filename);
			BufferedReader buff = new BufferedReader(file);
			String line = buff.readLine();

			while (line != null)
			{
				String[] ele =line.split(",");
				file_map.put(ele[0], ele[1]);
				
				line= buff.readLine();
			}
			buff.close();	

		}catch (IOException e)
		{
			System.out.println(e);
		}

		return file_map;
		
	}
	
	//return hashmap<item_name, price>
	public HashMap<String,Double> getPriceList()
	{
		HashMap<String,Double> price_list = new HashMap<String,Double>();
		
		try
		{
			FileReader file = new FileReader(this.path + this.filename);
			BufferedReader buff = new BufferedReader(file);
			String line = buff.readLine();

			while (line != null)
			{
				String[] ele =line.split(",");
				price_list.put(ele[0], Double.valueOf(ele[1]));
				
				line= buff.readLine();
			}
			buff.close();	

		}catch (IOException e)
		{
			System.out.println(e);
		}

		return price_list;

	}
	
	public static void main(String[] args) {
		ReadFile file = new ReadFile("DrinksPrice.csv");
		//HashMap<String,Double> map = new HashMap<String,Double>();
		HashMap<String,Double> map = file.getPriceList();
		map.putAll(file.getPriceList());
		System.out.println(map);
		
	}

}
