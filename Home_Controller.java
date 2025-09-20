package com.MyShop.Ecom_Pro.Controller;

import com.MyShop.Ecom_Pro.Model.Product;
import com.MyShop.Ecom_Pro.Services.Product_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
//@CrossOrigin(origins = "http://localhost:5173")

@RequestMapping("/api")
public class Home_Controller {


//    @Autowired // itis field injection
    private Product_Service service;


    @RequestMapping("/")
    public String greet(){
        System.out.println("in home controller");
        return " Welcome to the first part $ ";
    }

    //without status code
    /*
    @GetMapping("/products") // endpoint to get all products
    public List<Product>getAllProducts(){
        return service.getAllProducts();
    }
     */




    @GetMapping("/products") // endpoint to get all products
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }



    //constructor injection
    public Home_Controller(Product_Service service){
        this.service=service ;

    }


    /*
    @GetMapping("/product/{id}")
    public Product getProducts_by_id(@PathVariable int id){
        return service.getProducts_by_id(id);
    }
     */

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProducts_by_id(@PathVariable int id){
        Product produ = service.getProducts_by_id(id);

        if(produ!= null)
            return new ResponseEntity<>( produ,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/product")
    public  ResponseEntity<?> addProduct(@RequestPart Product product,
                                         @RequestPart MultipartFile imageFile){
        try {
            System.out.println("Details we get : "+product);

            Product product_1 = service.addProduct(product,imageFile);
            return  new ResponseEntity<>(product_1,HttpStatus.CREATED);
        } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR) ;

        }


    }

    @GetMapping("/product/{productid}/image")
    public ResponseEntity<byte[]> getImageByproductID(@PathVariable int productid){
        Product product = service.getProducts_by_id(productid);
        byte[] imageFile1 = product.getImage_Data();

        return  ResponseEntity.ok().contentType(MediaType.valueOf(product.getImage_Type()))
                .body(imageFile1);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id ,@RequestPart Product product,
                                                @RequestPart MultipartFile imageFile){

        Product product_2 = null ;
        try{
            product_2 =service.updateProduct( id , product , imageFile);

        } catch (IOException e) {
            return new ResponseEntity<>("failed to update",HttpStatus.INTERNAL_SERVER_ERROR);

        }

        if (product_2 != null){

            System.out.println("Changes made" + product_2);
            return  new ResponseEntity<>("Updated",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("failed to update", HttpStatus.BAD_REQUEST);
        }
        }


    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id ){
        Product product_3 = service.getProducts_by_id(id);

        if (product_3 !=null){
            service.deleteProduct(id);
            return  new ResponseEntity<>("Deleted Succesfully",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Not Deleted ",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        System.out.println("\nsearching with : "+ keyword);
        List<Product> productList =service.searchProducts(keyword);
        return new ResponseEntity<>(productList,HttpStatus.OK);
    }





}




//------------------------------------------------------------------------------------------//
/* Response entity for STatus code
Categories of HTTP Status Codes

They are always 3 digits (e.g., 200, 404, 500) and grouped into 5 categories:

1xx – Informational
👉 Request received, processing continues.
Example: 100 Continue.

2xx – Success
👉 Request was successful.
200 OK → Standard success.
201 Created → Resource created successfully (like after a POST).


3xx – Redirection
👉 Further action needed (redirect).
301 Moved Permanently
302 Found (temporary redirect).

4xx – Client Errors
👉 Problem with the request.
400 Bad Request → Invalid syntax in request.
401 Unauthorized → Need login/authentication.
403 Forbidden → Not allowed.
404 Not Found → Resource doesn’t exist.

5xx – Server Errors
👉 Problem with the server.
500 Internal Server Error
502 Bad Gateway
503 Service Unavailable
 */























