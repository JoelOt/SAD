package MVC2;

import PART1.EditableBufferedReader2;
import PART1.Keys;

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
 */
public class EditableBufferedReaderPropetyChange extends EditableBufferedReader2 {

    public EditableBufferedReaderPropetyChange(Reader in) {
        super(in);
    }

    @Override
    public String readLine() throws IOException {
        LineSupport line = new LineSupport();
        ConsoleListener Console = new ConsoleListener(line);
        line.addPropertyChangeListener(Console);

        this.setRaw();

        int r = read();
        while (r != Keys.RETURN) {
            switch (r) {
                case Keys.LEFT:
                    line.moveCursorLeft();
                    break;
                case Keys.END:
                    line.moveCursorFin();
                    break;
                case Keys.RIGHT:
                    line.moveCursorRight();
                    break;
                case Keys.HOME:
                    line.moveCursorHome();
                    break;
                case Keys.DEL:
                    line.delete();
                    break;
                default:
                    line.add((char) r);
            r = read();
            }
        }
        this.unsetRaw();
        return line.getText();
    }
}
