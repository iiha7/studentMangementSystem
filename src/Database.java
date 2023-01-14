import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class Database {

    public ArrayList<Classroom> classrooms;

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";



    public Database(){
        classrooms=new ArrayList<>();
    }

    public Database(ArrayList<Classroom> classrooms) {
        this.classrooms = classrooms;
    }


    public void readData(){

        try{

            // STEP1 -- Load the JDBC driver
            Class.forName(JDBC_DRIVER);
            System.out.println("Driver loaded");

            // STEP 2 -- Establish a connection
            System.out.println("Establishing a connection");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "hamza", "1234");
            System.out.println("Database connected");

            // STEP 3 -- Create a statement
            Statement stmt = con.createStatement();

            ResultSet resultSet = stmt.executeQuery("SELECT * FROM classrooms");

            while (resultSet.next()){
                int cid=Integer.parseInt(resultSet.getString(1));
                String location=resultSet.getString(2);
                Classroom classroom=new Classroom(cid,location);
                classrooms.add(classroom);
            }



            // STEP 4 -- Execute a statement
            resultSet = stmt.executeQuery("SELECT * FROM student");

            while(resultSet.next()){

                int sid=Integer.parseInt(resultSet.getString(2));
                int cid=Integer.parseInt(resultSet.getString(3));
                String fullName=resultSet.getString(4);
                String admissionYear=resultSet.getString(5);
                double cgpa=Double.parseDouble(resultSet.getString(6));
                boolean gender=true;

                if(resultSet.getString(7).equals("f")){
                    gender=false;
                }

                if(resultSet.getString(1).equals("u")){
                    int activityGrades=Integer.parseInt(resultSet.getString(8));
                    UndergraduateStudent undergraduateStudent=new UndergraduateStudent(sid,fullName,admissionYear,cgpa,gender,activityGrades,cid);
                    for(int i=0;i<classrooms.size();i++){
                        if(classrooms.get(i).getCid() == cid){
                            classrooms.get(i).students.add(undergraduateStudent);
                        }
                    }
                }

                else{
                    int researchGrades=Integer.parseInt(resultSet.getString(9));
                    MasterStudent masterStudent=new MasterStudent(sid,fullName,admissionYear,cgpa,gender,researchGrades,cid);

                    for(int i=0;i<classrooms.size();i++){
                        if(classrooms.get(i).getCid() == cid){
                            classrooms.get(i).students.add(masterStudent);
                        }
                    }

                }
            }

            resultSet =stmt.executeQuery("SELECT * FROM instructors");

            while (resultSet.next()){

                int instrId=Integer.parseInt(resultSet.getString(1));
                int cid=Integer.parseInt(resultSet.getString(2));
                String name=resultSet.getString(3);
                int age=Integer.parseInt(resultSet.getString(4));
                int experience=Integer.parseInt(resultSet.getString(5));
                double salary=Double.parseDouble(resultSet.getString(6));

                Instructors instructor=new Instructors(instrId,cid,name,age,experience,salary);
                for(int i=0;i<classrooms.size();i++){
                    if(classrooms.get(i).getCid() == cid){
                        classrooms.get(i).instructors.add(instructor);
                    }
                }
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public void writeData(){

        try{
            // STEP1 -- Load the JDBC driver
            Class.forName(JDBC_DRIVER);
            System.out.println("Driver loaded");

            // STEP 2 -- Establish a connection
            System.out.println("Establishing a connection");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "hamza", "1234");
            System.out.println("Database connected");


            // STEP 3 -- Create a statement
            PreparedStatement stmt = con.prepareStatement("DELETE FROM classrooms");
            stmt.executeUpdate();

            System.out.println("there are "+classrooms.size()+" students");

            for(int i=0;i<classrooms.size();i++){

                PreparedStatement preparedStatement=con.prepareStatement("INSERT INTO classrooms VALUES (?,?)");
                preparedStatement.setInt(1,classrooms.get(i).getCid());
                preparedStatement.setString(2,classrooms.get(i).getLocation());
                preparedStatement.executeUpdate();
            }

            stmt = con.prepareStatement("DELETE FROM student");
            stmt.executeUpdate();
            stmt = con.prepareStatement("DELETE FROM instructors");
            stmt.executeUpdate();

            for(int i=0;i<classrooms.size();i++){

                for(int j=0;j<classrooms.get(i).students.size();j++){
                    PreparedStatement preparedStatement=con.prepareStatement("INSERT INTO student VALUES (?,?,?,?,?,?,?,?,?)");
                    Student student = classrooms.get(i).students.get(j);

                    preparedStatement.setInt(2,student.getSid());
                    preparedStatement.setInt(3,student.getCid());
                    preparedStatement.setString(4,student.getFullName());
                    preparedStatement.setString(5,student.getAdmissionYear());
                    preparedStatement.setDouble(6,student.getCgpa());
                    String gender="f";
                    if(student.isGender()){
                        gender="m";
                    }
                    preparedStatement.setString(7,gender);

                    if(classrooms.get(i).students.get(j) instanceof UndergraduateStudent){
                        UndergraduateStudent undergraduateStudent = (UndergraduateStudent) student;
                        preparedStatement.setString(1,"u");
                        preparedStatement.setInt(8,undergraduateStudent.getActivityGrades());
                        preparedStatement.setInt(9,0);
                    }

                    else{
                        MasterStudent masterStudent=(MasterStudent) student;
                        preparedStatement.setInt(8,0);
                        preparedStatement.setInt(9,masterStudent.getResearchGrades());
                    }

                    preparedStatement.executeUpdate();

                }

                for(int z=0;z<classrooms.get(i).instructors.size();z++){
                    PreparedStatement preparedStatement=con.prepareStatement("INSERT INTO instructors VALUES (?,?,?,?,?,?)");

                    Instructors instructor=classrooms.get(i).instructors.get(z);

                    preparedStatement.setInt(1,instructor.getInstrId());
                    preparedStatement.setInt(2,instructor.getCid());
                    preparedStatement.setString(3,instructor.getName());
                    preparedStatement.setInt(4,instructor.getAge());
                    preparedStatement.setInt(5,instructor.getExperience());
                    preparedStatement.setDouble(6,instructor.getSalary());
                    preparedStatement.executeUpdate();
                }
            }

            try{

                FileOutputStream fileOutputStream=new FileOutputStream("classrooms.txt");
                ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(classrooms);
                objectOutputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try{
                //generating md5 file part

                FileInputStream fileInputStream=new FileInputStream("classrooms.txt");

                MessageDigest algorithm=MessageDigest.getInstance("MD5");

                byte []buffer=new byte[1024];

                int ch;

                while((ch=fileInputStream.read()) != -1){
                    algorithm.update(buffer,0,ch);
                }


                byte digest[]=algorithm.digest();

                StringBuilder hexString=new StringBuilder();

                for(int j=0;j<digest.length;j++){
                    hexString.append(Integer.toHexString(0XFF&digest[j]));
                    hexString.append(" ");
                }

                String md5=hexString.toString();



                FileWriter fileWriter=new FileWriter("md5.txt");
                fileWriter.write(md5);
                fileWriter.close();

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


}
