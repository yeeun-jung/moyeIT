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
}
