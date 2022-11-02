package br.com.myrella.com.modelo;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
	private int linhas;
	private int colunas;
	private int minas;
	
	
	private final List<Campo> campos = new ArrayList<>();
	
	
	public Tabuleiro(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
	}
	
	
	
}
