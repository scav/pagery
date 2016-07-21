package com.devbugger.pagery.transform;

public class TransformUtils {

    //kan denne brukes TransformPageryPostPage slik den brukes på TransformFontMatter?

    int indexSecondOccurance(String marker, String input) {
        int index = input.indexOf(marker);
        int counter = 0;
        while (index >= 0) {
            counter++;
            if(counter == 2) {
                break;
            }
            index = input.indexOf(marker, index +1);
        }

        return index;
    }
}
