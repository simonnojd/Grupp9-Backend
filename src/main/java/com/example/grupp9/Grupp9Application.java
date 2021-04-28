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
    public CommandLineRunner productMockData(ProductRepository productRepository, CategoryRepository categoryRepository,CompanyRepository companyRepository){

        return args -> {
            Company company1 = new Company("Coca cola company");
            Company company2 = new Company("Pringles");
            Company company3 = new Company("Estrella");
            Company company4 = new Company("Scan");
            Company company5 = new Company("Uncle Ben");
            Company company6 = new Company("Gränsbo Potatis AB");

            companyRepository.save(company1);
            companyRepository.save(company2);
            companyRepository.save(company3);
            companyRepository.save(company4);
            companyRepository.save(company5);
            companyRepository.save(company6);

            Category category01 = new Category("Läskedryck");
            Category category02 = new Category("Skafferi");
            Category category03 = new Category("Husmanskost");
            categoryRepository.save(category01);
            categoryRepository.save(category02);
            categoryRepository.save(category03);

            Product product01 = new Product("Coca cola", 15, 100, "Läskedryck med smak av cola", "https://citymarteg.com/image/cache/catalog/cat/Drinks/soda/coca-cola-bottle-330-ml-550x550.jpg",category01,company1);
            Product product02 = new Product("Ris", 10, 100, "Uncle bens odlade ris", "https://d1hr6nb56yyl1.cloudfront.net/product-images/24325-560.jpg",category02,company5);
            Product product03 = new Product("Spagetti", 19, 100, "Pasta från Italien", "https://outofhome.se/media/catalog/product/cache/30/image/600x/9df78eab33525d08d6e5fb8d27136e95/1/0/100048-141413_spaghetti_2.jpg",category02,company3);
            Product product04 = new Product("Köttbullar", 22, 100, "Handgjorda från mamma scan hon själv", "https://www.scan.se/contentassets/4c730ee428b445c1bf1b935befc50ebb/700067_scan_mammas-kottb.350gx12-srs_vinkel2.png?preset=scan-1:1",category03,company4);
            Product product05 = new Product("Choklad",15,200,"Handgjorda från mamma scan hon själv","https://static.mathem.se/shared/images/products/large/07310511210403_c1n0.jpeg.jpg",category03,company4);
            Product product06 = new Product("Svartvinbär Saft", 50, 300, "Läskande saft med svartvinbär","https://media.martinservera.se/f_auto,q_auto/prod/martinservera/produkt/2/001/260/343483_01_produktbild.jpg",category01,company1);
            Product product07 = new Product("Potatis",3,900,"God potatis från Gränsbo","https://media.martinservera.se/f_auto,q_auto/prod/martinservera/produkt/2/001/427/$v2/870956_01_produktbild.jpg",category02,company6);
            Product product08 = new Product("Hallon Saft", 50,300,"Läskande saft med hallon","https://media.martinservera.se/f_auto,q_auto/prod/martinservera/produkt/2/001/761/018267_01_produktbild.jpg",category01,company6);

            productRepository.save(product01);
            productRepository.save(product02);
            productRepository.save(product03);
            productRepository.save(product04);
            productRepository.save(product05);
            productRepository.save(product06);
            productRepository.save(product07);
            productRepository.save(product08);




            for(Category category : categoryRepository.findAll()){
                System.out.println(category.toString());
            }

            for(Product product : productRepository.findAll()){
                System.out.println(product.toString());
            }
            for(Company company : companyRepository.findAll()){
                System.out.println(company.toString());
            }
        };
    }
}
