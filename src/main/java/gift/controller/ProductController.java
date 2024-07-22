package gift.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gift.dto.ProductDto;
import gift.dto.request.ProductCreateRequest;
import gift.dto.response.ProductPageResponse;
import gift.service.CategoryService;
import gift.service.ProductService;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService){
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getProducts(Model model, @RequestParam(value = "page", defaultValue = "0")int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        ProductPageResponse paging = productService.getPage(page, size);
        model.addAttribute("paging", paging);
        return "admin_page";
    }

    @GetMapping("/new")
    public String showProductForm(Model model){
        model.addAttribute("product", new ProductCreateRequest("", 0, "", "", "", 0));
        model.addAttribute("categories", categoryService.findAll().getCategories());
        return "product_form";
    }

    @PostMapping("/new")
    public String addProduct(@Valid @ModelAttribute ProductCreateRequest productCreateRequest, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            model.addAttribute("product", productCreateRequest);
            model.addAttribute("categories",categoryService.findAll().getCategories());
            return "product_form";
        }

        productService.addProduct(productCreateRequest);
        return "redirect:/api/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id)); 
        model.addAttribute("categories", categoryService.findAll().getCategories());
        return "edit_product_form";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id,@Valid @ModelAttribute ProductDto productDto, BindingResult bindingResult, Model model) {
        
        if(bindingResult.hasErrors()){
            model.addAttribute("product", productDto);
            model.addAttribute("categories", categoryService.findAll().getCategories());
            return "product_form";
        }

        productService.updateProduct(productDto);
        return "redirect:/api/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/api/products";
    }
}