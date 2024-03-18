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

   
    public static final String ANSI_ESC = "\033";
    public static final String ANSI_RIGHT = ANSI_ESC + "[C";
    public static final String ANSI_LEFT = ANSI_ESC + "[D";
    public static final String ANSI_HOME = ANSI_ESC + "[1G";
    //public static final String ANSI_END = ANSI_ESC+ [ + <nombre de cols> + C;
    public static final String ANSI_INS = ANSI_ESC + "[2~";
    public static final String ANSI_RET = "\r";
    public static final String ANSI_DEL = ANSI_ESC + "[1P";
    public static final String ANSI_DEL_UNTIL_END = ANSI_ESC + "[K";
    
    public static final int ESCAPE = 27;
    public static final int RIGHT = 67;
    public static final int LEFT = 68;
    public static final int HOME = 72;
    public static final int END = 70;
    public static final int INS = 155;
    public static final int RETURN = 13;
    public static final int BCKS = 8;
    public static final int DEL = 127;
    
}
