import java.text.SimpleDateFormat;
import java.util.Date;

public class Manager extends Staff implements SalaryCalculator{
    String position;

    public Manager(String id, String name, int age, Department department, Date joinDate, int daysOff, double salaryRate, String position){
        this.id =id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.joinDate = joinDate;
        this.daysOff = daysOff;
        this.salaryRate = salaryRate;
        this.position = position;
    }


    public String getPosition() {
        return position;
    }

    public String toString(){
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String formatJoinDate = simpleDateFormat.format(joinDate);
        return String.format("%-10s%-15s%-15s%-15s%-15s%-15s%-15s%-15s", id, name, age, department.getDeptName(), formatJoinDate,daysOff,salaryRate,position);
    }
    public double calculateSalary(){
        int positionPay;
        if (position.equalsIgnoreCase("business leader"))
            positionPay = 8000;
        else if (position.equalsIgnoreCase("project leader"))
            positionPay = 5000;
        else if (position.equalsIgnoreCase("Technical leader"))
            positionPay = 6000;
        else return -1;
        return (salaryRate * 5000 + positionPay);
    }


}
