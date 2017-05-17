package interop;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args){
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
    }
}
