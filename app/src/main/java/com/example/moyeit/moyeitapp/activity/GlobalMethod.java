package com.example.moyeit.moyeitapp.activity;

/**
 * Created by ga0 on 2017-07-27.
 */

public class GlobalMethod {
    public static String toRegion(int i){
        String region ="";
        switch(i){
            case 1: region = "강원";
                break;
            case 2: region = "경기";
                break;
            case 3: region = "경남";
                break;
            case 4: region = "경북";
                break;
            case 5: region = "광주";
                break;
            case 6: region = "대구";
                break;
            case 7: region = "대전";
                break;
            case 8: region = "부산";
                break;
            case 9: region = "서울";
                break;
            case 10: region = "세종";
                break;
            case 11: region = "울산";
                break;
            case 12: region = "인천";
                break;
            case 13: region = "전남";
                break;
            case 14: region = "전북";
                break;
            case 15: region = "제주";
                break;
            case 16: region = "충남";
                break;
            case 17: region = "충북";
                break;
        }
        return region;
    }
    public int toIntRegion(String str){
        int region=0;
        if(str.equals("서울")){
            region=1;
        }else if(str.equals("강원")){
            region=2;
        }else if(str.equals("경기")){
            region=3;
        }else if(str.equals("경남")){
            region=4;
        }else if(str.equals("경북")){
            region=5;
        }else if(str.equals("광주")){
            region=6;
        }else if(str.equals("대구")){
            region=7;
        }else if(str.equals("대전")){
            region=8;
        }else if(str.equals("부산")){
            region=9;
        }else if(str.equals("세종")){
            region=10;
        }else if(str.equals("울산")){
            region=11;
        }else if(str.equals("인천")){
            region=12;
        }else if(str.equals("전남")){
            region=13;
        }else if(str.equals("전북")){
            region=14;
        }else if(str.equals("제주")){
            region=15;
        }else if(str.equals("충남")){
            region=16;
        }else if(str.equals("충북")){
            region=17;
        }
        return region;
    }
}
