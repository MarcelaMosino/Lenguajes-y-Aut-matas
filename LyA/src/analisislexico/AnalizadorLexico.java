
package analisislexico;

import cola.Cola;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import pila.Pila;


public class AnalizadorLexico {
    Token tok;
    File archivo;
    public Scanner entrada;
    Lenguaje l = new Lenguaje();
    Cola<String> cola = new Cola();
    //Cabeceras para impresión con formato
    String columnas = "\nLexema\t\tCategoría\tAtributo";
    String tab = "***********************************************";
    Lista<Token> tI = new Lista(tab+"Tabla de simbolos"+columnas);
    Lista<Token> tR = new Lista(tab+"Tabla de palabras reservadas"+columnas);
  
    public AnalizadorLexico(){ }
    
    public AnalizadorLexico(String a) throws IOException{
        archivo = new File(a);
        entrada = new Scanner(archivo);
        tI.vaciar();    //análisis anteriores, pues se puede
        tR.vaciar();    //leer más de un archivo por ejecución
    }
    
    public Token nuevoToken() {//Regresa el siguiente Token válido
        tok =null;
        String palabra="";
        if(cola.isEmpty()){
            palabra = entrada.next();
            separaTokens(palabra);//Encola los posibles tokens de la cadena leída
            tok = generaToken(cola.dequeue());//Genera el token siguiente de la cola
        }else
            tok = generaToken(cola.dequeue());        
        return tok;
    }    
    
    void separaTokens(String palabra){
        int i = 0;
        char s = 0;
        
        for (int j = 0; j < palabra.length(); j++) {
            s = palabra.charAt(j);
            if(i==j){
                if(s==9 || s==32)
                    i=j+1;  //Salto de espacios en blanco
                if(l.isSimbolo(s+"")){
                    cola.enqueue(palabra.substring(j,j+1));
                    i++;//Encola simbolos aislados o al inicio
                }
            }else{
                if(l.isSimbolo(s+"")){
                    cola.enqueue(palabra.substring(i,j));//Palabras
                    cola.enqueue(palabra.substring(j, j+1));//Simbolos al final
                    i=j+1;
                }else if(j==(palabra.length()-1)){
                    cola.enqueue(palabra.substring(i, j+1));
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
    private int estado(String substring) {
        char simbolo = substring.charAt(0);
        int caso = 2;
        
        if(simbolo<=90 && simbolo>=65)        caso = 0; //Palabras reservadas
        else if(simbolo<=122 && simbolo>=97)  caso = 1; //Identificadores
        else if(simbolo<=47 && simbolo>=33)   caso = 2; //Simbolos
        else if(simbolo<=57 && simbolo>=48)   caso = 3; //Numeros
        return caso;
    }   

    public Token generaToken(String substring) {
        switch(estado(substring)){
            case 0: //Revisa que la palabra reservada sea correcta
                if(l.isReservada(substring)){
                    tok = new Token(substring);
                    tR.add(tok);
                } else{
                    System.out.println("Error\nPalabra reservada mal escrita");
                } break;
            case 1: //Revisa que el identificador sea correcto
                if(l.isIdentificador(substring)){
                    tok = new Token(substring, 750);
                    tI.add(tok);
                } else{                    
                    System.out.println("Error\nIdentificador mal escrito");
                } break;
            case 2: //Revisa que los caracteres sean válidos
                if(l.isSimbolo(substring))  
                    tok = new Token(substring, substring.charAt(0));
                else{
                    System.out.println("Error\nSimbolo no reconocido");
                } break;
            case 3: //Revisa los 2 formatos de números
                if(l.isEntero(substring))
                    tok = new Token(substring, 333); 
                else if(l.isDecimal(substring))
                    tok = new Token(substring, 334);                
                else{
                    System.out.println("Error\nNumero mal escrito");
                } break;
        }return tok;
    }
    
    @Override
    public String toString(){ //Para impresión
        String cadena = "Análisis Léxico\n";
        if(!tI.estaVacia())
            cadena += "\n\n"+ tI ;
        if(!tR.estaVacia())
            cadena += "\n\n" + tR;
        return cadena;
    }   
}


        