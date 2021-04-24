import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class HumanResources {
    public static void main(String[] args) {
        ArrayList<Employee> employeeList = new ArrayList<>();
        ArrayList<Manager> managerList = new ArrayList<>();
        //dùng 1 list staff, instanceOf
        ArrayList<Department> departmentList = new ArrayList<>();

        boolean replay = true;
        System.out.println("HUMAN RESOURCE MANAGER...\n\n");
        System.out.println("Please initiate company structure.\n");
        initDept(departmentList);

        while (replay) {
            System.out.println("1. Print staff list ");
            System.out.println("2. Print department list ");
            System.out.println("3. Print staffs of each department ");
            System.out.println("4. Add new staff ");
            System.out.println("5. Search staff information ");
            System.out.println("6. Print salary table");
            System.out.println("7. Print shorted salary table");
            System.out.println("8. Exit");
            System.out.print("Your choice: ");
            Scanner scan = new Scanner(System.in);
            int choice = 0;
            while (choice == 0){
                if (scan.hasNextInt()){
                    choice = scan.nextInt(); scan.nextLine();
                }
            }
            switch (choice) {
                case 1 -> printAllStaff(employeeList, managerList);
                case 2 -> printDeptList(departmentList);
                case 3 -> printDeptStaffs(departmentList, employeeList, managerList);
                case 4 -> enterNewStaff(employeeList, managerList, departmentList);
                case 5 -> searchStaff(employeeList, managerList);
                case 6 -> printSalaryTable(employeeList,managerList);
                case 7 -> printShortedSalaryTable(employeeList, managerList);
                case 8 -> {
                    System.out.println("See you again.");
                    replay = false;
                }
            }
            scan.nextLine();
        }
    }

    private static void initDept(ArrayList list){
        System.out.print("Enter number of departments you want to enter: ");
        Scanner scan = new Scanner(System.in);
        int numDepts = scan.nextInt();scan.nextLine();

        for (int i = 0; i < numDepts; i++) {
            System.out.print("Department ID: ");
            String id = null;
            while (id == null){
                if (scan.hasNextLine()){
                    id = scan.nextLine();
                }
            }
            System.out.print("Department name: ");
            String name = null;
            while (name == null){
                if (scan.hasNextLine()){
                    name = scan.nextLine();
                }
            }
            int numStaffs = 0;
            Department newDept = new Department(id,name,numStaffs);
            list.add(newDept);
            System.out.println();

        }
    }

    private static void enterNewStaff(ArrayList employeeList, ArrayList managerList, ArrayList deptList) {
        Scanner scan = new Scanner(System.in);
        int validated;

        String inputID;
        do {
            validated = 0;
            System.out.print("Enter staff's ID (5 characters): ");
            inputID = scan.nextLine();
            if (inputID.length() != 5) {
                System.out.println("Staff's ID must have 5 characters.");
                validated = -1;
            }
        } while(validated == -1);

        String inputName;
        do {
            validated = 0;
            System.out.print("Name: ");
            inputName = scan.nextLine();
            if (!inputName.isEmpty()) {
                if (inputName.length() > 50) {
                    System.out.println("Name length must be less than 50 characters.");
                    validated = -1;
                }
                if (!isAlphabet(inputName)) {
                    System.out.println("Name can only has a-z characters.");
                    validated = -1;
                }
            } else validated = -1;
        } while (validated == -1);

        int inputAge = 0;
        do {
            validated = 0;
            System.out.print("Age: ");
            if (scan.hasNextInt()) {
                inputAge = scan.nextInt();
                if (inputAge < 18) {
                    System.out.println("Staff's age must be at least 18.");
                }
            } else validated = -1;
        } while (validated == -1);

        double inputSalaryRate = 0.0;
        do {
            validated = 0;
            System.out.print("Salary rate: ");
            if (scan.hasNextDouble()) {
                inputSalaryRate = scan.nextDouble();
                if (inputSalaryRate < 1.0) {
                    System.out.println("Salary rate must be greater than 1.0.");
                    validated = -1;
                }
            } else validated = -1;

        } while (validated == -1);

        Date joinDate = readJoinDate();

        Department dept = readDepartment(deptList);

        int daysOff = 0;
        do {

            System.out.print("Days off: ");
            if (scan.hasNextInt()){
                daysOff = scan.nextInt();
                if (daysOff < 0) {
                    System.out.println("Days off must be greater than 0.");
                    validated = -1;
                }
            } else validated = -1;
        } while (validated == -1);
        System.out.print("Is this an manager (1: yes, 0: no)? ");
        int choice = scan.nextInt();scan.nextLine();
        if (choice == 0){
            System.out.print("Enter employee's OT hours: ");
            int otHours = -1;
            while (otHours == -1) {
                if (scan.hasNextInt()) {
                    otHours = scan.nextInt();
                }
            }
            Employee newEmployee = new Employee(inputID, inputName, inputAge, dept, joinDate, daysOff, inputSalaryRate, otHours);
            employeeList.add(newEmployee);
            dept.setNumStaffs(dept.getNumStaffs() + 1);
        } else {
            System.out.print("Enter manager's position: ");
            String position = null;
            while (position == null) {
                if (scan.hasNextLine()){
                    position = scan.nextLine();
                }
            }
            Manager newManager = new Manager(inputID, inputName, inputAge, dept, joinDate, daysOff, inputSalaryRate, position);
            managerList.add(newManager);
            dept.setNumStaffs(dept.getNumStaffs() + 1);
        }


    }

    private static boolean isAlphabet(String string){
        for (char c : string.toCharArray()) {
            if ((!Character.isLetter(c)) && (int)c != 32)
                return false;
        }
        return true;
    }

    private static Date readJoinDate(){
        System.out.print("Join date (dd-MM-yyyy): ");
        Scanner scan = new Scanner(System.in);
        String strDate = scan.nextLine();

        SimpleDateFormat inputDate = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = inputDate.parse(strDate);
        } catch (ParseException e) {
            System.out.println("Please enter the proper format.");
            readJoinDate();
        }
            return date;



    }

    private static Department readDepartment(ArrayList<Department> deptList){
        while (true) {
            System.out.print("Enter department ID: ");
            Scanner scan = new Scanner(System.in);
            String deptID = null;
            while (deptID == null){
                if (scan.hasNextLine()){
                    deptID = scan.nextLine();
                }
            }

            for (Department dept : deptList) {
                if (dept.getDeptID().equalsIgnoreCase(deptID)) {
                    return dept;
                }
            }
            System.out.print("Couldn't find this department. Do you want to add new department? (1: yes, 0: no)  ");
            int choice = scan.nextInt();
            if (choice == 1) {
                initDept(deptList);
                return deptList.get(deptList.size() - 1);
            }
        }
    }

    private static void printAllStaff(ArrayList<Employee> employeeList, ArrayList<Manager> managerList){
        System.out.printf("%-10s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%n", "ID", "Name", "Age", "Department", "Join date", "Days off", "Salary rate", "OT hours");
        for (Employee employee : employeeList){
            System.out.println(employee.toString());
        }
        System.out.printf("%-10s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%n", "ID", "Name", "Age", "Department", "Join date", "Days off", "Salary rate", "Position");
        for (Manager manager : managerList){
            System.out.println(manager.toString());
        }
    }

    private static void printDeptList(ArrayList<Department> deptList){
        System.out.printf("%-15s%-20s%-20s%n", "ID", "Department", "Number of staffs");
        for (Department dept : deptList){
            System.out.println(dept.toString());
        }
    }

    private static void printDeptStaffs(ArrayList<Department> deptList, ArrayList<Employee> employeeList, ArrayList<Manager> managerList){
        for (Department department : deptList) {
            String deptID = department.getDeptID();
            System.out.println(department.getDeptName().toUpperCase());
            System.out.printf("%-10s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%n", "ID", "Name", "Age", "Department", "Join date", "Days off", "Salary rate", "Position/OT hours");
            for (Employee employee : employeeList) {
                if (employee.getDepartment().getDeptID().equals(deptID)) {
                    System.out.println(employee.toString());
                }
            }
            for (Manager manager : managerList) {
                if (manager.getDepartment().getDeptID().equals(deptID)) {
                    System.out.println(manager.toString());
                }
            }
            System.out.println();
        }
    }

    private static void searchStaff(ArrayList<Employee> employeeList, ArrayList<Manager> managerList){
        ArrayList<Staff> foundStaffs = new ArrayList<>();
        System.out.print("Enter staff's name or ID: ");
        Scanner scan = new Scanner(System.in);
        String searchKey = null;
        while (searchKey == null){
            if (scan.hasNextLine()){
                searchKey = scan.nextLine();
            }
        }
        for (Employee staff : employeeList){
            if (staff.getId().equalsIgnoreCase(searchKey) || staff.getName().toLowerCase().contains(searchKey.toLowerCase())){
                foundStaffs.add(staff);
            }
        }
        for (Manager staff : managerList){
            if (staff.getId().equalsIgnoreCase(searchKey) || staff.getName().toLowerCase().contains(searchKey.toLowerCase())){
                foundStaffs.add(staff);
            }
        }
        if (foundStaffs.isEmpty()){
            System.out.println("Couldn't find anyone.");
        } else {
            System.out.printf("%-10s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%n", "ID", "Name", "Age", "Department", "Join date", "Days off", "Salary rate", "Position/OT hours");
            for(Staff staff : foundStaffs){
                System.out.println(staff.toString());
            }
        }
    }

    private static void printSalaryTable(ArrayList<Employee> employeeList, ArrayList<Manager> managerList){
        System.out.printf("%-15s%-20s%-20s%-20s%-20s%-20s%n", "ID", "Name", "Department", "Salary rate", "OT hours/Position", "Salary(1000VND)");
        for (Employee staff : employeeList){
            System.out.printf("%-15s%-20s%-20s%-20s%-20s%-20s%n%n", staff.getId(), staff.getName(), staff.getDepartment().getDeptName(), staff.getSalaryRate(), staff.getOtHours(), staff.calculateSalary());
        }
        for (Manager staff : managerList){
            System.out.printf("%-15s%-20s%-20s%-20s%-20s%-20s%n%n", staff.getId(), staff.getName(), staff.getDepartment().getDeptName(), staff.getSalaryRate(), staff.getPosition(), staff.calculateSalary());
        }
    }

    private static void printShortedSalaryTable(ArrayList<Employee> employeeList, ArrayList<Manager> managerList){
        ArrayList<Staff> staffList = new ArrayList<>();
        staffList.addAll(employeeList);
        staffList.addAll(managerList);
        arrayListSort(staffList);
        System.out.printf("%-15s%-20s%-20s%-20s%n", "ID", "Name", "Department", "Salary(1000VND)");
        for (Staff staff : staffList){
            System.out.printf("%-15s%-20s%-20s%-20s%n", staff.getId(), staff.getName(), staff.getDepartment().getDeptName(), staff.calculateSalary());
        }



    }

    private static void arrayListSort(ArrayList<Staff> Array){
        int i, j;
        for (i = 1; i < Array.size(); i++) {
            Staff key = Array.get(i);
            double insertValue = Array.get(i).calculateSalary();
            j = i;
            while((j > 0) && (Array.get(j - 1).calculateSalary() > insertValue)) {
                Array.set(j,Array.get(j - 1));
                j--;
            }
            Array.set(j,key);
        }
    }
}
