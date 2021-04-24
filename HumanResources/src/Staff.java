import java.math.BigDecimal;
import java.util.Date;

public abstract class Staff {
    protected String id;
    protected String name;
    protected int age;
    protected double salaryRate;
    protected Date joinDate;
    protected Department department;
    protected int daysOff;

    //getters
    public String getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalaryRate() {
        return salaryRate;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public Department getDepartment() {
        return department;
    }

    public int getDaysOff() {
        return daysOff;
    }

    public abstract String toString();

    public abstract double calculateSalary();



}

