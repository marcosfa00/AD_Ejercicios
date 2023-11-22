
package primitiveutfchars;

import java.io.IOException;

/**
 *
 * @author marcosfa
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Methods.primitiveUTFcharsWrite("text6.dat");
        Methods.readAll("text6.dat");
    }

}
