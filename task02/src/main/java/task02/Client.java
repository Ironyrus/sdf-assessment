package task02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main( String[] args ){
        String website = "task02.chuklee.com";
        int port = 80;

        try {
            Socket soc = new Socket(website, port);
            DataOutputStream dataOut = new DataOutputStream(soc.getOutputStream());
            DataInputStream dataIn = new DataInputStream(soc.getInputStream());

            ObjectOutputStream dataOut2 = new ObjectOutputStream(dataOut);
            ObjectInputStream dataIn2 = new ObjectInputStream(dataIn);

            String str = (String)dataIn2.readUTF();
            String[] strArr = str.split(" ");
            String[] intArr = strArr[1].split(",");
            float ans = 0;
            float count = 0;
            for (String item : intArr) {
                ans += Float.parseFloat(item);
                count++;
            }

            ans = Math.round(ans)/count;

            dataOut2.writeUTF(strArr[0]);
            dataOut2.writeUTF("Muhammad Ridhwan Bin Zainal Abidin");
            dataOut2.writeUTF("vans_iwan@yahoo.com");
            dataOut2.writeFloat(ans);
            dataOut2.flush();

            boolean result = dataIn2.readBoolean();
            if(result == false){
                String reason = dataIn2.readUTF();
                System.out.println("FAILED");
                System.out.println(reason);
                soc.close();
            } else{
                soc.close();
                System.out.println("SUCCESS");
            }
            

        } catch (Exception e) {
            e.printStackTrace();;
        }
        
    }
}