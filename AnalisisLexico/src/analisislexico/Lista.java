
package analisislexico;


public class Lista<T> {
    Nodo<T> primero;
    Nodo<T> ultimo;
    String cabecera;
    
    public Lista(String cabecera){
        //Crea lista con cabecera para impresión
        this.cabecera = cabecera; 
        primero = ultimo = null;
    }
    
    public void add(T elemento){
        Nodo<T> nuevo = new Nodo(elemento);
        if(estaVacia()){
            primero = nuevo;
            ultimo = nuevo;
        }
        else{
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }           
    }
    
    public boolean estaVacia(){
        return primero==null;
    }
    
    //Devuelve el contenido de la lista
    @Override
    public String toString(){
        String cadena = cabecera+"\n";
        Nodo auxiliar = primero;
        while(auxiliar.siguiente!=null){
            cadena += auxiliar.contenido.toString()+"\n";
            auxiliar = auxiliar.siguiente;
        } return cadena+auxiliar.contenido+"\n";
    }
    
}
