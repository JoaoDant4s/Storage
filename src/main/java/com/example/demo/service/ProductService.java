package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.model.Produto;

public class ProductService {
    public List<String> allCategories(List<Produto> products){
        List<String> categories = new ArrayList<>();
        products.stream().forEach(product -> {
            if(!categories.contains(product.getCategoria())){
                categories.add(product.getCategoria());
            }
        });
        return categories;
    }

    public Long productsByCategory(List<Produto> products, String category){
        return products.stream()
                       .filter(product -> product.getCategoria().equals(category))
                       .count();
    }

    public Double productAverage(List<Produto> products){
        double averagePrice = products.stream()
                                    .mapToDouble(Produto::getPreco)
                                    .average()
                                    .orElse(0.0);
        return averagePrice;
    }

    public List<Produto> findLowStockProducts(List<Produto> products) {
        return products.stream()
                       .filter(product -> product.getQuantidade() < 3)
                       .collect(Collectors.toList());
    }
}
