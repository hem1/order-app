package Till;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TableSetup extends JFrame 
{
	private JButton[] buttons = new JButton[17];
	private FoodMenu[] table_obj = new FoodMenu[17];
	private String user;
	private HashMap<String, Items> menu_obj_list = new HashMap<String, Items>();
	
	
	
	public TableSetup(String usr){
		
		generateMap();
		
		this.user = usr;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocation(200, 100);
		this.setLayout(new BorderLayout());
		
		JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));

		JLabel table = new JLabel(" SELECT A TABLE : ");
		table.setFont(new Font("Arial", Font.BOLD, 16));
		top.add(table);
		
		JLabel name = new JLabel("User :" +" " +this.user);
		name.setFont(new Font("Arial", Font.BOLD,16));
		top.add(name);
		
		//create buttons for each table
		JPanel buttonPanel = new JPanel(new GridLayout(4,4,15,10));
		for(int i=1; i<17; i++)
		{
			String text = "Table " + Integer.toString(i);
			this.buttons[i] = new JButton(text);
			this.buttons[i].setFont(new Font("Arial", Font.BOLD, 16));
			buttonPanel.add(this.buttons[i]);
			int x = i;
			
			this.buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					createObj(x);
	 			}
			});
			
		}
		
		this.getContentPane().add(top, BorderLayout.NORTH);
		this.getContentPane().add(buttonPanel, BorderLayout.CENTER);
		setSize(800,500);
		
	}
	//Create Object for each table(table_obj[n]) of type FoodMenu(n, this.user, this.menu_obj_list)
	public void createObj(int n)
	{
		String s = null;
		if (this.table_obj[n] == null)
		{
			String text = this.buttons[n].getText();
			
			int size = 0;
			boolean valid = false;
			
			while(!valid)
			{
				s = JOptionPane.showInputDialog(null, "How many people in a group?","Group size",JOptionPane.QUESTION_MESSAGE);
				if (s == null)
				{
					break;
				}
				try
				{
					size = Integer.parseInt(s);
					if (size > 0)
					{
						valid = true;
						this.buttons[n].setText(text+ " *" + "( "+Integer.toString(size)+" )");
						this.table_obj[n] = new FoodMenu(n, this.user, this.menu_obj_list);
					}
				}catch (NumberFormatException e)
				{
					System.out.println(e);
				}
				
			}
//			this.buttons[n].setText(text+ " *" + "( "+Integer.toString(size)+" )");
//			this.table_obj[n] = new FoodMenu(n, this.user, this.menu_obj_list);
		}
		if( s != null || this.table_obj[n] != null)
		{
			this.table_obj[n].setVisible(true);
			this.table_obj[n].setLocation(200, 100);
		}
				
	}
	//delete table_obj[n] to reset that table
	public void deleteObj(int n)
	{
		this.table_obj[n] =  null;
		
	}
	
	//reads "FileMap.csv" and generates file_map with (k,v) as (type, filename) and creates items obj for each
	//type (e.g., of type are Drinks, Desserts, etc). Items(type) generate hashmap<item_name, price> which is then
	//used to generate a hashmap, menu_obj_list<type, item>
	public void generateMap()
	{
//		Items item = new Items("Food");
//		this.menu_obj_list.put("Food", item);
//		item = new Items("Drinks");
//		this.menu_obj_list.put("Drinks", item);
//		item = new Items("Alcohol");
//		this.menu_obj_list.put("Alcohol", item);
//		item = new Items("Mains");
//		this.menu_obj_list.put("Mains", item);
//		item = new Items("Dessert");
//		this.menu_obj_list.put("Dessert", item);
		
		ReadFile file = new ReadFile("FileMap.csv");
		HashMap<String,String> file_map = file.getFileMap();
		Items item;
		for(Map.Entry<String, String> e : file_map.entrySet())
		{
			item = new Items(e.getKey());
			this.menu_obj_list.put(e.getKey(), item);
		}
		
		
		
		//System.out.println(this.menu_obj_list);
//		for(Map.Entry<String, Items> e : this.menu_obj_list.entrySet())
//		{
//			for(Map.Entry<String, Double> en : (e.getValue().getMapList()).entrySet())
//			{
//				System.out.println(en);
//			}
//			
//		}
	}
	

	public static void main(String[] args) {
		TableSetup table = new TableSetup("hem");
		table.setVisible(true);

	}

}
