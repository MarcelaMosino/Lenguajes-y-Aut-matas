package AnalizadorLS;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Archivo {
    File f1, f;
    String nombre;
    Scanner entrada;

    public Archivo(String nom){
        nombre = nom;
    }
    
    public int pregunta(String msg){
        return JOptionPane.showConfirmDialog(null,msg,
                "", JOptionPane.YES_NO_OPTION);
    }
    
    //Regresa el path de este archivo
    public String obtenPath(){
        File path = new File("Archivo.java");
        String p= path.getAbsolutePath();
        String acumula=""        ;
        
        for(int i=0; i<(p.length()-12);i++)
            acumula += p.charAt(i);
        return acumula;
    }
    
    public String nombreNuevo(){
        String n;
        do{
            n = JOptionPane.showInputDialog("Nombre del nuevo archivo");
            f1 = new File(n);
        } while(f1.exists()); //Captura hasta que el archivo no exista
        return n; //Regresa un nombre que no sobreescribe
    }
    
    public void escribeArchivo(String c)throws IOException{
        FileWriter fw = new FileWriter(f); fw.flush();        
       
        fw.write(c);
        fw.close();
    }
    
    public void escribir(String c)throws IOException{
        f1 = new File(nombre);         
        int r;
        
//        if(f1.exists()){ 
//            r = pregunta("Sobreescribir archivo");
//            if(r == 0)
//                f1.delete();
//            else 
//                nombre = nombreNuevo();
//        } 
        //Crea el archivo de salida en la ruta de Archivo.java
        f = new File(obtenPath()+nombre);
        escribeArchivo(c);
    }

    public void abre()throws IOException{
    	String s;
        do{
            s = JOptionPane.showInputDialog("Archivo a leer\n"
                    + "Debera incluir .txt y la ruta deberá ser\n "
                    + "igual a la de manifest.mf");
            f = new File(s);
        } while(!f.exists()); //Solamente abre archivos existentes
        nombre = s;
        f = new File(nombre);    
        entrada = new Scanner(f);
    }
    
    public String leerLinea()throws IOException{
        return entrada.nextLine();//Lee una sola linea
    }

    public void cierra()throws IOException{
        entrada.close();
    }    
}
