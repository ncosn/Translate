package com.sgcc.yzd.translate.model;

public class TranslationData {
    public String text;
    public String translation;
    public int lang;

    public TranslationData() {
    }

    public TranslationData(String text, String translation, int lang) {
        this.text = text;
        this.translation = translation;
        this.lang = lang;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public int getLang() {
        return lang;
    }

    public void setLang(int lang) {
        this.lang = lang;
    }
}
