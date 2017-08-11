package Till;

import java.util.HashMap;
import java.util.Map;

public class Items
{
//	private String food_filename = "FoodPrice.csv";
//	private String drinks_filename = "DrinksPrice.csv";
//	private String alcohol_filename = "AlcoholPrice.csv";
//	private String mains_filename = "MainsPrice.csv";
//	private String dessert_filename = "DessertPrice.csv";
	private String price_list ; 
	private HashMap<String, Double> itemsMap = new HashMap<String, Double>();//hashmap<item_name,price>

	//read "FileMap.csv" and stores in file_map, hashmap<items_type, filename>. Then iterates over the hashmap 
	//and if the item_type matches, opens the corresponding file and generate a itemsMap, hashmap<item_name, price>
	public Items(String type)
	{
		ReadFile file = new ReadFile("FileMap.csv");
		HashMap<String,String> file_map = file.getFileMap();
		for(Map.Entry<String, String> e : file_map.entrySet())
		{
			//System.out.println(type.equals(e.getKey() ));
			if ( type.equals(e.getKey()))
			{
				ReadFile file1 = new ReadFile(e.getValue());
				this.itemsMap = file1.getPriceList();
				break;
			}
		}
//		if (type == "Food")
//		{
//			ReadFile file = new ReadFile(food_filename);
//			this.itemsMap = file.getPriceList();
//			
//		}
//		else if(type == "Drinks")
//		{
//			ReadFile file = new ReadFile(drinks_filename);
//			this.itemsMap = file.getPriceList();
//		}
//		else if (type == "Alcohol")
//		{
//			ReadFile file = new ReadFile(alcohol_filename);
//			this.itemsMap = file.getPriceList();
//		}
//		else if (type == "Mains")
//		{
//			ReadFile file = new ReadFile(mains_filename);
//			this.itemsMap = file.getPriceList();
//		}
//		else if (type == "Dessert")
//		{
//			ReadFile file = new ReadFile(dessert_filename);
//			this.itemsMap = file.getPriceList();
//		}
	}
	
	public HashMap<String, Double> getItemsMap()
	{
		return itemsMap;
	}
	
	//creates a list from hashmap, don't know why ??????????
//	public List<String> getItemsList()
//	{
//		List<String> listofItems = new ArrayList<String>();
//		for(Map.Entry<String, Double> e : this.itemsMap.entrySet())
//		{
//			listofItems.add(e.getKey());
//		}
//		return listofItems;
//	}
	
	//for editing price of item_name, not used so far
	public void editItemsPrice(String name, double price)
	{
		this.itemsMap.put(name, price);
	}
	
	//for adding new item_name to map, cannot be used with my structure.why is it here??
	public void addItems(String name, double price)
	{
		this.itemsMap.put(name, price);
	}
	//given the item_name, returns the unit price
	public double getItemsPrice(String name)
	{
		for(Map.Entry<String, Double> e : itemsMap.entrySet())
		{
			if (name == e.getKey())
			{
				return e.getValue();
			}
		}
		
		
//		Set<Entry<String, Double>> set = this.itemsMap.entrySet();
//		Iterator i = set.iterator();
//		while(i.hasNext())
//		{
//			Map.Entry me = (Map.Entry)i.next();
//			if ( name == me.getKey())
//			{
//				return  (double) me.getValue();
//			}
//		}
		
		return 0.0;
		
	}
	
	//display the whole price list of the hashmap. not used...
	public void displayPriceList()
	{
		this.price_list = "";
		this.price_list += String.format("%-20s", "Item") + String.format("%-10s", "Price") + "\n";
		for(Map.Entry<String, Double> e : itemsMap.entrySet())
		{
			this.price_list += String.format("%-20s", e.getKey()) + "\t";
			this.price_list += e.getValue() +"\n";
		}
		System.out.println(this.price_list);
	}
	
	public static void main(String[] args)
	{
		Items food = new Items("Drinks");
		System.out.println(food.getItemsMap());
		
		
	}

}
