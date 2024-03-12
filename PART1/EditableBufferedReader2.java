package PART1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
parse:
HOME: ESC O H 
END: ESC O F
RIGHT: ESC [ C
LEFT: ESC [ D
HOME: ESC [ 1 ~
INS: ESC [ 2 ~
DEL: ESC [ 3 ~
END: ESC [ 4 ~
*/


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ariadna
 */
public class EditableBufferedReader2 extends BufferedReader {

    public EditableBufferedReader2(Reader in) {
        super(in);
    }

    /*
    public static void main(String[] args) {
        try {
            
            System.out.println("passem a mode raw");
            setRaw();
            Thread.sleep(3000);
            unsetRaw();
            System.out.println("Hem tornat a mode cooked");
             

            BufferedReader in = new EditableBufferedReader(
                   new InputStreamReader(System.in));

            int c = in.read();

            System.out.println("Caràcter llegit:" + c);

        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }*/
    public static void setRaw() throws IOException, InterruptedException {

        List<String> command = new ArrayList<>();
        command.add("/bin/sh");
        command.add("-c");
        command.add("stty -echo raw");

        ProcessBuilder pb = new ProcessBuilder(command);

        pb.inheritIO();

        Process p = pb.start();
        p.waitFor();

    }

    public static void unsetRaw() throws IOException, InterruptedException { // passa de mode RAW a COOKED

        List<String> command = new ArrayList<>();
        command.add("/bin/sh");
        command.add("-c");
        command.add("reset");

        ProcessBuilder pb = new ProcessBuilder(command);

        pb.inheritIO();

        Process p = pb.start();
        p.waitFor();

    }

    public int read() throws IOException {
        /*
        int nextChar = super.read();
        if (nextChar == Line.ANSI_ESCAPE) {

            nextChar = super.read(); // Leer el siguiente carácter para determinar si es una secuencia de escape

            if (nextChar == '[') { // Si es una secuencia de escape, leer el tercer carácter (el comando de control)

                nextChar = super.read();
                
                switch (nextChar) {
                    case 'C':
                        return Line.LEFTS;
                    case 'D':
                        return Line.CURSOR_FORWARD;
                    case 'H':
                        return Line.HOME;
                    case 'F':
                        return Line.END;
                    case '2':
                        nextChar = super.read();
                        
                        return Line.INS;
                    case '3':
                        nextChar = super.read();
                        return Line.DELETE;
                    default:
                        nextChar = super.read();
                        break;

                }

            }
        }
        
        if (nextChar == Line.EOT | nextChar == Line.EXIT) {
            try {
                this.unsetRaw();
            } catch (InterruptedException ex) {
                Logger.getLogger(EditableBufferedReader.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
        }
        
        return nextChar;
*/
        int c = super.read();
        if (c != Keys.ESCAPE)
            return c;
        switch(c = super.read()){
            case 'O':
                switch (c = super.read()){
                    case 'H': return Keys.HOME;
                    case 'F': return Keys.END;
                    default: return c;
                    
                }
            case '[':
                switch(c = super.read()){
                    case 'D' : return Keys.LEFT;
                    case 'C': return Keys.RIGHT;
                    case '1': if(super.read() == '~')
                                    return Keys.HOME;
                    case '2': if(super.read() == '~')
                                    return Keys.INS;
                    case '3': if(super.read() == '~')
                                    return Keys.DEL;
                    case '4': if(super.read() == '~')
                                    return Keys.END;
                    default: return c;
                    
                }
            default: return c;
        }   
    }

    public String readLine() throws IOException {
        Line2 line = new Line2();
        try {
            setRaw();
        } catch (InterruptedException ex) {
            Logger.getLogger(EditableBufferedReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        int r = read();
        while (r != Keys.RETURN) {
            switch (r) {
                case Keys.LEFT:
                    if (line.moveCursorLeft())
                        System.out.print(Keys.ANSI_LEFT);
                    break;
                case Keys.END:
                    line.moveCursorFin();
                    System.out.print(Keys.ANSI_END);
                    break;
                case Keys.RIGHT:
                    if (line.moveCursorRight())
                        System.out.print(Keys.ANSI_RIGHT);
                    break;
                case Keys.HOME:
                    line.moveCursorHome();
                    System.out.print(Keys.ANSI_HOME);
                    break;
                case Keys.DEL:
                    line.delete();
                    System.out.print(Keys.ANSI_DEL);
                    break;
                case Keys.INS:
                    line.ins();
                    System.out.print(Keys.ANSI_HOME);
                    break;
                default:
                    line.add((char)r);
                    System.out.print((char) r);
            }
            r = read();
        }
        
        try {
            this.unsetRaw();
        } catch (InterruptedException ex) {
            Logger.getLogger(EditableBufferedReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return line.getText();
    }

}
