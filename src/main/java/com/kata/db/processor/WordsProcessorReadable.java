package com.kata.db.processor;

import com.kata.db.data.Words;
import com.kata.db.model.WordCombination;
import com.kata.db.util.Utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class WordsProcessorReadable {

    private final Set<String> totalWordSet;
    private final Integer wordCombinationMaxSize;

    public WordsProcessorReadable(Set<String> totalWordSet, int wordCombinationMaxSize) {
        this.wordCombinationMaxSize = wordCombinationMaxSize;
        this.totalWordSet = totalWordSet;
    }

    public WordsProcessorReadable(int wordCombinationMaxSize) {
        this.wordCombinationMaxSize = wordCombinationMaxSize;
        this.totalWordSet = Words.getInstance(wordCombinationMaxSize).getWords();
    }

    public List<WordCombination> processWords() {
        long now = System.currentTimeMillis();
        List<WordCombination> wordCombinations = totalWordSet.stream()
                .filter((String word) -> word.length() == wordCombinationMaxSize)
                .map(this::getWordCombinations)
                .map(this::getExistingWordCombination)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        System.out.println("processWords:: END. Time Taken::" + (System.currentTimeMillis() - now));
        return wordCombinations;
    }

    public List<WordCombination> getWordCombinations(String word) {
        List<WordCombination> wordCombinations = new ArrayList<>();
        int wcIndex = 0;

        while (wcIndex < word.length() - 1) {
            wordCombinations.add(new WordCombination(word, wcIndex));
            wcIndex++;
        }
        return wordCombinations;
    }

    public Optional<WordCombination> getExistingWordCombination(List<WordCombination> wordCombinations) {
        return wordCombinations.stream().filter(wc -> Utility.isPresent(totalWordSet, wc.getPrefix(), wc.getSuffix())).findFirst();
    }
}
