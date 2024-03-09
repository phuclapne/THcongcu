package com.example.NguyenTienLoi_LTJava_SQL.controller;

import com.example.NguyenTienLoi_LTJava_SQL.entity.Product;
import com.example.NguyenTienLoi_LTJava_SQL.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.io.File.separator;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.UUID.randomUUID;

@RequestMapping("/products")
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("listproduct", productService.GetAll());
        return "product/index";
    }

   /* @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "product/create";

    }

    @PostMapping("/create")
    public String create(@Valid Product newProduct,
                        BindingResult result, Model model) {
        return "redirect:/products";
    }*/


    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "product/create";
    }

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/create")
    public String create(@Valid Product newProduct, @RequestParam MultipartFile imageProduct, BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", newProduct);
            return "product/create";
        }

        if (imageProduct != null && imageProduct.getSize() > 0) {
            try {
                File saveFile = new ClassPathResource("static/image").getFile();
                String newImageFile = randomUUID() + ".png";
                java.nio.file.Path path = Paths.get(saveFile.getAbsolutePath() + separator + newImageFile);
                Files.copy(imageProduct.getInputStream(), path, REPLACE_EXISTING);
                newProduct.setImage(newImageFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        productService.addProduct(newProduct);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id")int id)
    {
        productService.remove(id);
        return "redirect:/products";
    }
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") int id, Model model){
        Product editProduct = productService.getProductById(id);
        if(editProduct != null){
            model.addAttribute("book", editProduct);

            return "product/edit";
        }else {
            return "not-found";
        }
    }

    @PostMapping("/edit")
    public String editBook(@Valid @ModelAttribute("product")Product updateBook, @RequestParam MultipartFile imageProduct, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){

            return "book/edit";
        }
        if (imageProduct != null && imageProduct.getSize() > 0) {
            try {
                File saveFile = new ClassPathResource("static/image").getFile();
                String newImageFile = randomUUID() + ".png";
                java.nio.file.Path path = Paths.get(saveFile.getAbsolutePath() + separator + newImageFile);
                Files.copy(imageProduct.getInputStream(), path, REPLACE_EXISTING);
                updateBook.setImage(newImageFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        productService.GetAll().stream()
                .filter(book -> book.getId() == updateBook.getId())
                .findFirst()
                .ifPresent(book -> {
                    productService.updateProduct(updateBook);
                });
        return "redirect:/books";
    }

}




