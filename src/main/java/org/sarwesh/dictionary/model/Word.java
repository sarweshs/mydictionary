package org.sarwesh.dictionary.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Word {

    @Id
    private ObjectId _id;
    
    private String lang = "en";

    public String word;
    public List<String> meaning = new ArrayList<>();
    public String allMeanings;

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }
    
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<String> getMeaning() {
        return meaning == null ? new ArrayList<String>():meaning;
    }

    public String getLang() {
		return lang;
	}

    public void setLang(String lang) {
		this.lang = lang;
	}

	public String getAllMeanings() {
		return allMeanings;
	}

	public void setAllMeanings(String allMeanings) {
		this.allMeanings = allMeanings;
	}



}
