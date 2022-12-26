package com.kafka.producer.api.controller;



import com.kafka.producer.api.model.Product;
import com.kafka.producer.api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Create a new product record")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "201", description = "product created", content = { @Content(mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", description = "Bad request") })
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Product product) {
        Product response = productService.save(product);
        return new ResponseEntity<Product>( response, HttpStatus.OK );
    }

    @Operation(summary = "View a list of products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/list", produces = "application/json")
    public Iterable<Product> list(){
        Iterable<Product> products = productService.findAll();
        return products;
    }
}
