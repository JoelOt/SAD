package MVC;

import java.util.Observable;

public class LineMVC extends Observable{
    protected StringBuilder text;
    protected static int posCursor;
    protected boolean modeins;
    protected String inici;
    protected String fi;

    public LineMVC(){ 
        text = new StringBuilder();
        inici = new String();
        fi = new String();
        posCursor = 0;
        modeins = false;

        Console con = new Console(this);
        this.addObserver(con);
    }
   

    public void add(char c) {
        
        if(posCursor == text.length()){
            text.append(c);

        }else{
            fi = text.substring(posCursor);
            text.setLength(posCursor);
            text.append(c);
            text.append(fi);
            
            
        }
        posCursor++;
        this.notifyObservers(c);

    }

    public void delete() {
        text.deleteCharAt(posCursor);
        posCursor--;
        this.notifyObservers(Keys.ANSI_DEL);
    }

    public boolean moveCursorLeft() {
        if (posCursor == 0){
            return false;
        }else {
            posCursor--;
            this.notifyObservers(Keys.ANSI_LEFT);
            return true;
        }
    }

    public boolean moveCursorRight() {
        
        if (posCursor == text.length()){
            return false;
        }else {
            posCursor++;
            this.notifyObservers(Keys.ANSI_RIGHT);
            return true;
        }

    }

    public void moveCursorHome() {
        posCursor = 0;
        this.notifyObservers(Keys.ANSI_HOME);

    }

public void moveCursorFin() {
        posCursor = text.length();
        this.notifyObservers(Keys.ANSI_END);
    }

    public void ins() { // CONMUTA SOBRE INSERTAR/SOBREESCRIURE
        modeins = !modeins;
        this.notifyObservers(Keys.ANSI_INS);
    }

    public String getText() {
        return text.toString();
    }

    public int getCursorPosition() {
        return posCursor;
    }

}