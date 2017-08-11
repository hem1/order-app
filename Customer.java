package Till;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer 
{
	
	private int table_no;
	private double total_price;
	private HashMap<String, Integer> order_map = new HashMap<String, Integer>();//hashmap<item_name,no>
	private List<String> order_list = new ArrayList<String>(); //list <item_name> to maintain textArea
	
	public Customer ( int table_no)
	{
		this.table_no = table_no;
	}
	
	//simply puts (item_name , no) in the hashmap
	public void takeOrder(String item_name, int quantity)
	{
		//System.out.println(this.order_map.);
		if(this.order_map.containsKey(item_name))
		{
			this.order_map.put(item_name,this.order_map.get(item_name)+ 1);
		}
		else
		{
			this.order_map.put(item_name, quantity);
		}
		if (! this.order_list.contains(item_name))
		{
			this.order_list.add(item_name);
		}

	}
	
	public String getTotalPrice()
	{
		return Double.toString(this.total_price);
	}
	
	public int getOrderListSize()
	{
		return this.order_list.size();
	}
	//clears hashmap and list
	public void resetOrder()
	{
		this.order_map.clear();
		this.order_list.clear();
	}
	
	//return string to display in the textArea
	public String showBill(HashMap<String, Items> menu_obj_list)
	{

		this.total_price = 0.0;
		String itenary = String.format("%-10s","Table no:")+"\t" + Integer.toString(this.table_no) +"\n\n";
		itenary += String.format("%-21s","Items:")+"\t";
		itenary += String.format("%-5s","Qty:")+"\t";
		itenary += String.format("%-5s","Unit Price:")+"\n";
		
		for(int i = 0; i< this.getOrderListSize(); i++)
		{
			double p = 0.0;
			String item_name = this.order_list.get(i);
			
			for(Map.Entry<String, Items> e :menu_obj_list.entrySet())
				{
					if ((e.getValue().getItemsMap().containsKey(item_name)))
					{
						p = (e.getValue().getItemsPrice(item_name));
						break;
						
					}
				}
			
			itenary += String.format("%-30s", item_name) +"\t";
			itenary += String.format("%-5s", this.order_map.get(item_name)) + "\t";
			itenary += String.format("%-5s", p) + "\n";
			this.total_price += (p * this.order_map.get(item_name));
		}
		
		return itenary;
	}
	
	public static void main(String[] args) 
	{
		/*Items food = new Items("Food");
		Items drinks = new Items("Drinks");

		food.addItems("Daal Bhat", 12.2);
		food.addItems("Wai Wai", 3.3);
		food.addItems("Chowmein", 2.3);
		food.addItems("Sekuwa", 5.5);
		food.addItems("Pork", 4.5);
		food.addItems("Mutton", 7.5);
		
		drinks.addItems( "Coke", 2.0);
		drinks.addItems("Pepsi", 2.0);
		drinks.addItems("Gurkha", 3.5);
		drinks.addItems("Budwiser", 2.5);
		drinks.addItems("Famous Grouse", 3.75);
		drinks.addItems("Tea",1.5);
		drinks.addItems("Stella", 3.5);
		drinks.addItems("San Miguel", 3.5);
		 
		
		Customer cust12 = new Customer(12);
		cust12.takeOrder("Sekuwa", 2);
		cust12.takeOrder("Pork", 1);
		cust12.takeOrder("Mutton", 2);
		
		cust12.takeOrder("Coke", 3);
		cust12.takeOrder("Budwiser", 4);
		
		System.out.println(cust12.showBill(food, drinks));
	*/
	}

}
