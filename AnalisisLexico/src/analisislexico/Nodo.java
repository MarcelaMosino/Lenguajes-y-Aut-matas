
package analisislexico;

class Nodo<T> {
    Nodo<T> siguiente;
    T contenido;
    
    public Nodo(T elemento){
        this.contenido = elemento;
        siguiente = null;
    }
}
