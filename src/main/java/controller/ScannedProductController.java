package controller;

import models.ScannedProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dto.AnalysisHistoryDto;
import dto.UpdateProductNameDto;
import service.ScannedProductService;
import java.util.List;

@RestController
@RequestMapping("/api/scannedProducts")
@CrossOrigin(origins = "*") // Mobil testlerde sorun çıkmaması için cors açık
public class ScannedProductController {

	private final ScannedProductService scannedProductService;

	public ScannedProductController(ScannedProductService scannedProductService) {
		this.scannedProductService = scannedProductService;
	}

	@GetMapping("/history/{userId}")
	public ResponseEntity<List<AnalysisHistoryDto>> getUserAnalysisHistory(@PathVariable Long userId) {

		return ResponseEntity.ok(scannedProductService.getHistoryByUserId(userId));
	}
	
	@PutMapping("/{productId}/name")
	public ResponseEntity<ScannedProduct> updateProductName(
	        @PathVariable Long productId,
			@RequestBody UpdateProductNameDto dto) {

	    ScannedProduct updated =
	            scannedProductService.updateProductName(
	                    productId,
	                    dto.getProductName());

	    return ResponseEntity.ok(updated);
	}
}