package com.epam.mjc;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        ArrayList<String> firstPart = new ArrayList<>();
        ArrayList<MethodSignature.Argument> secondPart = new ArrayList<>();
        System.out.println("Signature String = " + signatureString);
        StringTokenizer signatureToken = new StringTokenizer(signatureString, "()");
        int a = 1;
        while (signatureToken.hasMoreElements()) {
            if (a > 1) {
                StringTokenizer args = new StringTokenizer(signatureToken.nextToken(), ",");
                while (args.hasMoreElements()) {
                    StringTokenizer tokenizer = new StringTokenizer(args.nextToken(), " ");
                    while (tokenizer.hasMoreElements()) {
                        secondPart.add(new MethodSignature.Argument(tokenizer.nextToken(), tokenizer.nextToken()));
                    }
                }
                a++;
            }
            if (a == 1) {
                StringTokenizer untilArgs = new StringTokenizer(signatureToken.nextToken());
                while (untilArgs.hasMoreElements()) {
                    firstPart.add(untilArgs.nextToken());
                }
                a++;
            }

        }
        MethodSignature methodSignature;
        if (firstPart.size() == 3) {
            methodSignature = secondPart.size() > 0 ? new MethodSignature(firstPart.get(2), secondPart)
                    : new MethodSignature(firstPart.get(2));
            methodSignature.setAccessModifier(firstPart.get(0));
            methodSignature.setReturnType(firstPart.get(1));

        } else {
            methodSignature = secondPart.size() > 0 ? new MethodSignature(firstPart.get(1), secondPart)
                    : new MethodSignature(firstPart.get(1));
            methodSignature.setReturnType(firstPart.get(0));
        }

        return methodSignature;

    }
}
