package br.com.myrella.com.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

	@Test
	void dadoUmCampoComVizinhosEntaoDeveraRetornarAQuantidadeDeMinas() {
		Campo vizinho1 = new Campo(1, 1);
		Campo vizinho2 = new Campo(2, 3);
		Campo vizinho3 = new Campo(2, 2);
		Campo vizinho4 = new Campo(3, 2);

		campo.adicionarVizinho(vizinho1);
		campo.adicionarVizinho(vizinho2);
		campo.adicionarVizinho(vizinho3);
		campo.adicionarVizinho(vizinho4);

		vizinho2.minar();
		vizinho4.minar();

		assertEquals(2, campo.minasNaVizinhanca());
	}

	@Test
	void dadoUmCampoAoExecutarReiniciarEntaoOCampoNaoDeveraSerMinado() {
		campo.reiniciar();
		assertFalse(campo.isMinado());
	}

	@Test
	void dadoUmCampoAoExecutarReiniciarEntaoOCampoDeveraSerFechado() {
		campo.reiniciar();
		assertTrue(campo.isFechado());
	}

	@Test
	void dadoUmCampoAoExecutarReiniciarEntaoOCampoNaoDeveraSerMarcado() {
		campo.reiniciar();
		assertFalse(campo.isMarcado());
	}

	@Test
	void dadoUmCampoNaoMinadoEAbertoAoExecutarObjetivoAlcancadoEntaoDeveraRetornarTrue() {
		campo.abrir();
		assertTrue(campo.objetivoAlcancado());
	}

	@Test
	void dadoUmCampoMinadoEMarcadoAoExecutarObjetivoAlcancadoEntaoDeveraRetornarTrue() {
		campo.minar();
		campo.alternarMarcacao();

		assertTrue(campo.objetivoAlcancado());
	}

	@Test
	void dadoUmCampoNaoMinadoEAbertoOutroCampoMinadoEMarcadoAoExecutarObjetivoAlcancadoEntaoDeveraRetornarTrue() {
		Campo campo1 = new Campo(3, 2);
		campo1.abrir();

		Campo campo2 = new Campo(3, 2);
		campo2.minar();
		campo2.alternarMarcacao();

		campo.adicionarVizinho(campo1);
		campo.adicionarVizinho(campo2);

		assertTrue(campo1.objetivoAlcancado() && campo2.objetivoAlcancado());

	}

	@Test
	void dadoUmCampoMinadoEAbertoOutroCampoMinadoEMarcadoAoExecutarObjetivoAlcancadoEntaoDeveraRetornarFalse() {
		Campo campo1 = new Campo(3, 2);
		campo1.abrir();
		campo1.minar();

		Campo campo2 = new Campo(3, 2);
		campo2.minar();
		campo2.alternarMarcacao();

		campo.adicionarVizinho(campo1);
		campo.adicionarVizinho(campo2);

		assertFalse(campo1.objetivoAlcancado() && campo2.objetivoAlcancado());
	}

	@Test
	void dadoUmCampoNaoMinadoEAbertoOutroCampoMinadoENaoMarcadoAoExecutarObjetivoAlcancadoEntaoDeveraRetornarFalse() {
		Campo campo1 = new Campo(3, 2);
		campo1.abrir();
		campo1.minar();

		Campo campo2 = new Campo(3, 2);
		campo2.minar();

		campo.adicionarVizinho(campo1);
		campo.adicionarVizinho(campo2);

		assertFalse(campo1.objetivoAlcancado() && campo2.objetivoAlcancado());
	}

	@Test
	void dadoUmCampoMarcadoAoExecutarToStringEntaoDeveraRetornarX() {
		campo.alternarMarcacao();
		assertEquals("x", campo.toString());
	}

	@Test
	void dadoUmCampoAbertoEMinadoAoExecutarToStringEntaoDeveraRetornarUmaString() {
		campo.abrir();
		campo.minar();
		assertEquals("*", campo.toString());
	}

	@Test
	void dadoUmCampoAbertoEComMinasVizinhasAoExecutarToStringEntaoDeveraRetornarUmaString() {
		campo.abrir();

		Campo vizinho2 = new Campo(3, 2);
		vizinho2.minar();
		campo.adicionarVizinho(vizinho2);

		assertEquals("1", campo.toString());
	}

	@Test
	void dadoUmCampoAbertoAoExecutarToStringEntaoDeveraRetornarUmaStringVazia() {
		campo.abrir();

		assertEquals(" ", campo.toString());
	}

	@Test
	void dadoUmCampoFechadoAoExecutarToStringEntaoDeveraRetornarUmaString() {
		assertEquals("?", campo.toString());
	}

	@Test
	void dadoUmCampoAoExecutarGetLinhaDeveraRetornarUmaLinha() {
		Campo campo = new Campo(3, 2);
		assertEquals(3, campo.getLinha());
	}

	@Test
	void dadoUmCampoAoExecutarGetColunaDeveraRetornarUmaColuna() {
		Campo campo = new Campo(3, 2);
		assertEquals(2, campo.getColuna());
	}

}
