package telran.employees;

import java.util.Arrays;

import telran.view.InputOutput;
import telran.view.Item;

public class addEmployeeItems {
    private static Company company;
    private static final long MIN_ID = 1;
    private static final long MAX_ID = 20;
    private static final int MIN_BASICSALARY = 100;
    private static final int MAX_BASICSALARY = 10000;
    private static final String[] DEPARTMENTS = {"law", "data", "cs", "sales"};
    private static final int MIN_WAGE = 100;
    private static final int MAX_WAGE = 10000;
    private static final int MIN_HOURS = 100;
    private static final int MAX_HOURS = 10000;
    private static final float MIN_PERCENT = 0.1f;
    private static final float MAX_PERCENT  = 100.0f;
    private static final long MIN_SALES = 100;
    private static final long MAX_SALES = 10000;
    private static final float MIN_FACTOR = 0.1f;
    private static final float MAX_FACTOR  = 100.0f;

    public static Item[] getItems(Company company) {
        addEmployeeItems.company = company;
        Item[] res = {
                Item.of("Hire Employee", addEmployeeItems::hireEmployee, true),
                Item.of("Hire Wage Employee", addEmployeeItems::hireWageEmployee, true),
                Item.of("Hire Sales Person", addEmployeeItems::hireSalesPerson, true),
                Item.of("Hire Manager", addEmployeeItems::hireManager, true),
                Item.ofExit()
        };
        return res;
    }

    static void hireEmployee(InputOutput io) {
        long id = io.readNumberRange(String.format("Insert the Employee Id between %d and %d", MIN_ID, MAX_ID), "Invalid insertion", MIN_ID, MAX_ID).longValue();
        int basicSalary = io.readNumberRange(String.format("Insert the Basic Salary between %d and %d", MIN_BASICSALARY, MAX_BASICSALARY), "Invalid insertion", MIN_BASICSALARY, MAX_BASICSALARY).intValue();
        String department = checkDepartment(io);
        Employee emp = new Employee(id, basicSalary, department);
        company.addEmployee(emp);
    }

    static void hireWageEmployee(InputOutput io) {
        long id = io.readNumberRange(String.format("Insert the Employee Id between %d and %d", MIN_ID, MAX_ID), "Invalid insertion", MIN_ID, MAX_ID).longValue();
        int basicSalary = io.readNumberRange(String.format("Insert the Basic Salary between %d and %d", MIN_BASICSALARY, MAX_BASICSALARY), "Invalid insertion", MIN_BASICSALARY, MAX_BASICSALARY).intValue();
        String department = checkDepartment(io);
        int wage = io.readNumberRange(String.format("Insert the wage between %d and %d", MIN_WAGE, MAX_WAGE), "Invalid insertion", MIN_WAGE, MAX_WAGE).intValue();
        int hours = io.readNumberRange(String.format("Insert the hours between %d and %d", MIN_HOURS, MAX_HOURS), "Invalid insertion", MIN_HOURS, MAX_HOURS).intValue();
        Employee emp = new WageEmployee(id, basicSalary, department, wage, hours);
        company.addEmployee(emp);
    }

    static void hireSalesPerson(InputOutput io) {
        long id = io.readNumberRange(String.format("Insert the Employee Id between %d and %d", MIN_ID, MAX_ID), "Invalid insertion", MIN_ID, MAX_ID).longValue();
        int basicSalary = io.readNumberRange(String.format("Insert the Basic Salary between %d and %d", MIN_BASICSALARY, MAX_BASICSALARY), "Invalid insertion", MIN_BASICSALARY, MAX_BASICSALARY).intValue();
        String department = checkDepartment(io);
        int wage = io.readNumberRange(String.format("Insert the wage between %d and %d", MIN_WAGE, MAX_WAGE), "Invalid insertion", MIN_WAGE, MAX_WAGE).intValue();
        int hours = io.readNumberRange(String.format("Insert the hours between %d and %d", MIN_HOURS, MAX_HOURS), "Invalid insertion", MIN_HOURS, MAX_HOURS).intValue();
        float percent = io.readNumberRange(String.format("Insert the percent between %f and %f", MIN_PERCENT, MAX_PERCENT), "Invalid insertion", MIN_PERCENT, MAX_PERCENT).floatValue();
        long sales = io.readNumberRange(String.format("Insert the sales value between %d and %d", MIN_SALES, MAX_SALES), "Invalid insertion", MIN_SALES, MAX_SALES).longValue();
        Employee emp = new SalesPerson(id, basicSalary, department, wage, hours, percent, sales);
        company.addEmployee(emp);
    }

    static void hireManager(InputOutput io) {
        long id = io.readNumberRange(String.format("Insert the Employee Id between %d and %d", MIN_ID, MAX_ID), "Invalid insertion", MIN_ID, MAX_ID).longValue();
        int basicSalary = io.readNumberRange(String.format("Insert the Basic Salary between %d and %d", MIN_BASICSALARY, MAX_BASICSALARY), "Invalid insertion", MIN_BASICSALARY, MAX_BASICSALARY).intValue();
        String department = checkDepartment(io);
        float factor = io.readNumberRange(String.format("Insert the factor between %f and %f", MIN_FACTOR, MAX_FACTOR), "Invalid insertion", MIN_FACTOR, MAX_FACTOR).floatValue();
        Employee emp = new Manager(id, basicSalary, department, factor);
        company.addEmployee(emp);
    }

    private static String checkDepartment(InputOutput io) {
        boolean key;
        String department;
        do {
            key = true;
            department = io.readString(String.format("Insert a department from the list: %s", Arrays.toString(DEPARTMENTS)));
            if(Arrays.stream(DEPARTMENTS).anyMatch(department::equals)) {
                key = false;
            }
        } while (key);
        return department;
    }
}