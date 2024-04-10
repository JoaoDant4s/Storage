package com.example.demo.controller;

import java.util.List;

import com.example.demo.model.Produto;
import com.example.demo.service.ProductService;

public class ProductController {
    private ProductService productService;

    public ProductController(){
        this.productService = new ProductService();
    }

    public List<String> getAllCategories(List<Produto> products){
        return productService.allCategories(products);
    }

    public void printCategories(List<String> categories){
        System.out.println("---- CATEGORIAS CADASTRADAS ----");
        categories.stream().forEach(System.out::println);
    }

    public void printProductsByCategory(List<String> categories, List<Produto> products){
        System.out.println("---- PRODUTOS POR CATEGORIA ----");
        categories.stream().forEach(category -> {
            System.out.println(category + ": " + productService.productsByCategory(products, category) +" produtos.");
        });
    }

    public void printProductAverage(List<Produto> products){
        System.out.println("---- MEDIA DE VALOR DOS PRODUTOS ----");
        System.out.println("R$ " + productService.productAverage(products));
    }

    public void printLowerStockProducts(List<Produto> products){
        System.out.println("---- PRODUTOS EM ESTOQUE BAIXO ----");
        productService.findLowStockProducts(products).stream().forEach(product -> {
            System.out.println(product.getNome() + ", " + product.getQuantidade() + " itens.");
        });
    }
}
