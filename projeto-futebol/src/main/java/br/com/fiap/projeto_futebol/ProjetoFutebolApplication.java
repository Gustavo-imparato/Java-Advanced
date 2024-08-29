package br.com.fiap.projeto_futebol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan
@ComponentScan
@SpringBootApplication
public class ProjetoFutebolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoFutebolApplication.class, args);
	}

}
