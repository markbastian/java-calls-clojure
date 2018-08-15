package interop;

import interop.staticfunctions.Math;

public class x04MainJavaStaticMethods {
    public static void main(String[] args) {
        //Static functions are a very easy way to interact with Clojure code.
        System.out.println(Math.nthFib(100));

        //For another good example, see
        // https://stackoverflow.com/questions/2181774/calling-clojure-from-java
    }
}
