package org.sarwesh.dictionary.converters;

import org.sarwesh.dictionary.forms.WordForm;
import org.sarwesh.dictionary.model.Word;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WordToWordForm implements Converter<Word, WordForm> {
    @Override
    public WordForm convert(Word word) {
    	WordForm wordForm = new WordForm();
    	wordForm.setId(word.getId().toHexString());
        wordForm.setLang(word.getLang());
        wordForm.setWord(wordForm.getWord());
        wordForm.getMeaning().addAll(word.getMeaning());
        return wordForm;
    }
}
