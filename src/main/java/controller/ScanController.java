package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.AnalysisResultDto;
import dto.ScanRequestDto;
import service.ScannedProductService;

@RestController
@RequestMapping("/api/scan")
public class ScanController {

	private final ScannedProductService scannedProductService;

	public ScanController(ScannedProductService scannedProductService) {
		this.scannedProductService = scannedProductService;
	}

	@PostMapping
	public ResponseEntity<AnalysisResultDto> scan(@RequestBody ScanRequestDto dto) {

		AnalysisResultDto result = scannedProductService.analyzeAndSave(dto.getUserId(), dto.getProductName(),
				dto.getImageUrl(), dto.getIngredients());

		return ResponseEntity.ok(result);
	}

}
