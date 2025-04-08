package com.example.demo.algorithm.demo25.demo2501;


import java.util.ArrayList;
import java.util.HashMap;

public class Demo0120 {

    public static void main(String[] args) {
        boolean scramble = new isScramble().isScramble("abcde", "cabed");
        System.out.println();
    }


}

// 87
/*
s1 = "ab c de", s2 = "caebd"



c ab ed

abcdbdacbdac            b                             b                             b

bdacabcdbdac       a       cdbdacbdac          abcd          dacbdac      abcdbdac       dac
       b                             b                             b
  a       cdbdacbdac          abcd          dacbdac      abcdbdac       dac


                                                                                  d
                                                                             abc    bdac
                                                                                  d
                                                                             abcdb   ac
            c
        a      e
          b   d

ab   /cde
/ab  /cde

                 a/bc
        a bc             bc a
   a b c    a c b    b c a    c b a

                 ab/c
        ab c             c ab
   a b c    b a c    c a b    c b a

 */
class isScramble1 {
    private String target;
    private boolean result;

    public boolean isScramble(String s1, String s2) {

        if (s1.equals(s2)) {
            return true;
        }
        // 长度不一致，有字符不存在，一定不是
        if (!checkValid(s1, s2)) {
            return false;
        }

        this.target = s2;
        this.result = false;
        getSplit(s1);

        return this.result;
    }

    public boolean checkValid(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        char[] chars = s1.toCharArray();
        char[] chars1 = s2.toCharArray();
        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();
        for (char c : chars) {
            characterIntegerHashMap.put(c, characterIntegerHashMap.getOrDefault(c, 0) + 1);
        }

        for (char c : chars1) {
            if (!characterIntegerHashMap.containsKey(c) || characterIntegerHashMap.get(c) <= 0) {
                return false;
            }
            characterIntegerHashMap.put(c, characterIntegerHashMap.get(c) - 1);
        }

        return true;
    }


    public ArrayList<String> getSplit(String s) {
        ArrayList<String> resultList = new ArrayList<>();
        int length = s.length();
        if (length == 1) {
            resultList.add(s);
            return resultList;
        }
        for (int i = 1; i < length; i++) {
            if (this.result) {
                return resultList;
            }
            ArrayList<String> first = getSplit(s.substring(0, i));
            ArrayList<String> second = getSplit(s.substring(i, length));
            resultList.addAll(getResult(first, second));
        }

        return resultList;

    }

    public ArrayList<String> getResult(ArrayList<String> first, ArrayList<String> second) {
        ArrayList<String> resultList = new ArrayList<>();
        for (String f : first) {
            for (String s : second) {
                if ((s + f).equals(this.target) || (f + s).equals(this.target)) {
                    this.result = true;
                    return resultList;
                }
                resultList.add(f + s);
                resultList.add(s + f);
            }
        }
        return resultList;
    }


}

class isScramble {
    private String target;
    private boolean result;

    public boolean isScramble(String s1, String s2) {

        if (s1.equals(s2)) {
            return true;
        }
        // 长度不一致，有字符不存在，一定不是
        if (!checkValid(s1, s2)) {
            return false;
        }

        this.target = s2;
        this.result = false;


        return this.result;
    }

    public boolean checkValid(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        char[] chars = s1.toCharArray();
        char[] chars1 = s2.toCharArray();
        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();
        for (char c : chars) {
            characterIntegerHashMap.put(c, characterIntegerHashMap.getOrDefault(c, 0) + 1);
        }

        for (char c : chars1) {
            if (!characterIntegerHashMap.containsKey(c) || characterIntegerHashMap.get(c) <= 0) {
                return false;
            }
            characterIntegerHashMap.put(c, characterIntegerHashMap.get(c) - 1);
        }

        return true;
    }


    public boolean getSplit(String s1, String s2) {
        char root = s2.charAt(0);
        HashMap<Character, ArrayList<Integer>> position = getPosition(s1);
        ArrayList<Integer> integerArrayList = position.get(root);
        for (Integer pos : integerArrayList) {
            String first = s1.substring(0, pos);
            String second = s1.substring(pos + 1, s1.length());

            String substring = s2.substring(s2.length() - first.length(), s2.length());
        }


        return false;
    }

    public HashMap<Character, ArrayList<Integer>> getPosition(String s1) {
        HashMap<Character, ArrayList<Integer>> characterArrayListHashMap = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            ArrayList<Integer> orDefault = characterArrayListHashMap.getOrDefault(s1.charAt(i), new ArrayList<>());
            orDefault.add(i);
            characterArrayListHashMap.put(s1.charAt(i), orDefault);
        }

        return characterArrayListHashMap;
    }


}


// 174
/*
[-2,-3,3],
[-5,-10,1],
[10,30,-5]

[-2,-5,-2],
[-7,-15,-1],
[3,33,-5]


[ ,   ,  3 ],
[21, 16, 6 ],
[ , 6 , 1 ]
 */
class calculateMinimumHP {
    public int calculateMinimumHP(int[][] dungeon) {
        return 0;

    }
}