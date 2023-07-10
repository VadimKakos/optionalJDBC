import java.util.List;

public interface EmployeeDAO {

    Employee findById();

    List<Employee> getAllEmployee();

    Employee updateEmployee(int id,Employee employee);

    Employee createEmployee(Employee employee);

    void deleteEmployee(int id);
}
