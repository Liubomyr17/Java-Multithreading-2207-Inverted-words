package com.company;

/*

2207 Inverted words

In the main method from the console, read the name of the file that contains words separated by spaces.
Find in the text all pairs of words that are mutually appealing. Add them to result.
Use StringBuilder.
Example file contents
mouth tor cake about
oh that one
Conclusion:
mouth tor
oh oh
that one

Requirements:
1. The main method should read the file name from the keyboard.
2. StringBuilder should be used in the main method
3. The result list should be filled with the correct pairs according to the condition of the problem.
4. The Solution class must contain the nested Pair class.
5. In the Pair class, a constructor without parameters (or a default constructor) must be declared.

*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> words = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(reader.readLine()));
            reader.close();

            while (bufferedReader.ready()) {
                String[] wordsInLine = bufferedReader.readLine().split(" ");
                for (String s : wordsInLine) {
                    words.add(s);
                }
            }
            bufferedReader.close();
            for (String s : words) {
                StringBuilder str = new StringBuilder(s).reverse();
                String secondStr = str.toString();
                boolean isNotSameWord = true;

                Pair pair = new Pair();
                pair.first = secondStr;
                pair.second = s;

                Pair doublepair = new Pair();
                doublepair.first = s;
                doublepair.second = secondStr;
                if(words.contains(secondStr)&&!result.contains(pair)&&!result.contains(doublepair)) {
                    if(s.equals(secondStr)) {
                        isNotSameWord = false;
                        int x = words.indexOf(s);
                        if(words.lastIndexOf(s)!=x) {
                            isNotSameWord = true;
                        }
                    }
                    if(isNotSameWord) {
                        result.add(pair);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null && second != null ? second :
                            second == null && first != null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}

