import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        System.out.println(employeeDAO.findById());
        System.out.println(employeeDAO.getAllEmployee());
        System.out.println(employeeDAO.createEmployee(new Employee("Vad", "kos", "m", "pet", 2)));
    }
}
