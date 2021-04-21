package com.example.grupp9;

import com.example.grupp9.models.Category;
import com.example.grupp9.models.Company;
import com.example.grupp9.models.Product;
import com.example.grupp9.models.ProductQuantity;
import com.example.grupp9.repositories.CategoryRepository;
import com.example.grupp9.repositories.CompanyRepository;
import com.example.grupp9.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Grupp9Application {

    public static void main(String[] args) {
        SpringApplication.run(Grupp9Application.class, args);
    }

    @Bean
    public CommandLineRunner companyMockData(CompanyRepository repository){
        return args -> {
            Company company1 = new Company("Coca cola company");
            Company company2 = new Company("Pringles");
            Company company3 = new Company("Estrella");
            Company company4 = new Company("Scan");
            Company company5 = new Company("Uncle Ben");
            Company company6 = new Company("Gränsbo Potatis AB");

            repository.save(company1);
            repository.save(company2);
            repository.save(company3);
            repository.save(company4);
            repository.save(company5);
            repository.save(company6);

            for(Company company : repository.findAll()){
                System.out.println(company.toString());
            }
        };
    }

    @Bean
    public CommandLineRunner productMockData(ProductRepository repository){
        return args -> {
            Product product01 = new Product("Coca cola", 15, 100, "Läskedryck med smak av cola", "https://citymarteg.com/image/cache/catalog/cat/Drinks/soda/coca-cola-bottle-330-ml-550x550.jpg");
            Product product02 = new Product("Ris", 10, 100, "Uncle bens odlade ris", "https://d1hr6nb56yyl1.cloudfront.net/product-images/24325-560.jpg");
            Product product03 = new Product("Spagetti", 19, 100, "Pasta från Italien", "https://outofhome.se/media/catalog/product/cache/30/image/600x/9df78eab33525d08d6e5fb8d27136e95/1/0/100048-141413_spaghetti_2.jpg");
            Product product04 = new Product("Köttbullar", 22, 100, "Handgjorda från mamma scan hon själv", "https://www.scan.se/contentassets/4c730ee428b445c1bf1b935befc50ebb/700067_scan_mammas-kottb.350gx12-srs_vinkel2.png?preset=scan-1:1" );

            repository.save(product01);
            repository.save(product02);
            repository.save(product03);
            repository.save(product04);

            for(Product product : repository.findAll()){
                System.out.println(product.toString());
            }
        };
    }

    @Bean
    public CommandLineRunner categoryMockData(CategoryRepository repository){
        return args -> {
            Category category01 = new Category("Läskedryck");
            Category category02 = new Category("Skafferi");
            Category category03 = new Category("Husmanskost");

            repository.save(category01);
            repository.save(category02);
            repository.save(category03);

            for(Category category : repository.findAll()){
                System.out.println(category.toString());
            }

        };
    }
}
