package codeacademy.bookingforum.app.user.enums;

import java.util.Arrays;
public enum Gender {
    MALE,
    FEMALE,
    OTHER,
    UNDEFINED;

    private String displayName;
    private int weight;

    private static String[] maleArgs = {"1","M","MALE"};
    private static String[] femaleArgs = {"2","F","FEMALE"};
    private static String[] otherArgs = {"3","GAY","LESBIAN","BI","BISEXUAL","TRANS","TRANSGENDER","UNTRADITIONAL","OTHER","DIFFERENT","NEITHER","BOTH","NEUTRAL","NONBINARY"};
    private static String[] undefinedArgs = {"0",null,"NONE","","NULL","GENDERLESS","PREFER_NOT_TO_SAY","AGENDER"};

    public static Gender parse(String value) {
        value = value.trim().toUpperCase();

        if(Arrays.asList(maleArgs).contains(value)) {
            return MALE;
        } else if (Arrays.asList(femaleArgs).contains(value)) {
            return FEMALE;
        } else if (Arrays.asList(otherArgs).contains(value)) {
            return OTHER;
        } else if (Arrays.asList(undefinedArgs).contains(value)) {
            return UNDEFINED;
        } else {
            return null;
        }
    }

}
