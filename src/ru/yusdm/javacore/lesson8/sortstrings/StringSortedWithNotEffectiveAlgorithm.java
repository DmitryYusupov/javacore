package ru.yusdm.javacore.lesson8.sortstrings;

import java.util.*;

/**
 * ATTENTION! Dont sort strings just coping/pasting this function!!
 * <p>
 * It is not effective, just to demo Collections/Map in action
 * and how they can help us in different tasks.
 * <p>
 * P/S
 * Nether the less you can use such way of sorting but you should
 * see effective methods to decode string as number. See hash/encode algorithms.
 */
public class StringSortedWithNotEffectiveAlgorithm implements StringSorter {

    private static Map<Character, Integer> charAsIntMap = new HashMap<>();
    private static int charAsInt = 10;

    static {
        String upperCase = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";
        String lowerCase = upperCase.toLowerCase();
        fillCharAsIntMap(lowerCase);
        fillCharAsIntMap(upperCase);
    }

    private static void fillCharAsIntMap(String inputAlphabet) {
        String revertedStr = new StringBuilder(inputAlphabet).reverse().toString();
        for (char letter : revertedStr.toCharArray()) {
            charAsIntMap.put(letter, charAsInt);
            charAsInt = charAsInt + 1;
        }
    }

    @Override
    public List<String> getSorted(List<String> toSort) {
        //prepare map where key is Numeric view of incoming string
        //map['1023456', 'some string']
        //map['11223', 'Other string']
        Map<String, String> numericStrOriginStrMap = new HashMap<>();
        for (String string : toSort) {
            numericStrOriginStrMap.put(encodeStringAsNumeric(string), string);
        }

        //we should make length of all strings equals, we will add zeros to the end
        //map['1023456', 'some string']
        //map['1122300', 'Other string']
        Map<String, String> numericStrWithEqualsLengthOriginStrMap = new HashMap<>();
        int longestNumericStrLength = getLongestStringLength(numericStrOriginStrMap.keySet());
        numericStrOriginStrMap.forEach((numericStr, originalStr) -> {
            if (numericStr.length() < longestNumericStrLength) {
                String newKey = appendZerosAndGetString(numericStr, longestNumericStrLength - numericStr.length());
                numericStrWithEqualsLengthOriginStrMap.put(newKey, originalStr);
            } else {
                numericStrWithEqualsLengthOriginStrMap.put(numericStr, originalStr);
            }
        });

        //sort keys
        //['1023456']
        //['1122300']
        List<String> stringsAsNumbers = new ArrayList<>(numericStrWithEqualsLengthOriginStrMap.keySet());
        for (int i = 0; i < stringsAsNumbers.size() - 1; i++) {
            iterateAndSort(stringsAsNumbers);
        }

        //iterate other sorted ints and fetch by int key original string value
        //get by sorted keys original values
        List<String> result = new ArrayList<>();
        for (String numericStr : stringsAsNumbers) {
            result.add(numericStrWithEqualsLengthOriginStrMap.get(numericStr));
        }

        return result;
    }

    private String encodeStringAsNumeric(String s) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            result.append(charAsIntMap.get(s.charAt(i)));
        }

        return result.toString();
    }

    private int getLongestStringLength(Collection<String> strings) {
        int result = 0;

        for (String s : strings) {
            if (s.length() > result) {
                result = s.length();
            }
        }

        return result;
    }

    private String appendZerosAndGetString(String strToAppend, int numberOfZeros) {
        StringBuilder stringBuilder = new StringBuilder(strToAppend);

        for (int i = 0; i < numberOfZeros; i++) {
            stringBuilder.append("0");
        }

        return stringBuilder.toString();
    }

    private void iterateAndSort(List<String> toSort) {
        for (int i = 0, j = 1; j < toSort.size(); i++, j++) {
            String prev = toSort.get(i);
            String next = toSort.get(j);

            if (compareNumericStrings(prev, next) < 0) {
                Collections.swap(toSort, i, j);
            }
        }
    }

    /**
     * Compare to strings which contains only numbers
     *
     * @param s1 String with numeric chars
     * @param s2 String with numeric chars
     * @return 1 if s1 > s2, -1 if s2 > s1, otherwise 0
     */
    private int compareNumericStrings(String s1, String s2) {
        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();

        for (int i = 0; i < s1.length(); i++) {
            int s1CharAsInt = Integer.parseInt(Character.toString(s1Chars[i]));
            int s2CharAsInt = Integer.parseInt(Character.toString(s2Chars[i]));

            if (s1CharAsInt - s2CharAsInt > 0) {
                return 1;
            } else if (s1CharAsInt - s2CharAsInt < 0) {
                return -1;
            }

        }

        return 0;
    }




}
