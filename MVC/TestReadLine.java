package MVC;
import java.io.*;

import PART1.EditableBufferedReader2;

public class TestReadLine {
    public static void main(String[] args) {
        BufferedReader in = new EditableBufferedReader2(
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