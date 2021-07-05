package gramatica;

import java.io.IOException;

public class PruebaGram {
    public static void main(String[] args) throws IOException {
        Gramatica g = new Gramatica("gramatica1.txt");
        g.leeGramatica();
        System.out.println(g);
    }
}
