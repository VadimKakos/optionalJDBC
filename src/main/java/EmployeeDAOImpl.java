import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    final String user = "postgres";
    final String password = "redefokos123";
    final String url = "jdbc:postgresql://localhost:5432/postgres";

    @Override
    public Employee findById() {
        Employee employee = new Employee("1", "2", " 3", "4", 1);
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement =
                     connection.prepareStatement("SELECT *FROM employee where id =1")) {

            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                employee.setName(resultSet.getString("name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setCity(resultSet.getString("city"));
                employee.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
        }
        return employee;
    }


    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employees = new ArrayList<>();
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement =
                     connection.prepareStatement("SELECT *FROM employee")) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String employeeName = resultSet.getString("name");
                String employeeLastName = resultSet.getString("last_name");
                String employeeGender = resultSet.getString("gender");
                String employeeCity = resultSet.getString("city");
                int employeeId = resultSet.getInt("id");
                employees.add(new Employee(employeeName, employeeLastName, employeeGender, employeeCity, employeeId));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement =
                     connection.prepareStatement("DELETE FROM employee where id = ?")) {
            statement.setInt(1, id);
            createEmployee(employee);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement =
                     connection.prepareStatement("INSERT INTO employee (name, last_Name, gender, city, id) VALUES (?, ?,?,?,?)"
                     )) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setString(4, employee.getCity());
            statement.setInt(5, employee.getId());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Запись успешно добавлена в базу данных.");
            } else {
                System.out.println("Не удалось добавить запись в базу данных.");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        List<Employee> employees = new ArrayList<>();
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement =
                     connection.prepareStatement("DELETE FROM employee WHERE id = ?")) {
            statement.setInt(1, id);
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
        }
    }
}
