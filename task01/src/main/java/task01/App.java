package task01;

/*
 * 
vans_@LAPTOP-AS886SBL MINGW64 ~/VISA NUS-ISS VTTP/sdfassessment/task01 (main)
$ javac src/main/java/task01/App.java
vans_@LAPTOP-AS886SBL MINGW64 ~/VISA NUS-ISS VTTP/sdfassessment/task01/src/main/java (main)
$ java task01.App tour_packages.csv tour_packages.txt
 */

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
         //File csvFile = new File("thankyou.csv"); //args[0]
         //File txtFile = new File("thankyou.txt"); //args[1]
        File csvFile = new File(args[0]); //args[0]
        File txtFile = new File(args[1]); //args[1]
        ArrayList<String> csvData = new ArrayList<>();

        try {
           Scanner csvScanner = new Scanner(csvFile);
           while(csvScanner.hasNextLine()){
                String line = csvScanner.nextLine();
                csvData.add(line);
            }
            csvScanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int row = csvData.size();
        String[] colcount = csvData.get(0).split(",");
        int col = colcount.length;

        String[][] csvArray = new String[row][col];
        for(int i = 0; i < csvData.size(); i++){
            String[] temp = csvData.get(i).split(",");
            for(int j = 0; j < col; j++){
                csvArray[i][j] = temp[j];
            }
        }
        
        //Converting text file into a string
        String txtContents = "";
        try {
            FileReader fr = new FileReader(txtFile);
            int content;
            while((content = fr.read()) != -1){
                txtContents += (char)content;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] txtData = new String[1];
        txtData[0] = txtContents;
        for (int i = 1; i < csvArray.length; i++) {
            for (int j = 0; j < csvArray[0].length; j++) {
                txtContents = txtContents.replace(("__" + csvArray[0][j] + "__"), csvArray[i][j]);
                txtContents = txtContents.replace("\\n","\n");
            }   
            System.out.println(txtContents);
            txtContents = txtData[0];
        }
    }
}