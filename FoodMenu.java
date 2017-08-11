package Till;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


public class FoodMenu extends JFrame
{
	private Customer customer;
	private int table_no;
	private JTextArea textArea; //for displaying the ordered item_names
	private JLabel totDisp = new JLabel("0.00"); //displays the total bill
	private String user;
	private HashMap<String, Items> menu_obj_list;
	
	//create GUI for displaying menu with item names as buttons under different item_types, using tabbed pane
	public FoodMenu(int table_no, String user, HashMap <String,Items> menu_obj_list)
	{
		this.menu_obj_list = menu_obj_list;
		this.user = user;
		this.table_no = table_no;
		this.customer = new Customer(this.table_no);
		
		this.setLayout(new BorderLayout());
		this.setSize(800,500);
		
		
		JTabbedPane tabbedpane = new JTabbedPane();
		tabbedpane.setSize(200, 200);
		
		//generate panel for each item_types with item_names as butons and add to tabbed pane
		JComponent panel;
		for(Map.Entry<String, Items> e : this.menu_obj_list.entrySet())
		{
			
			panel = makePanel(e.getValue());
			panel.setSize(200,200);
			tabbedpane.add(e.getKey(), panel);
			
		}

		
		JPanel sidePanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel tableno = new JLabel("Table no: " + (this.table_no));
		tableno.setFont(new Font("Arial", Font.BOLD, 20));
		c.insets = new Insets(10,0,0,10);
		c.gridwidth = 3;
		c.ipadx = 5;
		c.ipady = 5;
		c.gridx = 0;
		c.gridy = 0;
		sidePanel.add(tableno, c);
		
		JLabel name = new JLabel("User :" +" "+ this.user);
		name.setFont(new Font("Arial", Font.BOLD, 20));
		c.gridwidth = 2;
		c.gridx = 3;
		c.gridy = 0;
		sidePanel.add(name, c);
				
		this.textArea = new JTextArea(10,20);
//		JScrollPane scrollPane = new JScrollPane(this.textArea);
		this.textArea.setText(getHeading());
		c.gridwidth = 5;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 1;
		sidePanel.add(textArea,c);
		
		JLabel tot = new JLabel("Total :");
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 2;
		sidePanel.add(tot,c);
		
		this.totDisp.setHorizontalAlignment(SwingConstants.RIGHT);
		c.gridwidth = 2;
		c.gridx = 3;
		c.gridy = 2;
		sidePanel.add(this.totDisp, c);
		
				
		JButton printbutton = new JButton("Print");
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 3;
		sidePanel.add(printbutton,c);
		
		JButton clearbutton = new JButton("Clear");
		c.gridx = 2;
		c.gridy = 3;
		sidePanel.add(clearbutton,c);
		clearbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTextArea();
 			}
		});
		
		JButton closebutton = new JButton("Close");
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 4;
		sidePanel.add(closebutton, c);
		closebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closePanel();
 			}
		});

		
		this.getContentPane().add(tabbedpane, BorderLayout.CENTER);
		this.getContentPane().add(sidePanel, BorderLayout.EAST);

		
		
	}
	public void closePanel()
	{
		this.setVisible(false);

	}
	
	public String getHeading()
	{
		String heading = String.format("%-10s","Table no:")+"\t" + Integer.toString(this.table_no) +"\n\n";
		heading += String.format("%-21s","Items:")+"\t";
		heading += String.format("%-5s","Qty:")+"\t";
		heading += String.format("%-5s","Unit Price:")+"\n";
		
		return heading;
	}
	
	//clears textArea when clear button is pressed
	public void clearTextArea()
	{
		this.textArea.setText(getHeading());
		this.customer.resetOrder();
		this.totDisp.setText("0.00");
	}
	
	//when item_name button is pressed, calls customer.takeOrder(item,no) to add to the order list and 
	//updates the textArea and total label
	public void takeOrderHere(String item)
	{
		this.customer.takeOrder(item, 1);
		this.textArea.setText(this.customer.showBill(this.menu_obj_list));
		this.totDisp.setText(customer.getTotalPrice());
	}
	

	//creates buttons for each item_name and puts them in a panel
	protected JComponent makePanel(Items type)
	{
		int size = type.getItemsMap().size();
		int r = (int) Math.sqrt(size);
		JPanel panel = new JPanel(new GridLayout(r,r,10,10));
		
		
		JButton[] itemsButton = new JButton[size];
		int i = 0;
		for(Map.Entry<String, Double> e : type.getItemsMap().entrySet())
		{
			String name = e.getKey();
			itemsButton[i] = new JButton(e.getKey());
			itemsButton[i].setFont(new Font("Arial", Font.BOLD, 16));
			panel.add(itemsButton[i]);
			
			itemsButton[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//System.out.println(name);
					takeOrderHere(name);
			
	 			}
			});
			i++;
		}
		return panel;
	}

	

	public static void main(String[] args) {
//		FoodMenu foodmenu = new FoodMenu(12, "hem");
//		foodmenu.setVisible(true);
//		foodmenu.setLocation(200, 100);

	}



}
