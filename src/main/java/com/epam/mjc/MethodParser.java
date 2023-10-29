package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */

    private static String[] accessModifiers = new String[]{"private", "public", "protected"};
    private static boolean checkElement(String[] arr, String element){
        for (String check: arr) {
            if(check.equals(element)) {
                return true;
            }
        }
        return false;
    }
    private static String[] tokenizingString(String elements){
        if(elements.isEmpty()){
            return null;
        }
        if(elements.indexOf(",") != -1){
            return elements.split(", ");
        }
        return elements.split(" ");
    }

    public static MethodSignature parseFunction(String signatureString) {

        // Spliting signatures into 2 parts: method signatures and arguments
        String[] firstPart = tokenizingString(signatureString.substring(0, signatureString.indexOf("(")));
        String[] arg = tokenizingString(signatureString.substring(signatureString.indexOf("(") + 1, signatureString.indexOf(")")));

        // Creating list of Arguments(Type, name)
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        if(arg != null) {
            for (String element : arg) {
                arguments.add(new MethodSignature.Argument(element.split(" ")[0], element.split(" ")[1]));
            }
        }
        // Initializing the class instance;
        MethodSignature parser = new MethodSignature(firstPart[firstPart.length - 1], arguments);

        // Setting Instance's Modifier and ReturnType
        for (int i = 0; i < firstPart.length-1; i++) {
            if(checkElement(accessModifiers, firstPart[i])){
                parser.setAccessModifier(firstPart[i]);
            } else {
                parser.setReturnType(firstPart[i]);
            }
        }

        //returning parser
        return parser;
    }
}
