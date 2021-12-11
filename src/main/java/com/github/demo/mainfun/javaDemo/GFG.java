package com.github.demo.mainfun.javaDemo;

import java.lang.reflect.Modifier;

public class GFG {
    public static void main(String[] args)
            throws NoSuchFieldException,
            SecurityException {

        // printing the Modifiers name
        // between integer value 10 to 20

        for (int i = 10; i < 20; i++) {

            // apply toString() methods
            System.out.println(
                    "i: "
                            + i + " Modifier:"
                            + Modifier.toString(i));
        }
        System.out.println(Modifier.toString(Modifier.methodModifiers()));
    }
}

// output

// i: 10 Modifier:private static
// i: 11 Modifier:public private static
// i: 12 Modifier:protected static
// i: 13 Modifier:public protected static
// i: 14 Modifier:protected private static
// i: 15 Modifier:public protected private static
// i: 16 Modifier:final
// i: 17 Modifier:public final
// i: 18 Modifier:private final
// i: 19 Modifier:public private final
