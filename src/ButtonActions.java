import javax.swing.*;
import java.sql.*;

public class ButtonActions {

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/java";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void displayEmployees() throws SQLException {
        // Implement logic to display employees
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
            
            StringBuilder result = new StringBuilder("Employees:\n");
            while (resultSet.next()) {
                result.append("ID: ").append(resultSet.getInt("id")).append(", ")
                      .append("Name: ").append(resultSet.getString("name")).append(", ")
                      .append("Position: ").append(resultSet.getString("position")).append(", ")
                      .append("Salary: ").append(resultSet.getDouble("salary")).append("\n");
            }
            
            JOptionPane.showMessageDialog(null, result.toString(), "Employees", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void addEmployee() throws SQLException {
        // Implement logic to add employee
        String name = JOptionPane.showInputDialog(null, "Enter employee name:");
        if (name == null || name.trim().isEmpty()) { 
            JOptionPane.showMessageDialog(null, "You can not enter null value!", "Error", JOptionPane.ERROR_MESSAGE);
            return; // 
        }
        
        String position = JOptionPane.showInputDialog(null, "Enter employee position:");
        if (position == null || position.trim().isEmpty()) { 
            JOptionPane.showMessageDialog(null, "You can not enter null value!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String salaryInput = JOptionPane.showInputDialog(null, "Enter employee salary:");
        if (salaryInput == null || salaryInput.trim().isEmpty()) { // Check if the salary input is empty or null
            JOptionPane.showMessageDialog(null, "You can not enter null value!", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method if the salary input is empty or null
        }
        
        double salary;
        try {
            salary = Double.parseDouble(salaryInput); // Parse the salary input as a double
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number for employee salary!", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }
        
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO employees (name, position, salary) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, position);
            preparedStatement.setDouble(3, salary);
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Employee added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add employee!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public static void deleteEmployee() throws SQLException {
        // Implement logic to delete employee by ID
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter employee ID to delete:"));
        
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "DELETE FROM employees WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Employee deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete employee!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
