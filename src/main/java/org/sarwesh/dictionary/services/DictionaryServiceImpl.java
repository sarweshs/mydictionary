package org.sarwesh.dictionary.services;

import java.util.ArrayList;
import java.util.List;

import org.sarwesh.dictionary.model.Word;
import org.sarwesh.dictionary.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    public List<Word> dictionary;

    @Autowired
    DictionaryRepository dictionaryRepository;
    
    public Word search(String word) {

        Word d = dictionaryRepository.findByWord(word);
        return d;
    }


    public void add(Word dictionaryEntry) {
        dictionaryRepository.save(dictionaryEntry);
    }
    
    @Override
    public List<Word> listAll() {
        List<Word> words = new ArrayList<>();
        dictionaryRepository.findAll().forEach(words::add); //fun with Java 8
        return words;
    }

    @Override
    public Word getById(String id) {
        return dictionaryRepository.findById(id).orElse(null);
    }

    @Override
    public Word saveOrUpdate(Word word) {
    	Word d = search(word.getWord());
    	if(d != null) {
    		word.setId(d.getId());
    	}
    	dictionaryRepository.save(word);
        return word;
    }

	@Override
	public void delete(String id) {
		dictionaryRepository.deleteById(id.toString());
	}
}
