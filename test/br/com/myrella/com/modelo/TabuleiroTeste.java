package br.com.myrella.com.modelo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TabuleiroTeste {

//	
//	private Tabuleiro tabuleiro;
//	
//	@BeforeEach
//	void startup() {
//		tabuleiro = new Tabuleiro(6, 6, 3);
//	}
	
	@Test
	void dadoUmTabuleiroAoExecutarOMetodoAbrirEntaoDeveraAbrirUmCampo() {
		List<Campo> campos = new ArrayList<>();
		Campo campo = new Campo(5, 2);
		campos.add(campo);
		
		Tabuleiro tabuleiro1 = new Tabuleiro(6, 6, 3);
		tabuleiro1.abrir(5, 2);
		assertTrue(campo.isAberto());
		
	}

}
