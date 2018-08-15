package interop;

import clojure.lang.*;

import java.io.IOException;
import java.util.stream.Collectors;

public class x01MainClojureClasses {
    public static void main(String[] args) throws IOException {
        //Use existing Clojure classes - This can actually be fairly useful.
        // For example, java.util.Collections are mutable. Other immutable collection libraries aren't persistent and
        // don't have methods for destructive updates.
        final PersistentVector v = PersistentVector.create("I", "love", "turtles");
        System.out.println(v);
        System.out.println(v.assocN(1, "hate"));
        //One issue to be aware of is that Clojure doesn't care about generics so will show unchecked type warnings.
        //Be aware that generics erase all types anyways.
        System.out.println(v.stream().map(s -> ((String)s).length()).collect(Collectors.toList()));

        //If you want to use Clojure atoms in Java, go ahead.... It's a great way to manage application state.
        Atom a = new Atom(3);
        a.swap(new AFn() {
            @Override
            public Object invoke(Object arg1) {return (Integer)arg1 + 1;}
        });
        System.out.println("The state is: " + a.deref());
    }
}
