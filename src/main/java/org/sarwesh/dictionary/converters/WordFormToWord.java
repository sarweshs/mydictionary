package org.sarwesh.dictionary.converters;

import org.bson.types.ObjectId;
import org.sarwesh.dictionary.forms.WordForm;
import org.sarwesh.dictionary.model.Word;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class WordFormToWord implements Converter<WordForm, Word> {

    @Override
    public Word convert(WordForm wordForm) {
    	Word word = new Word();
        if (wordForm.getId() != null  && !StringUtils.isEmpty(wordForm.getId())) {
        	word.setId(new ObjectId(wordForm.getId()));
        }
        word.setLang(wordForm.getLang());
        word.setWord(wordForm.getWord());
        word.getMeaning().addAll(wordForm.getMeaning());
        return word;
    }
}
