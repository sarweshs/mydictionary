package org.sarwesh.dictionary.controllers;

import java.io.IOException;
import java.util.Arrays;

import javax.validation.Valid;

import org.sarwesh.dictionary.converters.WordToWordForm;
import org.sarwesh.dictionary.forms.WordForm;
import org.sarwesh.dictionary.model.Word;
import org.sarwesh.dictionary.services.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class WordController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WordController.class);
	@Autowired
	private DictionaryService wordService;
	
	@Autowired
	DictionaryController dictionaryController;

	private WordToWordForm wordToWordForm;

	@GetMapping("/")
	public String index() {
		return "word/upload";
	}

	@PostMapping("/upload") // //new annotation since 4.3
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:word/uploadStatus";
		}

		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			// Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			// Files.write(path, bytes);
			String string = new String(bytes);

			String lines[] = string.split("[\\r\\n]+");
			for (String line : lines) {
				LOGGER.debug("Processing:" + line);
				String words[] = line.split(" ");
				Word dictionaryWord = new Word();
				dictionaryWord.setWord(words[0]);
				dictionaryWord.setAllMeanings(words[1]);
				dictionaryWord.getMeaning().addAll(Arrays.asList(words[1].split(",")));
				wordService.saveOrUpdate(dictionaryWord);
				LOGGER.debug("Processed:" + line);
			}

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/uploadStatus";
	}

	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "word/uploadStatus";
	}

	@RequestMapping({ "/word/list", "/word" })
	public String listProducts(Model model) {
		model.addAttribute("words", wordService.listAll());
		return "word/list";
	}

	@RequestMapping("/word/show/{word}")
	public String getProduct(@PathVariable String word, Model model) {
		Word savedWord = dictionaryController.getWord(word);
		model.addAttribute("word", savedWord);
		return "word/show";
	}
	

	@RequestMapping("/word/search")
	public String newProduct(Model model) {
		model.addAttribute("wordForm", new WordForm());
		return "word/wordform";
	}

	@RequestMapping(value = "/word", method = RequestMethod.POST)
	public String saveOrUpdateProduct(@Valid WordForm wordForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "word/wordform";
		}

		//Word savedWord = wordService.saveOrUpdateWordForm(wordForm);
		return "redirect:/word/show/" + wordForm.getWord();
	}

	@RequestMapping("/word/delete/{id}")
	public String delete(@PathVariable String id) {
		wordService.delete(id);
		return "redirect:/word/list";
	}
}
