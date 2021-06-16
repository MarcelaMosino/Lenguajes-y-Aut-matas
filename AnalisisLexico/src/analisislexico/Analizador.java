
package analisislexico;

import java.io.IOException;
import java.util.Arrays;


public class Analizador {
    CuadroDialogo c = new CuadroDialogo();
    Archivo a = new Archivo();
    Lenguaje l = new Lenguaje();
    //Cabeceras para impresión con formato
    String columnas = "\nLexema\t\tCategoría\tAtributo";
    String tab = "***********************************************";
    Lista<Token> tC = new Lista(tab+"Tabla de simbolos"+columnas);
    Lista<Token> tI = new Lista(tab+"Tabla de identificadores"+columnas);
    Lista<Token> tR = new Lista(tab+"Tabla de palabras reservadas"+columnas);
    Lista<String> tE = new Lista(tab+"Tabla de errores");
  
    public Analizador(){//Evitamos quedarnos con registros de 
        tC.vaciar();    //análisis anteriores, pues se puede
        tI.vaciar();    //leer más de un archivo por ejecución
        tR.vaciar();
        tE.vaciar();
    }
    
    void analizar() throws IOException{
        int renglon = 1;
        a.abre(); //Abre el archivo a analizar
        
        while(a.entrada.hasNext()){
            analizaRenglon(a.leerLinea(), renglon);
            renglon++; //lee linea por línea
        } a.cierra(); //cierra el archivo
        a.escribir(toString()); //escribe resultados en un archivo
        //Mensaje de éxito en caso de no tener errores
        if(tE.estaVacia()) c.mensaje("Sin errores léxicos");
    }
    
    void analizaRenglon(String contenido, int renglon){
        int i = 0;
        char simbolo =0;
               
        for (int j = 0; j < contenido.length(); j++) {
            simbolo = contenido.charAt(j);
            if(i==j){
                if(simbolo==9 || simbolo==32)
                    i=j+1;  //Salto de espacios en blanco
                else if(l.isSimbolo(simbolo+"")){
                    generaToken(contenido.substring(i,j+1), renglon);
                    i++;    //Genera Token de simbolos aislados
                }             
            } else{
                if(simbolo==9 || simbolo==32){ 
                    generaToken(contenido.substring(i, j), renglon);
                    i=j+1;  //Genera token de palabras luego de un espacio
                }else if(l.isSimbolo(simbolo+"")){
                    generaToken(contenido.substring(i, j), renglon);
                    generaToken(contenido.substring(j, j+1), renglon);
                    i=j+1;  //Genera token para palabra y caracter
                            //en caso de que estén concantenados
                }else if(j==(contenido.length()-1)){
                    generaToken(contenido.substring(i, j+1), renglon);
                    i=j+1;  //Genera token de palabras al final de la linea
                }
            }
        }
    }

    //Letras Mayúsculas 65-90
    //Letras Minúsculas 97-122
    //Digitos 48-57
    //Punto 46
    //Guión bajo 95
    
    private int analizaCaso(String substring) {
        char simbolo = substring.charAt(0);
        int caso = 4;
        
        if(simbolo<=90 && simbolo>=65)        caso = 0; //Palabras reservadas
        else if(simbolo<=122 && simbolo>=97)  caso = 1; //Identificadores
        else if(simbolo<=47 && simbolo>=33)   caso = 2; //Simbolos
        else if(simbolo<=57 && simbolo>=48)   caso = 3; //Numeros
        return caso;
    }   

    private void generaToken(String substring, int renglon) {
        switch(analizaCaso(substring)){
            case 0: //Revisa que la palabra reservada sea correcta
                if(l.isReservada(substring)){
                    tR.add(new Token(substring));
                    tC.add(new Token(substring));
                } else{
                    tE.add("Palabra reservada mal escrita renglon "+renglon);}
                break;
            case 1: //Revisa que el identificador sea correcto
                if(l.isIdentificador(substring)){
                    tI.add(new Token(substring, 750));
                    tC.add(new Token(substring, 750));
                } else{
                    tE.add("Identificador mal escrito en renglon "+renglon);}
                break;
            case 2: //Revisa que los caracteres sean válidos
                if(l.isSimbolo(substring))
                    tC.add(new Token(substring, substring.charAt(0)));                
                else
                    tE.add("Simbolo no reconocido en renglon "+renglon);
                break;
            case 3: //Revisa los 2 formatos de números
                if(l.isEntero(substring))
                    tC.add(new Token(substring, 333));                
                else if(l.isDecimal(substring))
                    tC.add(new Token(substring, 334));                
                else
                    tE.add("Numero mal escrito en renglon "+renglon);
                break;
        }
    }
    
    @Override
    public String toString(){ //Para impresión
        String cadena = "Análisis Léxico\n";
        if(!tE.estaVacia())
            cadena += "\n\n" + tE ;
        if(!tI.estaVacia())
            cadena += "\n\n"+ tI ;
        if(!tR.estaVacia())
            cadena += "\n\n" + tR;
        if(!tC.estaVacia())
            cadena += "\n\n" + tC;
        return cadena;
    }
}


        