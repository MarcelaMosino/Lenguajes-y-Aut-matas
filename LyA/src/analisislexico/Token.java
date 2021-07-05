
package analisislexico;

public class Token {    
    public int atributo;  
        // 800-806 palabras reservadas
        // 750 identificadores
        // 333 enteros
        // 334 Flotantes
        // ASCII caracteres especiales
    public String lexema;
    String clasificacion;
    
    public Token(){
        atributo = 0;
        lexema = null;
        clasificacion = null;
    }
    
    //Crea un Token según su atributo
    public Token(String lexema, int atributo){
        this.atributo = atributo;
        this.lexema = lexema;
        switch(atributo){
            case 750: clasificacion = "Identificador"; break;
            case 333: clasificacion = "Entero"; break;
            case 334: clasificacion = "Flotante"; break;
            default: clasificacion = "Caracter simple";
        }
    }
    
    //Crea Token a partir del lexema de palabra reservada
    public Token(String lexema){
        Lenguaje l = new Lenguaje();
        clasificacion = "Palabra reservada";
        this.lexema = lexema;
        for (int i = 0; i < l.reservadas.length; i++) {
            if(l.reservadas[i].equals(lexema)){
                atributo = 800+i;
                break;
            }                
        }
    }
    
    //Para impresión
    @Override
    public String toString(){
        return lexema+"\t\t"+clasificacion+"\t"+atributo+"\n";
    }
}