package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dto.AnalysisResultDto;
import analysis.AnalysisOrchestrator;

@RestController
@RequestMapping("/api/analyze")
public class AnalysisController {

	private final AnalysisOrchestrator orchestrator;

	public AnalysisController(AnalysisOrchestrator orchestrator) {
		this.orchestrator = orchestrator;
	}

	@PostMapping
	public ResponseEntity<AnalysisResultDto> analyze(@RequestParam Long userId, @RequestParam String productName,
			@RequestParam String imageUrl, @RequestParam MultipartFile image) {

		AnalysisResultDto result = orchestrator.run(userId, productName, imageUrl, image);

		return ResponseEntity.ok(result);
	}
}