package PART1;
import java.io.*;

public class EditableBufferedReader extends BufferedReader {

    public EditableBufferedReader(InputStreamReader in) {
        super(in);
        setRaw();

    }

    public void setRaw() { // posa el terminal en mode raw
        String[] cmd = { "/bin/sh", "-c", "stty -echo raw </dev/tty" };
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unsetRaw() { // restaura el terminal al mode normal
        String[] cmd = { "/bin/sh", "-c", "stty sane </dev/tty" };
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clear() { // borra tot de la pantalla (hauria d'anar a line.java)
        System.out.print("\u001b[2J");
        System.out.print("\u001b[H");
    }

    public int read() throws IOException {
        try {
            int car = super.read(); // no se si he de modificar o no
            if (car == 27) { // si es esc, triem entre els diferents caracters que poden venir: [home, ]
                int car2 = super.read(); // el descartem que sera [
                car2 = super.read(); // es el numero que decideix quin es el caracter

                switch (car2) {
                    case 49: // home (1)
                        car = -2;
                        break;
                    case 50: // insert (2)
                        car = -3;
                        break;
                    case 51: // Delete (3)
                        car = -4;
                        break;
                    case 68: // Right
                        car = -5;
                        break;
                    case 67: // Left
                        car = -6;
                        break;

                    default:
                        break;
                }
            }
            return car;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String readLine() throws IOException { // llegeix una lina sencera a partir de read()
        Line line = new Line();
        try {

            int res = 0;
            while (res != 13) {
                res = read();
                switch (res) {
                    case -5: // right
                        line.Move_cursor_left();
                        break;
                    case -6: // left
                        line.Move_cursor_right();
                    default: // resta de caracters
                        line.Add_car((char) res);
                        break;
                }
                clear();
                line.printline();
            }
            unsetRaw();
            return line.getlinia();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // public String readLine() throws IOException { // llegeix una lina sencera a
    // partir de read()
    // try {
    // int res1 = read(); // 27 es ESC
    // int res2 = read();
    // int res3 = read();
    // int res4 = read();
    // unsetRaw();

    // String seq2 = String.valueOf(res1) + ", " + String.valueOf(res2) + ", " +
    // String.valueOf(res3) + ", " + String.valueOf(res4); //imprimeix 4 ints (per
    // les ANSI sequences)
    // return seq2;
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // return null;
    // }
}
