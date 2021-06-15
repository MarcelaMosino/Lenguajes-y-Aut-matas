package analisislexico;

import javax.swing.*;


public class CuadroDialogo {
    public boolean isNum(String cad){
        try{
            Integer.parseInt(cad);
            return true;
        }catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(null, "Solo Numeros", "Error",0);
            return false;
        }
    }
    
    public String cap(String mensaje){
        return JOptionPane.showInputDialog(mensaje);
    }
    
    public int capInt(String mensaje){
        String aux="";
        do{
            aux = cap(mensaje);
        }while(!isNum(aux));
        return Integer.parseInt(aux);
    }
    
    public String capBox(String mensaje, String[] el){
        return (String)JOptionPane.showInputDialog(null,mensaje,
                "Seleccione", 3, null, el, el[0]);
    }
    
    public void mensaje(String mens, int tipo){
        JOptionPane.showMessageDialog(null, mens,
        " Análisis Léxico", tipo);
    }
    
    //Imprime con imagen de Bob Esponja para diferenciar
    public void mensaje(String mens){
        JOptionPane.showMessageDialog(null, mens,
        " Automata Final Simplificado", -1, new ImageIcon("Res.jpg"));
    }
    
    public boolean confirm(String mensaje){
        if(JOptionPane.showConfirmDialog(null, mensaje)==0)
            return true;
        else return false;
    }    
}
