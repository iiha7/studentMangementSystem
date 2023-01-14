import java.io.Serializable;
import java.util.ArrayList;

public class Classroom implements Serializable {

    private int cid;

    private String location;

    public ArrayList<Student> students;

    public ArrayList<Instructors> instructors;

    public Classroom(){
        this.cid=0;
        this.location="unknown";
        students=new ArrayList<>();
        instructors=new ArrayList<>();
    }

    public Classroom(int cid, String location, ArrayList<Student> students,ArrayList<Instructors> instructors) {
        this.cid = cid;
        this.location = location;
        this.students = students;
        this.instructors=instructors;
    }

    public Classroom(int cid, String location) {
        this.cid = cid;
        this.location = location;
        students=new ArrayList<>();
        instructors=new ArrayList<>();
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
