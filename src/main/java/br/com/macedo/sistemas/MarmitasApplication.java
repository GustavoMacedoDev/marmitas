package br.com.macedo.sistemas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.macedo.sistemas.util.SenhaUtils;

@SpringBootApplication
public class MarmitasApplication {

	public static void main(String[] args) {

		SpringApplication.run(MarmitasApplication.class, args);
		
		String senha = SenhaUtils.gerarBCrypt("123456");
		
		System.out.print(senha);
	}
    
}
