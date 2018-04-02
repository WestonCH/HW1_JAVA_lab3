package com.weston;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.*;
import java.util.*;

@RestController
public class Word_ladderController {


    /*public String say(){
        return "Hello SpringBoot!";
    }*/
    public static void word(String start, String end, HashSet<String> dict) {
        Set<String> covered = new HashSet<String>();
        Queue<Vector<String>> word_ladders = new LinkedList<Vector<String>>();
        Vector<String> word_ladder = new Vector<String>();
        String restart = start;
        String reend = end;
        boolean found = false;

        covered.add(start);
        word_ladder.addElement(start);
        word_ladders.offer(word_ladder);
        String curr = "";

        while (!word_ladders.isEmpty() && !found) {
            word_ladder = word_ladders.poll();
            if (word_ladder.size() != 0) {
                curr = word_ladder.lastElement();
            }

            for (int i = 0; i < curr.length(); i++) {
                for (char j = 'a'; j <= 'z'; j++) {
                    String prev = curr;
                    StringBuffer te = new StringBuffer(prev);
                    te.setCharAt(i, j);
                    prev = te.toString();

                    if (prev.equals(end)) {
                        word_ladder.addElement(prev);
                        System.out.println("A ladder from " + restart + " to " + end + ":");
                        if (!restart.equals(start)) {
                            System.out.print(restart + " ");
                        }
                        System.out.println(word_ladder);
                        found = true;
                        return;
                    }
                    if ((!covered.contains(prev)) && dict.contains(prev)) {
                        word_ladder.addElement(prev);
                        word_ladders.offer(word_ladder);
                        word_ladder.removeElementAt(word_ladder.size() - 1);
                        covered.add(prev);
                    }
                }
            }
            word_ladder.removeAllElements();
        }
        System.out.println("No word ladder found from " + end + " back to " + restart + ".");

    }

    public static void open_file(String start, String end, File file) throws IOException {

        while (true) {
            HashSet<String> dic = new HashSet<String>();
            String line = "";
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                dic.add(line);
            }
            word(start, end, dic);
            break;
        }

    }
    @RequestMapping("/main")
    public static String main(String start, String end) throws IOException {
        String file_name = new String();
        while (true) {
            Scanner in1 = new Scanner(System.in);
            System.out.println("Dictionary file name? ");
            file_name = in1.nextLine();
            File file = new File(file_name);
            HashSet<String> dic = new HashSet<String>();
            String line = "";
            if (file.exists()) {
                while (true) {
                    Scanner in2 = new Scanner(System.in);
                    System.out.println("Word #1 (or Enter to quit) : ");
                    start = in2.nextLine();
                    System.out.println("Word #2 (or Enter to quit) : ");
                    end = in2.nextLine();

                    if (start.length() == end.length()) {
                        double beg = System.currentTimeMillis();
                        open_file(start, end, file);
                        double last = System.currentTimeMillis();
                        System.out.println("time consuming: " + ((last - beg) / 1000) + "s");
                        break;
                    } else {
                        return "The two words must be in the same size. Please re-input";

                    }
                }
                break;
            } else {
                System.out.println("Unable to open that file.  Try again.");
                continue;
            }

        }
        return "";
    }

}
