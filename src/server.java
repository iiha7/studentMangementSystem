import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class server {



    public server(){

        try{
            ServerSocket serverSocket=new ServerSocket(8000);

            while(true){
                Socket clientSocket=serverSocket.accept();

                Thread t=new Thread(new ClientHandler(clientSocket));
                t.start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        new server();
    }

}



class ClientHandler implements Runnable{

    private Socket clientSocket;

    Database database;
    public ArrayList<Classroom> classrooms;

    public ClientHandler(Socket clientSocket){
        classrooms=new ArrayList<>();
        this.clientSocket=clientSocket;
        database=new Database(classrooms);
        database.readData();

    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {

        try{

            PrintWriter toClient=new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader fromClient=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


            while(true){

                int flag=0;
                String msg=fromClient.readLine();
                System.out.println(msg);
                String []msgList=msg.split(";");

                System.out.println(msgList[0]);

                if(msgList[0].equals("classRoomId")){

                    System.out.println("im here");

                    int cid=Integer.parseInt(msgList[1]);
                    String location=msgList[2];

                    for(int i=0;i<classrooms.size();i++){
                        if(cid == classrooms.get(i).getCid()){
                            flag=1;
                        }
                    }

                    if(flag == 0){
                        Classroom classroom=new Classroom(cid,location);
                        classrooms.add(classroom);
                        toClient.println("Classroom is added!");
                    }
                    else{
                        toClient.println("Classroom is already available!");
                    }

                }

                else if(msgList[0].equals("studentId")){
                    int sid=Integer.parseInt(msgList[1]);
                    int cid=Integer.parseInt(msgList[6]);

                    for (Classroom classroom : classrooms) {
                        if (classroom.getCid() == cid) {
                            for (int j = 0; j < classroom.students.size(); j++) {
                                if (classroom.students.get(j).getSid() == sid) {
                                    flag = 1;
                                }
                            }
                        }
                    }


                }
                else if(msgList[0].equals("Save in database")){
                    database.writeData();
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        finally {
            try{
                clientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
