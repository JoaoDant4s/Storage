package com.example.demo.model;

public class Produto {
    private Long produtoId;
    private String nome;
    private Integer quantidade;
    private String categoria;
    private Double preco;

    public Produto(Long produtoId, String nome, Integer quantidade, String categoria, Double preco) {
        this.produtoId = produtoId;
        this.nome = nome;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.preco = preco;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
    
}
