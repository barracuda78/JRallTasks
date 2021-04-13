package com.javarush.task.task24.task2412;

import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Знания - сила!
*/
public class Solution {
    public static void main(String[] args) {
        List<Stock> stocks = getStocks();
        sort(stocks);
        Date actualDate = new Date();
        printStocks(stocks, actualDate);
    }

    public static void printStocks(List<Stock> stocks, Date actualDate) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        double[] filelimits = {0d, actualDate.getTime()};
        String[] filepart = {"change {4}", "open {2} and last {3}"};

        ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart);
        //Format[] testFormats = {null, dateFormat, fileform};
        Format[] testFormats = {null, null, dateFormat, fileform, null, null, null};
        MessageFormat pattform = new MessageFormat("{0}   {1} | {5} {6}");
        pattform.setFormats(testFormats);

        for (Stock stock : stocks) {
            String name = ((String) stock.get("name"));
            String symbol = (String) stock.get("symbol");
            double open = !stock.containsKey("open") ? 0 : ((double) stock.get("open"));
            double last = !stock.containsKey("last") ? 0 : ((double) stock.get("last"));
            double change = !stock.containsKey("change") ? 0 : ((double) stock.get("change"));
            Date date = (Date) stock.get("date");
            Object[] testArgs = {name, symbol, open, last, change, date, date.getTime()};
            System.out.println(pattform.format(testArgs));
        }
    }

    public static void sort(List<Stock> list) {
//1. В методе sort написать компаратор для Stock:
//1.1. Первичная сортировка по name в алфавитном порядке
//1.2. Вторичная сортировка по дате без учета часов, минут, секунд (сверху самые новые), потом по прибыли от положительных к отрицательным
//... open 125,64 and last 126,74 - тут прибыль = 126,74-125,64
//... open 125,64 and last 123,43 - тут прибыль = 123,43-125,64
        list.sort(new Comparator<Stock>() {
            public int compare(Stock stock1, Stock stock2) {
                //задать лексикографический порядок сравнения строк:
                String name1 = (String)stock1.get("name");
                String name2 = (String)stock2.get("name");
                //в a1 будет отрицательное число, если имя первого стока лексикографически "меньше" имени второго стока.
                //в a1 будет положительное число, если имя первого стока лексикографически "больше" имени второго стока.
                //в a1 будет ноль, если имена стоков лексикографически равны.
                //это реализуется с помощью метода compareTo():
                //int a1 = name1.compareTo(name2);
                int a1 = ((String)stock1.get("name")).compareTo((String)stock2.get("name"));

                Date d1 = (Date)stock1.get("date");
                Date d2 = (Date)stock2.get("date");
                //убираем часы, минуты и секунды из наших дат. Создаем объект Calendar, устанавливаем для него нулевые значения часов, минут, секунд, минисекунд, берем из него нужную нам дату.
                Calendar cal = Calendar.getInstance();
                cal.setTime(d1);
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                d1 =  cal.getTime();

                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(d2);
                cal1.set(Calendar.HOUR_OF_DAY, 0);
                cal1.set(Calendar.MINUTE, 0);
                cal1.set(Calendar.SECOND, 0);
                cal1.set(Calendar.MILLISECOND, 0);
                d2 =  cal1.getTime();
                //Эта переменная d принимает значения -1, 1 или 0 в зависимости от того, какая из двух сравниваемых дат больше.
                int d = d1.compareTo(d2);



                if(a1 < 0){
                    return -1;
                } else if( a1 > 0){
                    return 1;
                }
                //здесь написать логику сравнения по дате без учета часов, минут, секунд (сверху самые новые):

                else{
                    if(d < 0){
                        return 1;
                    }
                    else if(d > 0){
                        return -1;
                    }
                    //здесь написать логику сравнения по прибыли от положительных к отрицательным:
                    //... open 125,64 and last 126,74 - тут прибыль = 126,74-125,64
                    //... open 125,64 and last 123,43 - тут прибыль = 123,43-125,64
                    else {
                        //ЗДЕСЬ ПРОВЕРИТЬ НА NULL stock1.get("open") и stock1.get("last") и stock2.get("open") и stock2.get("last")
                        //если у stock1 есть open и last, а у stock2 их нет, то stock1 больше.
                        //если у stock2 есть open и last, а у stock1 их нет, то stock2 больше.
                        //если у обоих стоков их нет - они равны.
                        if(stock1.containsKey("open") && !stock2.containsKey("open")){
                            return 1;
                        }
                        if(!stock1.containsKey("open") && stock2.containsKey("open")){
                            return -1;
                        }
                        if(!stock1.containsKey("open") && !stock2.containsKey("open")){
                            return 0;
                        }

                        double open1 = (double)stock1.get("open");
                        double last1 = (double)stock1.get("last");
                        double income1 = open1 - last1;
                        double open2 = (double)stock2.get("open");
                        double last2 = (double)stock2.get("last");
                        double income2 = open2 - last2;

                        if (income1 > income2) {
                            return 1;
                        } else if (income1 < income2) {
                            return -1;
                        }
                        else return 0;
                    }
                }
            }
        });
    }

    public static class Stock extends HashMap<String, Object> {
        public Stock(String name, String symbol, double open, double last) {
            put("name", name);
            put("symbol", symbol);
            put("open", open);
            put("last", last);
            put("date", getRandomDate(2020));
        }

        public Stock(String name, String symbol, double change, Date date) {
            put("name", name);
            put("symbol", symbol);
            put("date", date);
            put("change", change);
        }

    }

    public static List<Stock> getStocks() {
        List<Stock> stocks = new ArrayList<>();

        stocks.add(new Stock("Fake Apple Inc.", "AAPL", 125.64, 123.43));
        stocks.add(new Stock("Fake Cisco Systems, Inc.", "CSCO", 25.84, 26.3));
        stocks.add(new Stock("Fake Google Inc.", "GOOG", 516.2, 512.6));
        stocks.add(new Stock("Fake Intel Corporation", "INTC", 21.36, 21.53));
        stocks.add(new Stock("Fake Level 3 Communications, Inc.", "LVLT", 5.55, 5.54));
        stocks.add(new Stock("Fake Microsoft Corporation", "MSFT", 29.56, 29.72));

        stocks.add(new Stock("Fake Nokia Corporation (ADR)", "NOK", .1, getRandomDate()));
        stocks.add(new Stock("Fake Oracle Corporation", "ORCL", .15, getRandomDate()));
        stocks.add(new Stock("Fake Starbucks Corporation", "SBUX", .03, getRandomDate()));
        stocks.add(new Stock("Fake Yahoo! Inc.", "YHOO", .32, getRandomDate()));
        stocks.add(new Stock("Fake Applied Materials, Inc.", "AMAT", .26, getRandomDate()));
        stocks.add(new Stock("Fake Comcast Corporation", "CMCSA", .5, getRandomDate()));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", -.03, getRandomDate()));



        return stocks;
    }

    public static Date getRandomDate() {
        return getRandomDate(1970);
    }

    public static Date getRandomDate(int startYear) {
        int year = startYear + (int) (Math.random() * 30);   //from 0 to 29 ( greater than or equal to 0.0 and less than 1.0)
        int month = (int) (Math.random() * 12);            //from 0 to 11 //Month value is 0-based. e.g., 0 for January.
        int day = (int) (Math.random() * 28);               //from 0 to 27
        GregorianCalendar calendar = new GregorianCalendar(year, month, day); //the default locale.
        return calendar.getTime();
    }
}

