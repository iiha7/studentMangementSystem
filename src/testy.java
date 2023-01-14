import javax.swing.*;

public class testy extends JFrame{
    private JTextField textField1;
    private JPanel jpanee;
    private JTable table1;

    public static void main(String[] args) {
        testy frame=new testy();
        frame.setContentPane(frame.jpanee);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);

        frame.pack();
    }
}
