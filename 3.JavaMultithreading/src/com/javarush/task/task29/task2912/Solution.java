package com.javarush.task.task29.task2912;

/* 
Рефакторинг паттерна Chain of Responsibility
*/

//"C:\Program Files\Java\jdk1.8.0_201\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.1.1\lib\idea_rt.jar=53019:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2019.1.1\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_201\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_201\jre\lib\rt.jar;C:\coding\javarushtasks\JavaRushTasks\out\production\3.JavaMultithreading" com.javarush.task.task29.task2912.Solution
//Logging to file: Everything is OK
//Logging to console: We found a bug
//Logging to file: We found a bug
//Send SMS to CEO: Database connection error
//Logging to console: Database connection error
//Logging to file: Database connection error
//Call to director: System shut down
//Send SMS to CEO: System shut down
//Logging to console: System shut down
//Logging to file: System shut down
//Process finished with exit code 0

public class Solution {
    public static void main(String[] args) {
        Logger logger3 = new PhoneLogger(Level.FATAL);
        Logger logger2 = new SmsLogger(Level.ERROR);
        Logger logger1 = new ConsoleLogger(Level.WARN);
        Logger logger0 = new FileLogger(Level.INFO);

        logger3.setNext(logger2);
        logger2.setNext(logger1);
        logger1.setNext(logger0);

        logger3.inform("Everything is OK", Level.INFO);
        logger3.inform("We found a bug", Level.WARN);
        logger3.inform("Database connection error", Level.ERROR);
        logger3.inform("System shut down", Level.FATAL);
    }
}