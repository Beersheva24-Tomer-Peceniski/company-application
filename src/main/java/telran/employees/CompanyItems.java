package telran.employees;

import java.util.stream.Stream;

import telran.io.Persistable;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.StandardInputOutput;

public class CompanyItems {
    private static Company company;

    public static Item[] getItems(Company company) {
        CompanyItems.company = company;
        Item[] res = {
                Item.of("Add Employee", CompanyItems::add),
                Item.of("Display Employee Data", CompanyItems::displayEmployeeData),
                Item.of("Fire Employee", CompanyItems::fireEmployee),
                Item.of("Department Salary Budget", CompanyItems::departmentSalaryBudget),
                Item.of("List of Departments", CompanyItems::listOfDepartments),
                Item.of("Display Managers with Most Factor", CompanyItems::managersMostFactor),
                Item.ofExit()
        };
        return res;
    }

    static void add(InputOutput io) {
        Item[] items = addEmployeeItems.getItems(company);
        Menu menu = new Menu("Add Employee", items);
        menu.perform(new StandardInputOutput());
        CompanyItems.saveFile(company);
    }

    static void displayEmployeeData(InputOutput io) {
        long id = io.readLong("Insert the Employee id: ", "Invalid insertion");
        Employee emp = company.getEmployee(id);
        io.writeLine(emp);
    }

    static void fireEmployee(InputOutput io) {
        long id = io.readLong("Insert the Employee id: ", "Invalid insertion");
        Employee emp = company.removeEmployee(id);
        if(emp != null) {
            io.writeLine(String.format("Employee %d fired sucsessfully", id));
        }
        CompanyItems.saveFile(company);
    }

    static void departmentSalaryBudget(InputOutput io) {
        String department = io.readString("Insert the department: ");
        int res = company.getDepartmentBudget(department);
        io.writeLine(res);
    }

    static void listOfDepartments(InputOutput io) {
        String[] listOfDepartments = company.getDepartments();
        Stream.of(listOfDepartments).forEach(s -> io.writeString(String.format("-> %s", s)));
        io.writeString("");
    }

    static void managersMostFactor(InputOutput io) {
        Manager[] managers = company.getManagersWithMostFactor();
        Stream.of(managers).forEach(m -> io.writeString(m.toString()));
        io.writeString("");
    }

    private static void saveFile(Company company) {
        if(company instanceof Persistable persistable) {
            persistable.saveToFile("employees.data");
        }
    }
}