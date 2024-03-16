package MVC;

import java.util.Observable;
import PART1.Keys;

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

    public boolean delete() {
            if (posCursor > 0){
                text.deleteCharAt(posCursor-1);
                posCursor--;
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
        this.notifyObservers("\033" + '[' + (this.getCursorPosition()+1) + "G");
    }

    public void modeins() { // CONMUTA SOBRE INSERTAR/SOBREESCRIURE
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
