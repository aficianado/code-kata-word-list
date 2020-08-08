package com.kata.db.model;

public class WordCombination implements java.io.Serializable {

    private String word;
    private String prefix;
    private String suffix;

    public WordCombination(String word, String prefix, String suffix) {
        this.word = word;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public WordCombination(String originalWord, int index) {
        this.word = originalWord;
        this.prefix = originalWord.substring(0, index + 1);
        this.suffix = originalWord.substring(index + 1);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public String toString() {
        return "WordCombination {" +
                "word='" + word + '\'' +
                ", prefix='" + prefix + '\'' +
                ", suffix='" + suffix + '\'' +
                '}';
    }
}
