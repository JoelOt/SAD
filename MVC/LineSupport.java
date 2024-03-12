package MVC;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LineSupport {
    protected StringBuilder text;
    protected static int posCursor;
    protected boolean modeins;
    protected String inici;
    protected String fi;
    private PropertyChangeSupport propertyChangeSupport;

    public LineSupport() {
        text = new StringBuilder();
        inici = new String();
        fi = new String();
        posCursor = 0;
        modeins = false;
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public String getText() {
        return text.toString();
    }

    public void add(char c) {
        if (posCursor == text.length()) {
            text.append(c);
        } else {
            fi = text.substring(posCursor);
            text.setLength(posCursor);
            text.append(c);
            text.append(fi);
        }
        posCursor++;
        propertyChangeSupport.firePropertyChange("text", null, c);
    }

    public void delete() {
        text.deleteCharAt(posCursor);
        String aux = MVC.Keys.ANSI_DEL;
        posCursor--;
        propertyChangeSupport.firePropertyChange("text", null, aux);
    }

    public boolean moveCursorLeft() {
        if (posCursor == 0) {
            return false;
        } else {
            posCursor--;
            propertyChangeSupport.firePropertyChange("text", null, Keys.ANSI_LEFT);
            return true;
        }
    }

    public boolean moveCursorRight() {
        if (posCursor == text.length()) {
            return false;
        } else {
            posCursor++;
            propertyChangeSupport.firePropertyChange("text", null, Keys.ANSI_RIGHT);
            return true;
        }
    }

    public void moveCursorHome() {
        posCursor = 0;
        propertyChangeSupport.firePropertyChange("text", null, Keys.ANSI_HOME);

    }

    public void moveCursorFin() {
        posCursor = text.length();
        propertyChangeSupport.firePropertyChange("text", null, Keys.ANSI_END);
    }

    public void ins() { // CONMUTA SOBRE INSERTAR/SOBREESCRIURE
        modeins = !modeins;
        propertyChangeSupport.firePropertyChange("text", null, Keys.ANSI_INS);
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
