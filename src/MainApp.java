	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	
	public class MainApp extends JFrame {
	
	    // Database connection parameters
	    private static final String DB_URL = "jdbc:mysql://localhost:3306/java";
	    private static final String DB_USER = "root";
	    private static final String DB_PASSWORD = "";
	
	    // Database connection object
	    private Connection connection;
	
	    // GUI components
	    private JButton connectButton;
	
	    public MainApp() {
	        super("Employee Management System");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(400, 200);
	
	        // Set default font size for the entire application
	        Font defaultFont = new Font("Arial", Font.ITALIC, 25); // You can adjust the font family and size here
	        UIManager.put("Button.font", defaultFont);
	        UIManager.put("Label.font", defaultFont);
	
	        connectButton = new JButton("Connect to Database");
	
	        connectButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Attempt to connect to the database
	                try {
	                    connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	                    JOptionPane.showMessageDialog(MainApp.this, "Connected to the database!", "Success", JOptionPane.INFORMATION_MESSAGE);
	
	                    changeScene();
	                } catch (SQLException ex) {
	                    JOptionPane.showMessageDialog(MainApp.this, "Failed to connect to the database!", "Error", JOptionPane.ERROR_MESSAGE);
	                    ex.printStackTrace();
	                }
	            }
	        });
	
	        getContentPane().setLayout(new BorderLayout());
	        getContentPane().add(connectButton, BorderLayout.CENTER);
	    }
	
	    private void changeScene() {
	        getContentPane().removeAll();
	
	        getContentPane().setLayout(new GridLayout(0, 1)); // Use GridLayout for vertical layout
	        getContentPane().add(new JLabel("Database connection successful!"));
	
	        JButton viewButton = new JButton("View Employees");
	        viewButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    ButtonActions.displayEmployees(); // Call method to display employees
	                } catch (SQLException ex) {
	                    JOptionPane.showMessageDialog(MainApp.this, "Error fetching employees: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                    ex.printStackTrace();
	                }
	            }
	        });
	        getContentPane().add(viewButton);
	
	        JButton addButton = new JButton("Add Employee");
	        addButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    ButtonActions.addEmployee(); // Call method to add employee
	                } catch (SQLException ex) {
	                    JOptionPane.showMessageDialog(MainApp.this, "Error adding employee: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                    ex.printStackTrace();
	                }
	            }
	        });
	        getContentPane().add(addButton);
	
	        JButton deleteButton = new JButton("Delete Employee");
	        deleteButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    ButtonActions.deleteEmployee(); // Call method to delete employee
	                } catch (SQLException ex) {
	                    JOptionPane.showMessageDialog(MainApp.this, "Error deleting employee: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                    ex.printStackTrace();
	                }
	            }
	        });
	        getContentPane().add(deleteButton);
	
	        // Refresh the frame
	        revalidate();
	        repaint();
	    }
	
	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                MainApp app = new MainApp();
	                app.setVisible(true);
	            }
	        });
	    }
	}
