package interop;

import clojure.lang.*;
import interop.protocols_and_records.Person;
import interop.api.*;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        //Data
        List<Double> doubles = new LinkedList<>();
        for(int i = 0; i < 10; i++) doubles.add((double) i);

        //Static methods.
        //definterface generated
        Collection<String> cres = Demo.foo().methodC(doubles);
        //Java interface
        Collection<String> jres = Demo.bar().methodJ(doubles);

        System.out.println(cres);
        System.out.println(jres);

        StatefulDemo demo = new StatefulDemo();
        demo.setInc(10);
        //Class methods don't pass on generic params.
        Collection<String> methodC = demo.methodC(doubles);
        Collection<String> methodJ = demo.methodJ(doubles);
        System.out.println(methodC);
        System.out.println(methodJ);

        JInterface o = FactoryDemo.getInstance();
        final Collection<String> s = o.methodJ(doubles);
        System.out.println("Factory method: " + s);

        System.out.println(s.stream().map(String::length).collect(Collectors.toList()));

        Person mark = new Person("Mark", 21);
        System.out.println(mark);
        System.out.println(((Person)mark.getOlder()).age);
        System.out.println(mark.pretty());

        //Use existing Clojure classes - This can actually be fairly useful.
        final IPersistentVector v = PersistentVector.create("I", "love", "turtles");
        Atom a = new Atom(3);
        a.swap(new AFn() {
            @Override
            public Object invoke(Object arg1) {return (Integer)arg1 + 1;}
        });
        System.out.println("The state is: " + a.deref());

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
