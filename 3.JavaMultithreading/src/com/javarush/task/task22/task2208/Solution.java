package com.javarush.task.task22.task2208;

import java.util.LinkedHashMap;
import java.util.Map;

/* Формируем WHERE^ Сформируй часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.

Пример:
{name=Ivanov, country=Ukraine, city=Kiev, age=null}

Результат:
name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'

Требования:
1. Метод getQuery должен принимать один параметр типа Map.
2. Метод getQuery должен иметь тип возвращаемого значения String.
3. Метод getQuery должен быть статическим.
4. Метод getQuery должен возвращать строку сформированную по правилам описанным в условии задачи.*/

public class Solution {
    public static void main(String[] args) {
//----------TEST-------------
//        Map<String, String> map = new HashMap<>();
//        map.put("name", "Ivanov");
//        map.put("country", "Ukraine");
//        map.put("city", "Kiev");
//        map.put("age", "18");
//        map.put(null, null);
//        map.put(null, null);
//        map.put(null, null);
//        map.put(null, null);
//        map.put(null, null);
//        map.put(null, null);
//
//        System.out.println(getQuery(map));
//-------END OF TEST---------
    }

    //Убедись, что метод getQuery возвращает пустую строку, если во входящем Map все значения null.
    public static String getQuery(Map<String, String> params) {
        int nullCounter = 0;
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, String> pair : params.entrySet()){
            String key = pair.getKey();
            String value = pair.getValue();

            if(value == null) nullCounter++;

            if(value != null) {
                sb.append(key);
                sb.append(" = '");
                sb.append(value);
                sb.append("' and ");
            }

        }
        if(nullCounter == params.size()) return "";

        sb.delete(sb.length()-5, sb.length()-1);

        return sb.toString().trim();
    }
}
