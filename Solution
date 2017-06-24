
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
Составить цепочку слов
*/
public class Solution {
    //TODO change to List<String>
    //TODO better name for w and change to private
    public static ArrayList<String> w = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        //TODO extract this logic into the function List<String> getWords(InputStream input)
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();
        BufferedReader fReader = new BufferedReader(new FileReader(file));
        //TODO why cities?
        String cities = "";
        while (fReader.ready()) {
            //TODO List<String> words = ...; words.add(fReader.readLine())
            cities += fReader.readLine() + " ";
        }
        fReader.close();
        String words [] = cities.split(" ");
        for (String s: words)
            w.add(s);
        StringBuilder result = getLine(words);
        System.out.println(result.toString());
    }
    //TODO return String. use List<String> words
    public static StringBuilder getLine(String... words) {
        
        ArrayList<String>[] lines = new ArrayList [words.length];
        for (int i = 0; i < words.length; i++) {
            ArrayList<String> list = new ArrayList<>();
            list.add(words[i]);
            lines[i] = list;
        }
        //TODO the algorithm is not correct. We need to use BredthFirst search or smth. Here you just have 4 loops
        for (int count = 0; count < words.length; count++ ) {
            for (int i = 0; i < words.length; i++) {
                int listSize = lines[i].size();
                for (int k = 0; k < listSize; k++) {
                    for (int j = 0; j < words.length; j++) {
                        if (lines[i].get(k).charAt(lines[i].get(k).length() - 1) == words[j].toLowerCase().charAt(0) && !lines[i].get(k).contains(words[j])) {
                            lines[i].add(lines[i].get(k) + " " + words[j]);
                        }
                    }

                }
            }
        }
        int max = 0;
        String maxLine = "";
        for (int i = 0; i < lines.length; i++) {
            for(String s: lines[i]) {
                int LineLength = 0;
                String [] arr = s.split(" ");
                LineLength = arr.length;
                if (LineLength > max) {
                    max = LineLength;
                    maxLine = s;
                }
            }
        }
        StringBuilder result = new StringBuilder(maxLine);
        return result;
    }
}
