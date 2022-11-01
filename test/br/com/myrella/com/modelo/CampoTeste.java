package br.com.myrella.com.modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.myrella.com.exceçao.ExplosaoException;

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
	void dadoUmCampoNaoMinadoEMarcadoAoAbrirDeveraRetornarFalse() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}

	@Test
	void dadoUmCampoMinadoENaoMarcadoAoAbrirDeveraLançarUmaExceçao() {
		campo.minar();

		assertThrows(ExplosaoException.class, () -> {
			assertFalse(campo.abrir());
		});

	}
	
	@Test
	void dadoUmCampoComVizinhancaSeguraAoExecutarAbrirEntaoDeveraRetornarTrue() {
		Campo vizinho1 = new Campo(2, 2);
		Campo vizinho2 = new Campo(1, 1);
		
		vizinho1.adicionarVizinho(vizinho2);
		
		campo.adicionarVizinho(vizinho1);
		campo.abrir();
		
		assertTrue(vizinho1.isAberto() && vizinho2.isAberto());
	}
	
	@Test
	void dadoUmCampoComVizinhosMinadosAoExecutarAbrirEntaoDeveraRetornarUmaExceçao() {
		Campo vizinho1 = new Campo(1, 1);
		Campo vizinho2 = new Campo(1, 1);
		Campo vizinho3 = new Campo(2, 2);
		vizinho2.minar();
		
				
		campo.adicionarVizinho(vizinho1);
		campo.adicionarVizinho(vizinho2);
		
		campo.adicionarVizinho(vizinho3);
		campo.abrir();
		
		assertTrue(vizinho3.isAberto() && vizinho1.isFechado());
	}
	

}
