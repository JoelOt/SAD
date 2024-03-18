package PROPETY_CHANGE;

import PART1.Keys;
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

    public int getCursorPosition() {
        return posCursor;
    }

    public void add(char c) {
        if (posCursor == text.length()) {
            text.append(c);
            propertyChangeSupport.firePropertyChange("text", null, c);
        }else{
            propertyChangeSupport.firePropertyChange("delall", null, Keys.ANSI_DEL_UNTIL_END);
            if(!modeins){
                fi = text.substring(posCursor);
                text.setLength(posCursor);
                text.append(c);
                text.append(fi);
                propertyChangeSupport.firePropertyChange("text", null, c + this.fi.toString() + "\033" + '[' + (this.getCursorPosition()+1) + "G");

            }else{
                fi = text.substring(posCursor-1);
                text.setLength(posCursor-1);
                text.append(c);
                text.append(fi);
                propertyChangeSupport.firePropertyChange("text", null, c + this.fi.toString() + "\033" + '[' + (this.getCursorPosition()+1) + "G");
            }
        }
        if (!modeins) {
            posCursor++;
        }
    }

    public void delete() {  //
        text.deleteCharAt(posCursor);
        posCursor--;
        propertyChangeSupport.firePropertyChange("text", null, Keys.ANSI_LEFT + Keys.ANSI_DEL);
    }
    public boolean moveCursorLeft() {  //
        if (posCursor == 0) {
            return false;
        } else {
            posCursor--;
            propertyChangeSupport.firePropertyChange("text", null, Keys.ANSI_LEFT);
            return true;
        }
    }

    public boolean moveCursorRight() { //
        if (posCursor == text.length()) {
            return false;
        } else {
            posCursor++;
            propertyChangeSupport.firePropertyChange("text", null, Keys.ANSI_RIGHT);
            return true;
        }
    }

    public void moveCursorHome() {  //
        posCursor = 0;
        propertyChangeSupport.firePropertyChange("text", null, Keys.ANSI_HOME);

    }

    public void moveCursorFin() { //
        posCursor = text.length();
        propertyChangeSupport.firePropertyChange("text", null, Keys.ANSI_HOME + Keys.ANSI_ESC + '[' + this.getCursorPosition() + 'C');
    }

    public void modeins() { // CONMUTA SOBRE INSERTAR/SOBREESCRIURE
        modeins = !modeins;
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
