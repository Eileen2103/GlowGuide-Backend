package ocr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/ocr")
public class OcrController {

	@Autowired
	private OcrService ocrService;

	public OcrController(OcrService ocrService) {

		this.ocrService = ocrService;
	}

	@PostMapping("/extract")
	public ResponseEntity<OcrResultDto> extract(@RequestParam("image") MultipartFile image) {

		OcrResultDto result = ocrService.extractText(image);
		return ResponseEntity.ok(result);

	}

}
