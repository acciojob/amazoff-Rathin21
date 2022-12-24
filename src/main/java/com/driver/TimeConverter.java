package com.driver;

public class TimeConverter {
    
    public static int convertTimeStringtoInt(String time){

        int HH = getTime(time.charAt(0),time.charAt(1));
        int MM = getTime(time.charAt(3),time.charAt(4));

        return HH*60 + MM;
    }

    static int getTime(char first,char second){

        int time = (first - '0')*10;
        time += (second - '0');
        return time;
    }

    public static String convertTimeInttoString(int time){

        int MM=time%60;
        int HH=time/60;

        String strHH =""+HH;
        String strMM =""+MM;
        if(HH<10){
            strHH = "0" + HH;
        }
        if(MM<10){
            strMM="0" + MM;
        }
        return strHH + ":" + strMM;
    }
}
