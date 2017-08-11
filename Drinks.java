package Till;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Drinks 
{
//	private String name;
//	private double unit_price;
	private String price_list; 
	private HashMap<String, Double> drinksList = new HashMap<String, Double>();

	public Drinks()
	{
		this.drinksList.put("Coke", 2.0);
		this.drinksList.put("Pepsi", 2.0);
		this.drinksList.put("Gurkha", 3.5);
		this.drinksList.put("Budwiser", 2.5);
		this.drinksList.put("Famous Grouse", 3.75);
	}
	
	
	public void addDrinkPrice(String name, double price)
	{
		this.drinksList.put(name, price);
	}
	public double getFoodPrice(String name)
	{
		Set<Entry<String, Double>> set = this.drinksList.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext())
		{
			Map.Entry me = (Map.Entry)i.next();
			if ( name == me.getKey())
			{
				return  (double) me.getValue();
			}
		}
		
		return 0.0;
		
	}
	
	public void displayPriceList()
	{
		this.price_list = "";
		this.price_list += String.format("%-15s", "Item") + String.format("%-10s", "Price") + "\n";
		for(Map.Entry<String, Double> e : drinksList.entrySet())
		{
			this.price_list += String.format("%-10s", e.getKey()) + "\t";
			this.price_list += e.getValue() +"\n";
		}
		System.out.println(this.price_list);
	}
	public static void main(String[] args)
	{
		Drinks drinks = new Drinks();
		drinks.addDrinkPrice("Tea",1.5) ;
		drinks.addDrinkPrice("Stella", 3.5);
		drinks.addDrinkPrice("San Miguel", 3.5);
		drinks.displayPriceList();
		System.out.println(drinks.getFoodPrice("Gurkha"));
		
		
	}

}
