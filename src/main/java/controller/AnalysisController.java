package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dto.AnalysisResultDto;
import analysis.AnalysisOrchestrator;

@RestController
@RequestMapping("/api/analysis")
@CrossOrigin("*")
// @CrossOrigin(origins = "https://www.glowguideapp.com") Sadece bu resmi site istek atabilir! doğrusu bu
public class AnalysisController {

	private final AnalysisOrchestrator orchestrator;

	public AnalysisController(AnalysisOrchestrator orchestrator) {
		this.orchestrator = orchestrator;
	}

	@PostMapping("/{userId}")
	public ResponseEntity<AnalysisResultDto> scanProduct(@PathVariable Long userId,
			@RequestParam("productName") String productName, @RequestParam("imageUrl") String imageUrl,
			@RequestParam("image") MultipartFile image) {

		AnalysisResultDto result = orchestrator.run(userId, productName, imageUrl, image);
		return ResponseEntity.ok(result);
	}
}