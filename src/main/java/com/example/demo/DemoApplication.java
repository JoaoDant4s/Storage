package com.example.demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.csv.CSVReader;
import com.example.demo.model.Produto;
import com.example.demo.repository.MySqlRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private final JdbcTemplate jdbcTemplate;

	public DemoApplication(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private void createTable() {
		String createTableQuery = "CREATE TABLE IF NOT EXISTS produtos (" +
				"produto_id INT PRIMARY KEY," +
				"nome VARCHAR(100)," +
				"quantidade INT," +
				"categoria VARCHAR(100)," +
				"preco DECIMAL(10,2)" +
				")";
		jdbcTemplate.execute(createTableQuery);
	}

	private void dropTable() {
		String dropTableQuery = "DROP TABLE IF EXISTS produtos";
		jdbcTemplate.execute(dropTableQuery);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override 
	public void run(String... args) throws Exception {
		CSVReader csvReader = new CSVReader();
		MySqlRepository mySqlRepository = new MySqlRepository(jdbcTemplate);

		createTable();

		List<Produto> products = csvReader.readCSV();
		mySqlRepository.save(products);

		csvReader.clean();
	}
}
