
package analisislexico;

import javax.swing.JOptionPane;
import java.io.IOException;

public class AnalisisLexico {
    CuadroDialogo c = new CuadroDialogo();
    
    public void inicio(){
        String mensaje = "Bienvenido! \n"
                + "El programa realiza un análisis léxico"
                + "\nde un archivo con extensión .txt";
        c.mensaje(mensaje,1);
    }
    
    public int menuPrincipal(){
        String[] opciones = {"1.-Analizar archivo", "2.-Ver categorías léxicas",
                            "3.-Salir"};        
        String respuesta = c.capBox("Menu Principal", opciones);
        return Integer.parseInt(respuesta.charAt(0)+"");
    }
    
    public static void main(String[] args) throws IOException{
        AnalisisLexico p = new AnalisisLexico();
        CuadroDialogo c = new CuadroDialogo();
        Lenguaje l = new Lenguaje();
        Analizador a = new Analizador();
        int eleccion=0;
       
        p.inicio();
        do{
            eleccion = p.menuPrincipal();
            switch(eleccion){
                case 1: a.analizar();
                        System.out.println(a); break;
                case 2: c.mensaje(l.toString()); break;
                case 3: c.mensaje("Adios",-1); break;
            }
        }while(eleccion!=3);
    }
}
