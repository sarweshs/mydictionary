package org.sarwesh.dictionary.repository;

import org.sarwesh.dictionary.model.Word;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DictionaryRepository extends PagingAndSortingRepository<Word, String>{

    Word findByWord(String word);
}
