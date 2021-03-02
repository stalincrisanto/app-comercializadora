package ec.edu.espe.monster.GR10COMERCIALIZADORA.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {
	
	@GetMapping({"", "/", "/store", "/store/home"})
	public String indexStore() {
		return "/store/home";
	}
	
	@GetMapping("/store/products")
	public String productsStore() {
		return "/store/products";
	}
}
