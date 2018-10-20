package com.step.course.util;

public class ValidationUtil {

    public static boolean validate(String... fields){

        for (String s : fields){
            if (s==null || s.trim().isEmpty()){
                return false;
            }
        }

        return true;
    }

}
