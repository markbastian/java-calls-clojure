package interop;

import clojure.lang.*;

import java.io.IOException;

public class MainClojureClasses {
    public static void main(String[] args) throws IOException {
        //Use existing Clojure classes - This can actually be fairly useful.
        final IPersistentVector v = PersistentVector.create("I", "love", "turtles");
        System.out.println(v);
        System.out.println(v.assocN(1, "hate"));

        Atom a = new Atom(3);
        a.swap(new AFn() {
            @Override
            public Object invoke(Object arg1) {return (Integer)arg1 + 1;}
        });
        System.out.println("The state is: " + a.deref());
    }
}
