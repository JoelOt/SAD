public class Line {  //classe linia que conte la linia que s'esta escrivint i el cursor
    StringBuilder linia;  //linia que s'esta escrivint
    int cursor;  //posicio del cursor

    public Line(){ 
        linia = new StringBuilder();
        cursor = 0;
    }

    public String getlinia(){  //retorna la linia
        return linia.toString();
    }

    public int getCursor(){  //retorna la posicio del cursor
        return cursor;
    }
    
    public void printline() {  //imprimeix la linia
        if(cursor>0){
            String text = this.getlinia().toString();
            for(int i=0;i<text.length();i++){
                if(i==cursor){
                    System.out.print("\u001b[7m");  //highlight pos cursor
                    System.out.print(text.charAt(i));
                    System.out.print("\u001b[0m"); //treure el hisghlight
                }else{
                    System.out.print(text.charAt(i));
                }
            }
        }

    }
    
    public void Add_car(char c){  //afegeix el caracter c a la posicio del cursor al escriure
        linia.insert(cursor, c);
        cursor = cursor + 1;
        
    }

    public void Delete_car(){  //si es prem "backspace" esborra el caracter que hi ha davant del cursor
        linia = linia.deleteCharAt(cursor - 1);
        cursor = cursor - 1;
    }

    public void Move_cursor_left(){  //si es prem "left" el cursor es mou una posicio a l'esquerra           
        if(cursor > 0){
            cursor = cursor - 1;
        }
    }

    public void Move_cursor_right(){  //si es prem "right" el cursor es mou una posicio a la dreta
        if(cursor < linia.length()){
            cursor = cursor + 1;
        }
    }

    public void Move_cursor_home(){  //si es prem "home" el cursor es mou a la posicio inicial
        cursor = 0;
    }

}
