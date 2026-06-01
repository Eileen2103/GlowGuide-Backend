package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.GeminiService;

@RestController
@RequestMapping("/gemini")
public class GeminiController {

	private final GeminiService geminiService;

	public GeminiController(GeminiService geminiService) {
		this.geminiService = geminiService;
	}

	@GetMapping("/test")
	public String test() {
		
		return geminiService.testCall("Gemini explain the game called call of duty modern warfare 3's story");
	}
}
