package com.ruoyi.web.controller.tool;


/**
 * @author dfm
 * @create 2021-04-12 20:10
 */
public class StrUtils {
    public static String stringArrayToString(String[] strings){
        String result = "";
        for (int i=0;i<strings.length;i++){
            result+=strings[i];
            if(i<strings.length-1)result+=",";
        }
        return result;
    }


    public static String[] stringToStringArray(String str){
        return str.split(",");
    }

//    public static void main(String[] args) {
//
//
//        String[] strings = new String[]{"https:baidu.com","www.dfm.cn","d:upload"};
//        String s = stringArrayToString(strings);
//        System.out.println(s);
//
//
//        String  str = "https:baidu.com,www.baidu.com,d://ruoyi/upload";
//        String[] split = str.split(",");
//
//
//        String[] strings1 = stringToStringArray(str);
//        for (String t:strings1
//             ) {
//            System.out.println(t);
//
//      }
}
