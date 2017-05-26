package interop;

import clojure.lang.*;

import java.io.IOException;
import java.util.List;

/**
 * You can put a bunch of Clojure scripts into a resource folder (or just a folder) if you want some dynamic
 * capabilities in your project or if there are functions that Clojure does better it's harmless to write them in
 * Clojure then load them in your project. This works best if the functions are static and even better if you create a
 * bridge method.
 */
public class MainResourceScripts {
    //Prevent script reloads.
    static { //Did you know you could do this?
        try {
            RT.loadResourceScript("interop/scripts.clj");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Var fib = RT.var("interop.scripts", "nth-fib");

    //Bridge function - looks just like Java.
    public static BigInt nthFib(long n){
        return (BigInt) fib.invoke(n);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Using invoke");
        for(int i = 0; i <= 10; i++)
            System.out.println("fib(" + i + ") = " + fib.invoke(i));

        System.out.println("Using bridge function");
        for(int i = 0; i <= 10; i++)
            System.out.println("fib(" + i + ") = " + nthFib(i));

        //Invoke directly from a string - you'd probably never do this.
        Var eval = RT.var("clojure.core", "eval");
        System.out.println(eval.invoke(RT.readString("(mapv inc (range 10))")));
        List<Integer> l = (List<Integer>)eval.invoke(RT.readString("(mapv inc (range 10))"));
        System.out.println(l.get(3));
    }
}
