/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PART1;

/**
 *
 * @author 
 */
public class Keys {

   
    protected static final String ANSI_ESC = "\033";
    protected static final String ANSI_RIGHT = ANSI_ESC + "[C";
    protected static final String ANSI_LEFT = ANSI_ESC + "[D";
    protected static final String ANSI_HOME = ANSI_ESC + "[1G";
    //protected static final String ANSI_END = ANSI_ESC+ [ + <nombre de cols> + C;
    protected static final String ANSI_INS = ANSI_ESC + "[2~";
    protected static final String ANSI_RET = "\r";
    protected static final String ANSI_DEL = ANSI_ESC + "[1P";
    protected static final String ANSI_DEL_UNTIL_END = ANSI_ESC + "[K";
    
    protected static final int ESCAPE = 27;
    protected static final int RIGHT = 67;
    protected static final int LEFT = 68;
    protected static final int HOME = 72;
    protected static final int END = 70;
    protected static final int INS = 155;
    protected static final int RETURN = 13;
    protected static final int BCKS = 8;
    protected static final int DEL = 127;
    
}
