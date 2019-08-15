package org.sarwesh.dictionary.controllers;

import org.sarwesh.dictionary.model.Word;
import org.sarwesh.dictionary.services.DictionaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DictionaryController {

    @Autowired
    public DictionaryServiceImpl dictionaryService;

    @RequestMapping("/search/{word}")
	public Word getWord(@PathVariable String word) {
    	return dictionaryService.search(word);
	}

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addWordAndMeaning(@RequestBody Word dictionaryEntry){
        dictionaryService.add(dictionaryEntry);
    }
}
