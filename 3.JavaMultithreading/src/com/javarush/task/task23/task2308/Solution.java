package com.javarush.task.task23.task2308;
/*Рефакторинг, вложенные классы
Отрефакторите класс Solution: вынесите все константы в public вложенный(nested) класс Constants.
Запретите наследоваться от Constants.
Требования:
1. В классе Solution должен быть создан класс Constants содержащий строковые константы.
2. Класс Constants должен быть публичным.
3. Класс Constants должен быть объявлен с модификатором, запрещающим наследование от этого класса.
4. В классе Constants должна существовать константа SERVER_IS_CURRENTLY_NOT_ACCESSIBLE со значением "The server is not currently accessible.".
5. В классе Constants должна существовать константа USER_IS_NOT_AUTHORIZED со значением "The user is not authorized.".
6. В классе Constants должна существовать константа USER_IS_BANNED со значением "The user is banned.".
7. В классе Constants должна существовать константа ACCESS_IS_DENIED со значением "Access is denied.".*/
public class Solution {
    public static final class Constants{
        public static final String SERVER_IS_CURRENTLY_NOT_ACCESSIBLE = "The server is not currently accessible.";
        public static final String USER_IS_NOT_AUTHORIZED = "The user is not authorized.";
        public static final String USER_IS_BANNED = "The user is banned.";
        public static final String ACCESS_IS_DENIED = "Access is denied.";
    }
    public class ServerNotAccessibleException extends Exception {
        public ServerNotAccessibleException() {
            super(Solution.Constants.SERVER_IS_CURRENTLY_NOT_ACCESSIBLE);
        }

        public ServerNotAccessibleException(Throwable cause) {
            super(Solution.Constants.SERVER_IS_CURRENTLY_NOT_ACCESSIBLE, cause);
        }
    }

    public class UnauthorizedUserException extends Exception {
        public UnauthorizedUserException() {
            super(Solution.Constants.USER_IS_NOT_AUTHORIZED);
        }

        public UnauthorizedUserException(Throwable cause) {
            super(Solution.Constants.USER_IS_NOT_AUTHORIZED, cause);
        }
    }

    public class BannedUserException extends Exception {
        public BannedUserException() {
            super(Solution.Constants.USER_IS_BANNED);
        }

        public BannedUserException(Throwable cause) {
            super(Solution.Constants.USER_IS_BANNED, cause);
        }
    }

    public class RestrictionException extends Exception {
        public RestrictionException() {
            super(Solution.Constants.ACCESS_IS_DENIED);
        }

        public RestrictionException(Throwable cause) {
            super(Solution.Constants.ACCESS_IS_DENIED, cause);
        }
    }

    public static void main(String[] args) {

    }
}
