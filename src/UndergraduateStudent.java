

public class UndergraduateStudent extends Student{

    private int activityGrades;

    public UndergraduateStudent(int activityGrades) {
        this.activityGrades = activityGrades;
    }

    public UndergraduateStudent(int sid, String fullName, String admissionYear, double cgpa, boolean gender, int activityGrades,int cid) {
        super(sid, fullName, admissionYear, cgpa, gender,cid);
        this.activityGrades = activityGrades;
    }


    public int getActivityGrades() {
        return activityGrades;
    }

    public void setActivityGrades(int activityGrades) {
        this.activityGrades = activityGrades;
    }

    public double getCgpa() {
        return (getActivityGrades()*getCgpa())+excellence.bias;
    }
}
