package OBS;

import java.io.*;

public class TestReadLineObs{
    public static void main(String[] args) {
        BufferedReader in = new EditableBufferedReaderObs(
            new InputStreamReader(System.in));
        String str = null;
        try {
            str = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println( "\nline is: " + str);
    }
}