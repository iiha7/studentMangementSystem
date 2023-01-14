/**
 *
 */
public class MasterStudent extends Student{

    private int researchGrades;

    public MasterStudent(int researchGrades) {
        this.researchGrades = researchGrades;
    }

    public MasterStudent(int sid, String fullName, String admissionYear, double cgpa, boolean gender, int researchGrades,int cid) {
        super(sid, fullName, admissionYear, cgpa, gender,cid);
        this.researchGrades = researchGrades;
    }

    public int getResearchGrades() {
        return researchGrades;
    }

    public void setResearchGrades(int researchGrades) {
        this.researchGrades = researchGrades;
    }

    public double getCgpa() {
        return (getResearchGrades()*getCgpa())+excellence.bias;
    }
}
