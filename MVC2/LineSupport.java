/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVC2;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import PART1.Keys;

/**
 *
 * @author ariadna
 */
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

    public void add(char c) {
        
        if(posCursor == text.length()){
            text.append(c);
            propertyChangeSupport.firePropertyChange("text", null, c);
        }else{
            if(!modeins){
                fi = text.substring(posCursor);
                text.setLength(posCursor);
                text.append(c);
                text.append(fi);
                propertyChangeSupport.firePropertyChange("text", null, c);
            }else{
                fi = text.substring(posCursor-1);
                text.setLength(posCursor-1);
                text.append(c);
                text.append(fi);
                for(int i = 0; i < this.text.length(); i++){
                    propertyChangeSupport.firePropertyChange(Integer.toString(i), null, "\b \b");
                }
                propertyChangeSupport.firePropertyChange("text", null, this.getText());
                propertyChangeSupport.firePropertyChange("text", null, "\u001B[" + this.getCursorPosition() + "G");
            }
        }
        if(!modeins){
        posCursor++;
        }
    }

    public boolean delete() {
        if (posCursor > 0){
            text.deleteCharAt(posCursor-1);
            posCursor--;
            propertyChangeSupport.firePropertyChange("text", null, Keys.ANSI_DEL);
            return true;
        }else{
            return false;
        }
    }

    public boolean moveCursorLeft() {
        if (posCursor == 0){
            return false;
        }else {
            posCursor--;
            propertyChangeSupport.firePropertyChange("text", null, Keys.ANSI_LEFT);
            return true;
        }
    }

    public boolean moveCursorRight() {
        
        if (posCursor == text.length()){
            return false;
        }else {
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

    public void modeins() { // CONMUTA SOBRE INSERTAR/SOBREESCRIURE
        modeins = !modeins;
    }
    
    public String getText() {
        return text.toString();
    }

    public int getCursorPosition() {
        return posCursor;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
}
