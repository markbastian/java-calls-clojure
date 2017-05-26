package interop;

import clojure.lang.*;

import java.io.IOException;
import java.util.stream.Collectors;

public class MainClojureClasses {
    public static void main(String[] args) throws IOException {
        //Use existing Clojure classes - This can actually be fairly useful.
        final PersistentVector v = PersistentVector.create("I", "love", "turtles");
        System.out.println(v);
        System.out.println(v.assocN(1, "hate"));
        System.out.println(v.stream().map(s -> ((String)s).length()).collect(Collectors.toList()));

        Atom a = new Atom(3);
        a.swap(new AFn() {
            @Override
            public Object invoke(Object arg1) {return (Integer)arg1 + 1;}
        });
        System.out.println("The state is: " + a.deref());
    }
}
