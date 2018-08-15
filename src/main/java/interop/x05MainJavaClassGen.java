package interop;

import interop.protocols_and_records.Person;
import interop.api.*;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class x05MainJavaClassGen {
    public static void main(String[] args) throws IOException {
        //Data
        List<Double> doubles = new LinkedList<>();
        for(int i = 0; i < 10; i++) doubles.add((double) i);

        //Static methods.
        //definterface generated
        Collection<String> cres = Demo.foo().methodC(doubles);
        System.out.println(cres);
        //Java interface
        Collection<String> jres = Demo.bar().methodJ(doubles);
        System.out.println(jres);

        //This class has state - It keeps track of how much to increment incoming collections.
        StatefulDemo demo = new StatefulDemo();
        demo.setInc(10);
        //Class methods don't pass on generic params.
        Collection<String> methodC = demo.methodC(doubles);
        Collection<String> methodJ = demo.methodJ(doubles);
        System.out.println(methodC);
        System.out.println(methodJ);

        //Factory method - I recommend it. No interface loss and implementation is hidden.
        JInterface o = FactoryDemo.getInstance();
        final Collection<String> s = o.methodJ(doubles);
        System.out.println("Factory method: " + s);

        //Yep, it is indeed a Java class - you can even do stream operations on it.
        System.out.println(s.stream().map(String::length).collect(Collectors.toList()));

        //defrecord results in a class being generated. However
        Person mark = new Person("Mark", 21);
        System.out.println(mark);
        //getOlder is from a protocol, so type hinting is lost. Protocols are not meant for Java interop.
        System.out.println(((Person)mark.getOlder()).age);
        //pretty was definterfaced and has typing.
        System.out.println(mark.pretty());
    }
}
