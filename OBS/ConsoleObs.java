package OBS;

import java.util.Observable;
import java.util.Observer;

public class ConsoleObs implements Observer{

    public ConsoleObs(Observable o) {
        Observable LineObs = o;
    }

    public void update(Observable LineObs, Object arg) {
        System.out.print(arg);
    }
}