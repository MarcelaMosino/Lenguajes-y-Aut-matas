
package analisislexico;

import java.util.Iterator;
import pila.Nodo;


public class Lista<T>{
    public Nodo<T> primero;
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
    
    public void addI(T elemento){
        Nodo<T> nuevo = new Nodo(elemento);
        if(!isEmpty())            
            nuevo.siguiente = primero;
        else
            ultimo = nuevo;
        primero = nuevo ;
    }
    
    public boolean estaVacia(){
        return primero==null;
    }
    
    public void vaciar(){
        primero=ultimo=null;
    }
    
    public boolean isEmpty(){
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
