import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 extends Thread{

    @Override
    public void run() {
        try{
            //generating md5 file part

            File file1 =new File("classrooms.txt");

            if(file1.createNewFile()){
                System.out.println("Animal file created for the first time!");
            }

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

            String newMD5=hexString.toString();





            File file=new File("md5.txt");

            if(file.createNewFile()){
                System.out.println("MD5 file created for the first time!");
            }



            FileReader fileReader=new FileReader("md5.txt");
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String savedMD5="";
            String line;



            while((line=bufferedReader.readLine()) != null){
                savedMD5=line;
            }


            if(file.length() != 0){ // if length is 0, means first time application is opened
                if(savedMD5.equals(newMD5)){
                    System.out.println("Data is not corrupted and is secure");
                }

                else{
                    System.out.println("Serialized data has been corrupted!!");
                }
            }




        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
