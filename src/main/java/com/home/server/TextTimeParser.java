package com.home.server;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextTimeParser {
    private Pattern pattern;
    private Matcher matcher;

    private static final String HOURS_PATTERN="(часа|часов|час)";

    private static final String MINUTES_PATTERN="(минут|мин)";

    private static final String SECONDS_PATTERN="(секунд|сек)";

    private Pattern patternHours=Pattern.compile(HOURS_PATTERN);
    private Pattern patternMinutes=Pattern.compile(MINUTES_PATTERN);
    private Pattern patternSeconds=Pattern.compile(SECONDS_PATTERN);

    HashMap<String,Integer> mapOfTime=new HashMap<String, Integer>();

    {
        mapOfTime.put("один",1);
        mapOfTime.put("одна",1);
        mapOfTime.put("два",2);
        mapOfTime.put("две",2);
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

    public int parseString(String str){
        String[] massofWords = str.trim().toLowerCase().split(" ");
        int time=0;
        int i=0;
        List<Integer> listOfTimeAndIndex;

        for(int j=0;j<3;j++) {
            listOfTimeAndIndex = getTime(massofWords, i);
            time += listOfTimeAndIndex.get(0);
            i = listOfTimeAndIndex.get(1);
        }
        System.out.println(time);
        return time;
    }

    private List<Integer> getTime(String[] massofWords, int i){
        int time=0;
        for (; i < massofWords.length; i++) {
            matcher = patternHours.matcher(massofWords[i]);
            if(matcher.find()) {
                time = time * 60*60;
                break;
            }

            matcher = patternMinutes.matcher(massofWords[i]);
            if(matcher.find()) {
                time = time * 60;
                break;
            }

            matcher = patternSeconds.matcher(massofWords[i]);
            if(matcher.find())
                break;


            if(mapOfTime.containsKey(massofWords[i]))
                time+=mapOfTime.get(massofWords[i]);
            else{
                try {
                    time+=Integer.parseInt(massofWords[i].trim());
                }catch (Exception e){

                }
            }
        }
        return Arrays.asList(time,++i);
    }

}
