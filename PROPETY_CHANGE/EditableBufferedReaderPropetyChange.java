package PROPETY_CHANGE;

import PART1.EditableBufferedReader;
import PART1.Keys;

import java.io.*;

public class EditableBufferedReaderPropetyChange extends EditableBufferedReader{
    
    public EditableBufferedReaderPropetyChange(Reader in) {
        super(in);
    }

    public String readLine() throws IOException {

        LineSupport line = new LineSupport();
        ConsoleListener Console = new ConsoleListener(line);

            this.setRaw();

        int r = read();
        while (r != Keys.RETURN) {
            switch (r) {
                case Keys.LEFT:
                    if (line.moveCursorLeft())
                        break;
                case Keys.END:
                    line.moveCursorFin();
                    break;
                case Keys.RIGHT:
                    if (line.moveCursorRight())
                        break;
                case Keys.HOME:
                    line.moveCursorHome();
                    break;
                case Keys.DEL:
                    line.delete();
                    break;
                case Keys.INS:
                    line.modeins();
                    break;
                default:
                    line.add((char) r);
            }
            r = read();
        }

            this.unsetRaw();

        return line.getText();
    }

}
