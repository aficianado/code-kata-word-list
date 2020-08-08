package com.kata.db;

import com.kata.db.data.Words;
import com.kata.db.model.WordCombination;
import com.kata.db.processor.WordsProcessorPerformance;
import com.kata.db.processor.WordsProcessorReadable;
import com.kata.db.util.Utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class MainExecutor {

    private static int wordCombineLen = 6;
    //private static Words words = Words.getInstance(wordCombineLen);
    private static Words words = Words.getInstance();

    public static void main(String[] args) {
        long now = System.currentTimeMillis();

        Set<String> wordsSet = words.getWords();
        System.out.println("*********** STARTED WordsProcessorReadable **************");
        WordsProcessorReadable wpr = new WordsProcessorReadable(wordsSet, 6);
        List<WordCombination> wcs = wpr.processWords();

        System.out.println("Completed Processing :: Total Time Taken::" + (System.currentTimeMillis() - now));
        String opFileName = "./output_" + now + "_READABLE.json";
        System.out.println(String.format("List of Word Combinations, printed below / in new file (name = {%s})in current dir :: combinations::{%d}",
                opFileName, wcs.size()));
        Utility.createFile(wcs, opFileName);
        System.out.println("*********** END OF WordsProcessorReadable **************");

        System.out.println("\n*********** STARTED WordsProcessorPerformance **************");
        long _now = System.currentTimeMillis();
        WordsProcessorPerformance wpp = new WordsProcessorPerformance(wordsSet, 6);
        List<WordCombination> _wcs = wpp.processWords();

        System.out.println("Completed Processing :: Total Time Taken::" + (System.currentTimeMillis() - _now));
        String opFileNamePerf = "./output_" + now + "_PERFORMANCE.json";
        System.out.println(String.format("List of Word Combinations, printed below / in new file (name = {%s})in current dir :: combinations::{%d}",
                opFileNamePerf, _wcs.size()));
        Utility.createFile(wcs, opFileNamePerf);
        System.out.println("*********** END OF WordsProcessorPerformance **************");
    }
}
