package PROPETY_CHANGE;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ConsoleListener implements PropertyChangeListener{
    
    public ConsoleListener(LineSupport line) {
        line.addPropertyChangeListener(this);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        System.out.print(evt.getNewValue());
    }
}
