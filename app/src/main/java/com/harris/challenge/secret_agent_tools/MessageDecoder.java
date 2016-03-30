/*------------------------------------------------------------------------------
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *------------------------------------------------------------------------------
 */

package com.harris.challenge.secret_agent_tools;


import java.util.Arrays;

/**
 * Class to hold and interpret data returned from the MasterServer.
 */
public class MessageDecoder {

    /**
     * Variable for holding the last encoded message return from
     * the MasterServer; Publicly accessible to other activities
     */
    static String encodedMessage = "---";

    /**
     * Variable for holding the decoded result of the last message return
     * from the MasterServer; Publicly accessible to other activities
     */
    static String decodedMessage = "---";

    /**
     * Function for getting a decoded message given an encoded message.
     * Each team must implement this function.
     *
     * Example:
     * See the specification document of this year's competition
     */


    public static String decodeResponse(String encodedString)
    {
        MessageDecoder.encodedMessage = encodedString;

        /*
         * Put code here to decode the encodedString and set result.
         * Also be sure to set decodedMessage to the result.
         */
        String srcString = spaceToDash(encodedString);
        String unqString = uniqueChars(srcString);
        String srtString = sortChars(unqString);
        String rvrsString = reverse(srtString);
        String mapString = mapped(encodedString, srtString, rvrsString);






        String result = mapString.replace("_"," ");
        MessageDecoder.decodedMessage = result;
        return result;
    }

    public static String spaceToDash(String str) {
        // simply makes all spaces into underscores
        String underSc = str.replace(" ","_");
        return underSc;
    }

    public static String uniqueChars(String str) {
        boolean[] seen = new boolean[256];
        StringBuilder sb = new StringBuilder(seen.length);

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!seen[ch]) {
                seen[ch] = true;
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    public static String sortChars(String str) {
        char k;
        int a;
        int o;
        String newWord;
        int[] testArray = new int[str.length()]; // Makes empty boxes in arrays
        newWord = "";
        for (a = 0; a < str.length(); a++) {
            k = str.charAt(a); // Chooses a character at a position
            // in the string and assigns it to k
            o = (int)k; // Makes the character k into the integer
            // that represents the letter in ASCII
            testArray[a] = o; // Places the integer of the letter letter at the
            // position in the array relative to the string
        }
        a = 0;
        for (int w = 0; w < str.length(); w++) { // Steps through each statement
            // until the string finishes
            Arrays.sort(testArray); // Sorts array into ASCII order
            k = (char)testArray[a]; // Converts the IntArray to a char and assigns to k
            newWord = newWord + k; // Adds whatever is in k to the string newWord
            a = a + 1; // Goes to the next array box
        }


        return newWord;
    }

    public static String reverse(String str) {
        // Uses StringBuilder on str to reverse then convert it to a String
        String revStr = new StringBuilder(str).reverse().toString();
        return revStr;
    }

    public static String mapped(String x, String key, String map){
        String[] testArray = new String[x.length()];
        int wordCount = x.length();
        if (wordCount == 0){
            return x;
        }
        wordCount = 1;
        int b = 0;
        String decodedWord = "";
        for (int i = 0; i < x.length(); i++){
            char a = x.charAt(b);
            int location = key.indexOf(a);
            if(location != x.length() -1){
                a = map.charAt(location);
                String newWord1 = Character.toString(a);
                testArray[wordCount - 1] = newWord1;
                b = b + 1;
                decodedWord = decodedWord + testArray[wordCount - 1 ];
                wordCount = wordCount + 1;
            }
        }
        return decodedWord;
    }


}
