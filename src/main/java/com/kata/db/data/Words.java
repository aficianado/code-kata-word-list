package com.kata.db.data;

import com.kata.db.util.C;
import com.kata.db.util.FileReaderUtility;
import com.kata.db.util.Utility;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Words {

    private static final Logger LOG = Logger.getLogger(Words.class.getName());
    private static Words wordsInstance = null;
    private final Set<String> words;
    private final String fileName = C.WORD_LIST_FILE_NAME;

    private Words() {
        try {
            this.words = (FileReaderUtility.getWords(this.fileName));
        } catch (Exception e) {
            String exMsg = Utility.getStackTrace(e);
            LOG.log(Level.SEVERE, "Runtime Exception occurred ::", exMsg);
            throw new RuntimeException(String.format("Could not read file -> {%s} containing words. :: \n Exception :: {%s}",
                    fileName, exMsg), e);
        }

    }

    private Words(int wordSize) {
        try {
            this.words = (FileReaderUtility.getWords(this.fileName, wordSize));
        } catch (Exception e) {
            String exMsg = Utility.getStackTrace(e);
            LOG.log(Level.SEVERE, "Runtime Exception occurred ::", exMsg);
            throw new RuntimeException(String.format("Could not read file -> {%s} containing words. :: \n Exception :: {%s}",
                    fileName, exMsg), e);
        }

    }

    public static Words getInstance()
    {
        if (wordsInstance == null) {
            wordsInstance = new Words();
        }
        return wordsInstance;
    }

    public static Words getInstance(int wordSize)
    {
        if (wordsInstance == null) {
            wordsInstance = new Words(wordSize);
        }
        return wordsInstance;
    }

    public Set<String> getWords() {
        return words;
    }
}
