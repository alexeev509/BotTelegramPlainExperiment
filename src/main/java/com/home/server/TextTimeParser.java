package com.home.server;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextTimeParser {
    private Pattern pattern;
    private Matcher matcher;

    private static final String HOURS_PATTERN="(часа|часов|час)";

    private static final String MINUTES_PATTERN="(минут|мин)";

    private static final String SECONDS_PATTERN="(секунд|сек)";
    HashMap<String,Integer> mapOfTime=new HashMap<String, Integer>();

    {
        mapOfTime.put("один",1);
        mapOfTime.put("два",2);
        mapOfTime.put("три",3);
        mapOfTime.put("четыре",4);
        mapOfTime.put("пять",5);
        mapOfTime.put("шесть",6);
        mapOfTime.put("семь",7);
        mapOfTime.put("восемь",8);
        mapOfTime.put("девять",9);
        mapOfTime.put("десять",10);

        mapOfTime.put("одиннадцать",11);
        mapOfTime.put("двеннадцать",12);
        mapOfTime.put("триннадцать",13);
        mapOfTime.put("четырнадцать",14);
        mapOfTime.put("пятнадцать",15);
        mapOfTime.put("шестнадцать",16);
        mapOfTime.put("семнадцать",17);
        mapOfTime.put("восемнадцать",18);
        mapOfTime.put("девятнадцать",19);
        mapOfTime.put("двадцать",20);
        mapOfTime.put("тридцать",30);
        mapOfTime.put("сорок",40);
        mapOfTime.put("пятьдесят",50);
        mapOfTime.put("шестьдесят",60);

    }

    public int parseText(String str){
        int indexOfHoursEnd=-1, indexOfMinutesEnd=-1,indexOfSecondsEnd=-1;

        pattern=Pattern.compile(HOURS_PATTERN);
         matcher = pattern.matcher(str);
        if(matcher.find()) {
            indexOfHoursEnd = matcher.end();
            System.out.println(matcher.end());
        }


        pattern=Pattern.compile(MINUTES_PATTERN);
        matcher = pattern.matcher(str);
        if(matcher.find()) {
            indexOfMinutesEnd = matcher.end();
            System.out.println(matcher.end());
        }

        pattern=Pattern.compile(SECONDS_PATTERN);
        matcher = pattern.matcher(str);
        if(matcher.find()) {
            indexOfSecondsEnd = matcher.end();
            System.out.println(matcher.end());
        }
        int time=0;
        //If we have only hours
        if(indexOfHoursEnd!=-1 && indexOfMinutesEnd==-1 && indexOfSecondsEnd==-1){
             String[] massOfWords=str.split(" ");
            for (int i = 0; i < massOfWords.length; i++) {
                if(mapOfTime.containsKey(massOfWords[i]))
                    time+=mapOfTime.get(massOfWords[i]);
            }
            System.out.println(time);
            time=time*60*60;
        }

         //If we have only minutes
        else if(indexOfHoursEnd==-1 && indexOfMinutesEnd!=-1 && indexOfSecondsEnd==-1){
            String[] massOfWords=str.split(" ");
            for (int i = 0; i < massOfWords.length; i++) {
                if(mapOfTime.containsKey(massOfWords[i]))
                    time+=mapOfTime.get(massOfWords[i]);
            }
            System.out.println(time);
            time=time*60;
        }

        //If we have only seconds
        else if(indexOfHoursEnd==-1 && indexOfMinutesEnd==-1 && indexOfSecondsEnd!=-1){
            String[] massOfWords=str.split(" ");
            for (int i = 0; i < massOfWords.length; i++) {
                if(mapOfTime.containsKey(massOfWords[i]))
                    time+=mapOfTime.get(massOfWords[i]);
            }
            System.out.println(time);
            time=time;
        }
        //If we have every
//        else if(indexOfHoursEnd!=-1 && indexOfMinutesEnd!=-1 && indexOfSecondsEnd!=-1){
//            String[] massOfWords=str.split(" ");
//            for (int i = 0; i < massOfWords.length; i++) {
//                if(mapOfTime.containsKey(massOfWords[i]))
//                    time+=mapOfTime.get(massOfWords[i]);
//            }
//            System.out.println(time);
//            time=time;
//        }


        return time;
    }

}
