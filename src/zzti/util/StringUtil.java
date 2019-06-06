package zzti.util;

/**
 * Created by guoli on 17/7/6.
 */
public class StringUtil {
    public static  boolean isEmpty(String str){
        if("".equals(str)||str==null){
            return true;
        }
        return false;
    }
}
