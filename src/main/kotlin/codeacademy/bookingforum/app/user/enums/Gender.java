package codeacademy.bookingforum.app.user.enums;

import java.util.Arrays;
public enum Gender {
    MALE,
    FEMALE,
    OTHER,
    UNDEFINED;

    private static final String[] maleArgs = {"1","M","MALE"};
    private static final String[] femaleArgs = {"2","F","FEMALE"};
    private static final String[] otherArgs = {"3","GAY","LESBIAN","BI","BISEXUAL","TRANS","TRANSGENDER","UNTRADITIONAL","OTHER","DIFFERENT","NEITHER","BOTH","NEUTRAL","NONBINARY","AGENDER"};
    private static final String[] undefinedArgs = {"0","NONE","","NULL","GENDERLESS","PREFER NOT TO SAY"};

    public static Gender parse(String value) {
        if(value != null) {
            value = value.trim().toUpperCase();
        }

        if(Arrays.asList(maleArgs).contains(value)) {
            return MALE;
        } else if (Arrays.asList(femaleArgs).contains(value)) {
            return FEMALE;
        } else if (Arrays.asList(otherArgs).contains(value)) {
            return OTHER;
        } else if (Arrays.asList(undefinedArgs).contains(value)) {
            return UNDEFINED;
        } else {
            return UNDEFINED;
        }
    }

}
