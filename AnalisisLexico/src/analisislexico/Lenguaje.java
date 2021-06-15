
package analisislexico;

public class Lenguaje {
    String[] reservadas = {"Programa", "Real", "Entero", 
        "Leer", "Escribir", "Inicio", "Fin"};
    char[] simbolos = {';', '=', '+', '-', '/', '(', ')', ','};
    
    public boolean isReservada(String token){
        for (int i = 0; i < reservadas.length; i++) {
            if(reservadas[i].equals(token)) //Si token el igual, verdadero
                return true;
        } return false; // de otro modo falso
    }   
    
    public boolean isSimbolo(String st){
        char s = st.charAt(0);
        if(st.length()!=1 )
            return false; //Si el caracter tiene más de un simbolo, falso
        else //Sino, revisa si st pertenece a simbolos
            return s == 40 || s== 41 ||
            s == 43 || s == 44 || s==45 ||
            s == 47 || s == 59 || s== 61 ;
    }
    
    public boolean isEntero(String s){
        if(s.charAt(0)==48)//No empiezan con 0
            return false;
        for (int i = 1; i < s.length(); i++) {
            if(s.charAt(i)>=58 || s.charAt(i)<=47)
                //Falso si contiene otra cosa que no sea dígitos
                return false; 
        }return true;
    }

    public boolean isDecimal(String s){
        boolean punto = false;
        for (int i = 0; i < s.length(); i++) {
            if(punto){  //Solo contiene un punto
                if(s.charAt(i)>=58 || s.charAt(i)<=47)
                    //Falso si contiene otra cosa que no sea dígitos
                    return false; 
            }else{  
                if(s.charAt(i)==46)
                    punto = true;
            }
        }if (punto) return true;   //Deben contener un punto
        else        return false;  
    }
    //Letras Mayúsculas 65-90
    //Letras Minúsculas 97-122
    //Digitos 48-57
    //Punto 46
    //Guión bajo 95
    
    public boolean isIdentificador(String s){
        char simbolo = 0;
        for (int i = 0; i < s.length(); i++) {
            simbolo = s.charAt(i);
            if(simbolo<=90 && simbolo>=65)  
                return false; //Si incluye mayúsculas, falso
            else if(simbolo==95 &&
                    (i==(s.length()-1 ) || i==0))
                return false; //Si el "_" esta al inicio o final, falso
            else if((simbolo<=96 || simbolo>=123) && simbolo!=95)
                return false; //Si no es letra y no es "_", falso
        }
        return true; //Verdadero de otro modo
    }
    
    //Para impresión, muestra las reglas de construcción
    @Override
    public String toString(){
        String cadena="Reglas de construcción de categorías léxicas";
        cadena += "\n\nPalabras reservadas: {Programa, Real, " +
                  "\nEntero, Leer, Escribir, Inicio, Fin}";
        cadena += "\n\nIdentificadores: de la forma "
                + "\n(a-zA-Z)(_+(a-zA-Z))*((a-zA-Z)+^)";
        cadena += "\n\nNumeros enteros: de la forma"
                + "\n(1-9)(0-9)* | 0";
        cadena += "\n\nNumeros de punto flotante: de la forma"
                + "\n(0-9)(0-9)* . (0-9)(0-9)*";
        cadena += "\n\nCaracteres simples: "
                + "\n{';', '=', '+', '-', '/', '(', ')', ','}";
        return cadena;
    }
}







