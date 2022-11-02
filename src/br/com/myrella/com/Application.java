package br.com.myrella.com;

import br.com.myrella.com.modelo.Tabuleiro;
import br.com.myrella.com.visao.TabuleiroConsole;

public class Application {
	public static void main(String[] args) {

		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
		new TabuleiroConsole(tabuleiro);
	}

}
