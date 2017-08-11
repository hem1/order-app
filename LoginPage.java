package Till;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginPage extends JFrame implements ActionListener
{
	
	private JTextField username; //username JTextField
	private JPasswordField password;
	private String user; // name of the user
	
	public LoginPage()
	{
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,0,0,10);
		c.gridwidth = 5;
		c.gridheight = 1;
		c.ipadx = 5;
		c.ipady = 5;
		c.gridx = 0;
		c.gridy = 0;
		
		JLabel logo = new JLabel("Here is your Name");
		panel.add(logo,c);
		
		JLabel usr = new JLabel("Username :");
		JLabel pass = new JLabel("Password :");
		this.username = new JTextField(15);
		this.username.setText("Employee");
		this.password = new JPasswordField(14)
		{
			public void addNotify()             
		    {                 
		        super.addNotify();
		        requestFocusInWindow();             
		    }         
		};
		this.password.addActionListener((ActionListener) this);
		
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 1;
		panel.add(usr, c);
		
		c.gridx = 0;
		c.gridy = 2;
		panel.add(pass, c);
		
		c.gridwidth = 4;
		c.gridx = 1;
		c.gridy = 1;
		panel.add(this.username, c);
		
		c.gridx = 1;
		c.gridy = 2;
		panel.add(this.password, c);
				
		
		JButton login = new JButton("Login");
		login.addActionListener((ActionListener) this);
		
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 3;
		panel.add(login, c);
		
		this.getContentPane().add(panel);
		
		setSize(300,300);
		setLocation(300,300);

	}
	
	public void actionPerformed(ActionEvent evt)
	{
		this.validateUser();
	}
	
	//validate the password and saves the name of user in user variable by reading the passwords in "password.csv"
	public void validateUser()
	{
		//String usr = this.username.getText();
		String pass = new String(this.password.getPassword());
		String usr;
		
		ReadFile file = new ReadFile("password.csv");
		usr = file.checkPassword(pass);
		if (usr == "")
		{
			JOptionPane.showMessageDialog(null, "Wrong Password. Please try again !");
			this.password.setText("");
		}
		else
		{
			this.user = usr;
			TableSetup table = new TableSetup(this.user);
			//this.setVisible(false);
			table.setVisible(true);		
			}
		
	}
	
	public String getUser()
	{
		return this.user;
	}
	
	public static void main(String[] args)
	{
		LoginPage fp = new LoginPage();
		fp.setVisible(true);
	}



}
