package com.kata.db.processor;

import com.kata.db.data.Words;
import com.kata.db.model.WordCombination;
import com.kata.db.util.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class WordsProcessorPerformance {

    private final Set<String> totalWordSet;
    private final Integer wordCombinationMaxSize;

    public WordsProcessorPerformance(Set<String> totalWordSet, int wordCombinationMaxSize) {
        this.wordCombinationMaxSize = wordCombinationMaxSize;
        this.totalWordSet = totalWordSet;
    }

    public WordsProcessorPerformance(int wordCombinationMaxSize) {
        this.wordCombinationMaxSize = wordCombinationMaxSize;
        this.totalWordSet = Words.getInstance(wordCombinationMaxSize).getWords();
    }

    public List<WordCombination> processWords() {
        long now = System.currentTimeMillis();
        List<WordCombination> wordCombinations = totalWordSet.stream()
                .filter(word -> word.length() == wordCombinationMaxSize)
                .map(this::getWordCombination)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        System.out.println("processWords:: END. Time Taken::" + (System.currentTimeMillis() - now));
        return wordCombinations;
    }

    public Optional<WordCombination> getWordCombination(String word) {
        char[] characters = word.toCharArray();
        StringBuilder firstPart = new StringBuilder();
        StringBuilder secondPart = new StringBuilder(word);
        Optional<WordCombination> wordCombination = Optional.empty();
        boolean isExistingWordPair = false;

        for (int i = 0; i < characters.length && !isExistingWordPair; i++) {
            char currentCharacter = characters[i];
            firstPart.append(currentCharacter);
            secondPart.deleteCharAt(0);

            isExistingWordPair = Utility.isPresent(totalWordSet, firstPart.toString(), secondPart.toString());
        }

        if (isExistingWordPair) {
            wordCombination = Optional.of(new WordCombination(word, firstPart.toString(), secondPart.toString()));
        }

        return wordCombination;
    }

    public Optional<WordCombination> getExistingWordCombination(List<WordCombination> wordCombinations) {
        return wordCombinations.stream().filter(wc -> Utility.isPresent(totalWordSet, wc.getPrefix(), wc.getSuffix())).findFirst();
    }
}
