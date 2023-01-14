package reflectionTester;

final public class Employee
{
    public String firstName;
    public String lastName;
    private int salary;

    public Employee(){
        this( "John", "Smith", 50000);
    }
    public Employee(String fn, String ln, int salary) {
        this.firstName = fn;
        this.lastName = ln;
        this.salary = salary;
    }

    public Employee(String fn,String ln){
        this.firstName=fn;
        this.lastName=ln;
    }
    public int getSalary(){
        return this.salary;
    }
    public void setSalary(int salary){
        this.salary = salary;
    }
    public String toString() {
        return "Employee: " + this.firstName +  " "+ this.lastName + " " + this.salary;
    }
}
