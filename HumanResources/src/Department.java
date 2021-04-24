public class Department {
    private String deptID;
    private String deptName;
    private int numStaffs;


    public Department(String deptID, String deptName, int numStaffs){
        this.deptID = deptID;
        this.deptName = deptName;
        this.numStaffs = numStaffs;
    }

    public String getDeptID() {
        return deptID;
    }

    public int getNumStaffs() {
        return numStaffs;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setNumStaffs(int numStaffs) {
        this.numStaffs = numStaffs;
    }

    public String toString(){
        return String.format("%-15s%-20s%-20s",deptID,deptName,numStaffs);
    }
}

