import java.io.Serializable;

public class Instructors implements Comparable, Serializable {

    private int instrId;

    public Instructors(int instrId, int cid, String name, int age, int experience, double salary) {
        this.instrId = instrId;
        this.cid = cid;
        this.name = name;
        this.age = age;
        this.experience = experience;
        this.salary = salary;
    }

    private int cid;

    private String name;

    private int age;

    private int experience;

    private double salary;


    public int getInstrId() {
        return instrId;
    }

    public void setInstrId(int instrId) {
        this.instrId = instrId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }



    @Override
    public int compareTo(Object o) {

        Instructors instructors=(Instructors) o;
        if(this.getSalary() > instructors.getSalary()){
            return 1;
        }

        else if(this.getSalary()< instructors.getSalary()){
            return -1;
        }
        else{
            return 0;
        }
    }

    public String toString(){
        return this.getInstrId()+" "+this.getName()+" "+this.getAge()+" "+this.getSalary()+" "+this.getExperience();
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
