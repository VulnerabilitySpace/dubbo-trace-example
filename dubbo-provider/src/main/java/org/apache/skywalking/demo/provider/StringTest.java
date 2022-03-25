package org.apache.skywalking.demo.provider;

import org.springframework.boot.logging.log4j2.Log4J2LoggingSystem;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StringTest {
    private static final Pattern pattern = Pattern.compile("((com/secnium)|(java/lang)|(cn/huoxian))/iast/.*");
    private static final String[] targetClasses = new String[]{
            "antlr/CharLiteralElement",
            "antlr/CharQueue",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "antlr/CharLiteralElement",
            "antlr/CharQueue",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "antlr/CharLiteralElement",
            "antlr/CharQueue",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "antlr/CharLiteralElement",
            "antlr/CharQueue",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "antlr/CharLiteralElement",
            "antlr/CharQueue",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "antlr/CharLiteralElement",
            "antlr/CharQueue",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "antlr/CharLiteralElement",
            "antlr/CharQueue",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "antlr/CharLiteralElement",
            "antlr/CharQueue",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/sun/org/apache/xerces/internal/impl/XMLEntityManager$RewindableInputStream",
            "com/secnium/iast/dongtai"
    };


    public static void main(String[] args) {
        // test string match
        // test re match
        StopWatch clock = new StopWatch();
        clock.start();
        for (String targetClass : targetClasses) {
            reMatch(targetClass);
        }
        clock.stop();
        System.out.println("use time: " + clock.getTotalTimeSeconds());

        clock.start();
        for (String targetClass : targetClasses) {
            reMatch(targetClass);
        }
        clock.stop();
        System.out.println("use time: " + clock.getTotalTimeSeconds());
    }

    private static void reMatch(String className) {
        if (pattern.matcher(className).find()) {
            System.out.println("match");
        }
    }

    private static void stringMatch(String className) {
        if (className.startsWith("com/secnium/iast/") || className
                .startsWith("java/lang/iast/") || className.startsWith("cn/huoxian/iast/")) {
            System.out.println("match");
        }
    }
}
