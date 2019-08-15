package org.sarwesh.dictionary.forms;


import java.util.ArrayList;
import java.util.List;

public class WordForm {
    private String id;
    private String lang;
    private String word;
    private List<String> meaning;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<String> getMeaning() {
		return meaning == null? new ArrayList<String>():meaning;
	}

}
