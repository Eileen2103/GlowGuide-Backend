package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.ProductCreateDto;
import dto.ProductResponseDto;
import service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	public ProductService productService;

	@PostMapping("/add/{userId}")
	public ResponseEntity<String> addProduct(@RequestBody ProductCreateDto dto, @PathVariable Long userId) {
		return ResponseEntity.ok(productService.saveNewProduct(dto, userId));

	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<ProductResponseDto>> getUserProducts(@PathVariable Long userId) {
		return ResponseEntity.ok(productService.getMyProducts(userId));
	}

}
