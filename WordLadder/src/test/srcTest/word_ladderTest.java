import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;

/** 
* word_ladder Tester. 
* 
* @author <Authors name> 
* @since 03/10/2018 
* @version 1.0 
*/ 
public class word_ladderTest { 

@Before
public void before() throws Exception {
    System.out.println("before");
} 

@After
public void after() throws Exception {
    System.out.println("after");
}

/** 
* 
* Method: word(String start, String end, HashSet<String> dict) 
* 
*/ 
@Test
public void testWord() throws Exception {
    word_ladder word_ladder = new word_ladder();
    String start = "cog";
    String end = "dog";
    String line;
    HashSet<String> dic = new HashSet<String>();
    String file_name = new String("dictionary.txt");
    File file = new File(file_name);
    FileReader fr = new FileReader(file);
    BufferedReader br = new BufferedReader(fr);
    while((line = br.readLine()) != null)
    {
        dic.add(line);
        System.out.println(line);
    }
    word_ladder.word(start, end, dic);
//TODO: Test goes here... 
} 

/** 
* 
* Method: main(String[] args) 
* 
*/ 
@Test
public void testOpen_file() throws Exception {
    String start = new String("dog");
    String end = new String ("cog");
    File file = new File("dictionary.txt");
    word_ladder.open_file(start, end,file);
//TODO: Test goes here... 
} 


} 
