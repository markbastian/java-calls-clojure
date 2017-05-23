package interop;

import clojure.lang.*;

import java.io.IOException;
import java.util.List;

public class MainScripts {
    public static void main(String[] args) throws IOException {
        //Load resource files from a script. If you do this, you might want a proxy/bridge class.
        RT.loadResourceScript("interop/scripts.clj");
        Var fib = RT.var("interop.scripts", "nth-fib");
        for(int i = 0; i <= 10; i++)
            System.out.println("fib(" + i + ") = " + fib.invoke(i));

        //Invoke directly from a string - you'd probably never do this.
        Var eval = RT.var("clojure.core", "eval");
        System.out.println(eval.invoke(RT.readString("(mapv inc (range 10))")));
        List<Integer> l = (List<Integer>)eval.invoke(RT.readString("(mapv inc (range 10))"));
        System.out.println(l.get(3));
    }
}
