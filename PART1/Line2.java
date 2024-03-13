/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package readLine;

/**
 *
 * @author ariadna
 */
public class Line {

    protected StringBuilder text;
    protected static int posCursor;
    protected boolean modeins;
    protected String inici;
    protected String fi;


    public Line() {
        text = new StringBuilder();
        inici = new String();
        fi = new String();
        posCursor = 0;
        modeins = false;

    }

    public void add(char c) {
        
        if(posCursor == text.length()){
            text.append(c);
        }else{
            if(!modeins){
                fi = text.substring(posCursor);
                text.setLength(posCursor);
                text.append(c);
                text.append(fi);
            }else{
                fi = text.substring(posCursor-1);
                
                text.setLength(posCursor-1);
                text.append(c);
                text.append(fi);
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
            return true;
        }
    }

    public boolean moveCursorRight() {
        
        if (posCursor == text.length()){
            return false;
        }else {
            posCursor++;
            return true;
        }

    }

    public void moveCursorHome() {
        posCursor = 0;

    }

public void moveCursorFin() {
        posCursor = text.length();
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

}
