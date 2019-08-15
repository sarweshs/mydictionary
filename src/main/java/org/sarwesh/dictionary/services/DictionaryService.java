package org.sarwesh.dictionary.services;

import java.util.List;

import org.sarwesh.dictionary.model.Word;


public interface DictionaryService {

    List<Word> listAll();

    Word getById(String id);

    Word saveOrUpdate(Word word);

    void delete(String id);

}
