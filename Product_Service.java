package com.MyShop.Ecom_Pro.Services;


import com.MyShop.Ecom_Pro.Model.Product;
import com.MyShop.Ecom_Pro.repo.Product_repo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class Product_Service {

    private Product_repo repo_1;

    //constructor injection for repo
public Product_Service(Product_repo repo_1){
    this.repo_1 = repo_1;
}



    public List<Product>getAllProducts(){
//        System.out.println("product list in console :"+ prod_list);
        return repo_1.findAll() ;
    }

    /*
    public Product getProducts_by_id(int id) {
    return  repo_1.findById(id).get();
    }
    // becz we are using response entity we dont want .get()
     */

    public Product getProducts_by_id(int id) {
        return  repo_1.findById(id).orElse(null);

    }


    public Product addProduct( Product product, MultipartFile imageFile) throws IOException {

        product.setImageName(imageFile.getOriginalFilename());
        product.setImage_Type(imageFile.getContentType());
        product.setImage_Data(imageFile.getBytes());


        return repo_1.save(product);
    }


    public Product updateProduct (int id, Product product, MultipartFile imageFile) throws  IOException{
        product.setImageName(imageFile.getOriginalFilename());
        product.setImage_Type(imageFile.getContentType());
        product.setImage_Data(imageFile.getBytes());

        return  repo_1.save(product);
    }

    public  void deleteProduct(int id) {

//        Product pro = repo_1.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
        repo_1.deleteById(id);
//         return pro;
    }

    public List<Product> searchProducts(String keyword) {
        return repo_1.searchProducts(keyword);

    }
}
























































