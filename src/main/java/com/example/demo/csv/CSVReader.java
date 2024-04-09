package com.example.demo.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.model.Produto;

public class CSVReader {

    private static final Logger LOG = LoggerFactory.getLogger(CSVReader.class);
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private List<String> data;
    private Function<String, Produto> parseToProduct = line -> {
        String[] values = line.split(",");
        return new Produto(Long.parseLong(values[0]), values[1], Integer.parseInt(values[2]), values[3], Double.parseDouble(values[4]));
    };

    public CSVReader() {
        this.data = new ArrayList<>();
        try {
            this.fileReader = new FileReader("Storage.csv");
            this.bufferedReader = new BufferedReader(this.fileReader);
        } catch (FileNotFoundException e) {
            LOG.error(e.getMessage());
        }
    };

    public List<Produto> createProducts(){
        List<Produto> products = new ArrayList<>();
        data.forEach(line -> {
            products.add(parseToProduct.apply(line));
        });
        return products;
    }

    public List<Produto> readCSV() {
        String line;
        try {
            line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                data.add(line);
            }
            return createProducts();
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return null;
    }

    public void clean() {
        try {
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            LOG.info(e.getMessage());
        }
    }
}