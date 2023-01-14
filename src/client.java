import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class client extends JFrame {

    public JPanel panel1;
    private JTextField classroomId;
    private JTextField studentId;
    private JTextArea classroomLocation;
    private JTextField studentClassroomId;
    private JTextField studentFullName;
    private JTextField studentAdmissionYear;
    private JTextField studentCgpa;
    private JTextField studentGender;
    private JTextField studentActivityGrades;
    private JTextField studentResearchGrades;
    private JTextField instructorId;
    private JTextField instructorClassroomId;
    private JTextField instructorName;
    private JTextField instructorAge;
    private JTextField instructorExpierence;
    private JTextField instructorSalary;
    private JButton addClassroomButton;
    private JButton addStudentButton;
    private JButton addInstructorButton;


    PrintWriter toServer;

    BufferedReader fromServer;

    class deleteClassroomFrame extends JFrame{ // inner class? hardcoded?

    }

    client.deleteClassroomFrame deleteClassroomFrame=new deleteClassroomFrame();


    public client(){



        try{

            Socket clientSocket=new Socket("localhost",8000);
            toServer=new PrintWriter(clientSocket.getOutputStream(),true);
            fromServer=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        addClassroomButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                int flag=0;

                if(classroomId.getText().isEmpty() || classroomLocation.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Enter all fields");
                }

                String location=classroomLocation.getText();

                toServer.println("classRoomId;"+classroomId.getText()+";"+location);


                try{
                    String reply=fromServer.readLine();
                    System.out.println(reply);
                    JOptionPane.showMessageDialog(null,reply);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        addStudentButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                toServer.println("studentId"+";"+studentId.getText()+";"+studentFullName.getText()+";"+studentAdmissionYear.getText()+";"+studentCgpa.getText()+";"+studentGender.getText()+";"+studentClassroomId.getText()+";"+studentActivityGrades.getText()+";"+studentResearchGrades.getText());



            }
        });
        addInstructorButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {




            }
        });

    }

    public static void main(String[] args) {
        client client=new client();
        client.setVisible(true);
        client.setContentPane(client.panel1);
        client.setTitle("Hello");
        client.setSize(300,400);
        client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.pack();

        client.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                client.toServer.println("Save in database");
            }
        });

    }




}
