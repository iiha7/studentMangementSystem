import java.io.Serializable;

/**
 *
 */
public abstract class Student implements excellence, Serializable {

    public Student(){
        sid=0;
        fullName="";
        admissionYear="2000-12-12";
        cgpa=3.5;
        gender=true;
        cid=0;
    }

    public Student(int sid, String fullName, String admissionYear, double cgpa, boolean gender,int cid) {
        this.sid = sid;
        this.fullName = fullName;
        this.admissionYear = admissionYear;
        this.cgpa = cgpa;
        this.gender = gender;
        this.cid=cid;
    }

    private int sid;

    private int cid;

    private String fullName;

    private String admissionYear;

    private double cgpa;

    private boolean gender;


    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(String admissionYear) {
        this.admissionYear = admissionYear;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String toString(){
        return this.getSid()+" "+this.getFullName()+" "+this.isGender()+" "+this.getCgpa()+" "+this.getAdmissionYear();
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
