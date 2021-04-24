import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee extends Staff implements SalaryCalculator{
    int otHours;

    public Employee(String id, String name, int age, Department department, Date joinDate, int daysOff, double salaryRate, int otHours){
        this.id =id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.joinDate = joinDate;
        this.daysOff = daysOff;
        this.salaryRate = salaryRate;
        this.otHours = otHours;
    }


    public int getOtHours() {
        return otHours;
    }

    public String toString(){
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String formatJoinDate = simpleDateFormat.format(joinDate);
        return String.format("%-10s%-15s%-15s%-15s%-15s%-15s%-15s%-15s", id, name, age, department.getDeptName(), formatJoinDate,daysOff,salaryRate,otHours);
    }

    @Override
    public double calculateSalary() {
        return (salaryRate * 3000 + otHours * 200);
    }

}