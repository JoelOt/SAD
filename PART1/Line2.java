package PART1;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ariadna
 */
public class Line2 {

    protected StringBuilder text;
    protected static int posCursor;
    protected boolean modeins;
    protected String inici;
    protected String fi;


    public Line2() {
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
            fi = text.substring(posCursor);
            text.setLength(posCursor);
            text.append(c);
            text.append(fi);
            
        }
        posCursor++;

    }

    public void delete() {
        text.deleteCharAt(posCursor);
        posCursor--;
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

    public void ins() { // CONMUTA SOBRE INSERTAR/SOBREESCRIURE
        modeins = !modeins;
    }

    public String getText() {
        return text.toString();
    }

    public int getCursorPosition() {
        return posCursor;
    }

}
