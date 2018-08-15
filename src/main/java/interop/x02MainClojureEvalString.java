package interop;

import clojure.lang.RT;
import clojure.lang.Var;

import java.util.List;

/**
 * This is probably the most primitive way to call Clojure from Java and the least desirable. Note that the Clojure
 * Runtime (RT) is a complete runtime environment within your Java project.
 */
public class x02MainClojureEvalString {
    public static void main(String[] args) {
        //Get a handle to the runtime's eval function.
        Var eval = RT.var("clojure.core", "eval");

        //Do some evaluation. Note that the type is cast.
        List<Integer> l = (List<Integer>)eval.invoke(RT.readString("(mapv inc (range 10))"));
        System.out.println(l);
        System.out.println(l.get(3));
    }
}
