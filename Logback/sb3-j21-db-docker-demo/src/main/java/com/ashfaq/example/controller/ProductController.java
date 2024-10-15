package com.ashfaq.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashfaq.example.entity.Product;
import com.ashfaq.example.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}

	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		logger.info("Received request to create user: {}", product);

		return productService.saveProduct(product);
	}

	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
		Product existingProduct = productService.getProductById(id);
		if (existingProduct != null) {
			product.setId(id);
			return productService.saveProduct(product);
		}
		return null;
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		logger.warn("Received request to delete product with ID: {}", id);

		productService.deleteProduct(id);
	}
}
