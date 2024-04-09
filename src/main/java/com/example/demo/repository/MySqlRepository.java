package com.example.demo.repository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.model.Produto;

public class MySqlRepository {
    private ExecutorService executor;
    private final JdbcTemplate jdbcTemplate;

    public MySqlRepository(JdbcTemplate jdbcTemplate) {
        this.executor = Executors.newCachedThreadPool();
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(List<Produto> data) {
        executor.execute(new Register(data));
    }

    private class Register implements Runnable {
        private List<Produto> products;

        public Register(List<Produto> products) {
            this.products = products;
        }

        @Override
        public void run() {
            products.forEach(produto -> {
                String sql = "INSERT INTO produtos (produto_id, nome, quantidade, categoria, preco) VALUES (?, ?, ?, ?, ?)";
                jdbcTemplate.update(sql, produto.getProdutoId(), produto.getNome(), produto.getQuantidade(), produto.getCategoria(), produto.getPreco());
            });
        }
    }
}
