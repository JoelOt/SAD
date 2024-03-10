package MVC;

import java.util.Observable;
import java.util.Observer;

public class Console implements Observer{

    public Console(Observable o) {
        Observable LineMVC = o;
    }

    public void update(Observable LineMVC, Object arg) {
        System.out.print(arg);
    }
}
