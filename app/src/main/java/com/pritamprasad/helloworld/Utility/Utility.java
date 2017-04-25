package com.pritamprasad.helloworld.Utility;

import java.util.Date;

/**
 * Created by jarvis on 2/8/17.
 */

public class Utility {

    /**
     * Converts String to Date
     * @return
     */
    public static Date StringToDate(String date){
        return null;
    }

    public static Integer goalStateEnumToInt(LocalConstants.GOAL_STATES state) {

        int stateNum;
         switch (state){
            case NEW :
                stateNum = 1;
                break;
             case ACTIVE:
                 stateNum = 2;
                 break;
             case ON_HOLD:
                 stateNum = 3;
                 break;
             case DONE:
                 stateNum = 4;
                 break;
             default:
                 stateNum = LocalConstants.INVALID_INTEGER_VALUE;
                 break;
        }
        return stateNum;
    }

    public static Integer quadrantStateEnumToInt(LocalConstants.QUADRANT_STATES quadrant_state) {
        int quadstate;
        switch (quadrant_state){
            case ONE:
                quadstate = 1;
                break;
            case TWO:
                quadstate = 2;
                break;
            case THREE:
                quadstate = 3;
                break;
            case FOUR:
                quadstate = 4;
                break;
            default:
                quadstate = LocalConstants.INVALID_INTEGER_VALUE;
                break;
        }
        return quadstate;
    }
}
