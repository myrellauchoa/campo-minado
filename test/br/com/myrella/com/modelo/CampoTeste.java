package br.com.myrella.com.modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.myrella.com.modelo.Campo;

public class CampoTeste {

	private Campo campo;
	
	@BeforeEach
	void startup() {
		campo = new Campo(3, 3);
	}
	
	@Test
	void dadoUmVizinhoAEsquerdaEntaoOVizinhoDeveraSerAdicionado() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void dadoUmVizinhoADireitaEntaoOVizinhoDeveraSerAdicionado() {
		Campo vizinho = new Campo(3, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void dadoUmVizinhoAcimaEntaoOVizinhoDeveraSerAdicionado() {
		Campo vizinho = new Campo(2, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void dadoUmVizinhoeEmbaixoEntaoOVizinhoDeveraSerAdicionado() {
		Campo vizinho = new Campo(4, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void dadoOutroCampoNasMesmasCoordenadasEntaoNaoDeveraAdicionarVizinho() {
		Campo vizinho = new Campo(1, 1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}
	
	@Test
	void dadoUmCampoAoExecutarAlternarMarcacaoEntaoDeveraRetornarTrue() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void dadoUmCampoAoExecutarDuasVezesAlternarMarcacaoEntaoDeveraRetornarTrue() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void dadoUmCampoNaoMinadoENaoMarcadoAoAbrirDeveraRetornarTrue() {
		assertTrue(campo.abrir());
	}
	
	
	@Test
	void dadoUmCampoNaoMinadoEMarcadoAoAbrirDeveraRetornarTrue() {
		
	}
	
}
