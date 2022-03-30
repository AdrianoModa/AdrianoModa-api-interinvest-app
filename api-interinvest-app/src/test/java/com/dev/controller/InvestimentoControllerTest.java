package com.dev.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.dev.service.ClienteService;
import com.dev.service.InvestimentoService;
import com.dev.service.PapelService;

import io.restassured.http.ContentType;

@WebMvcTest
public class InvestimentoControllerTest {

	@Autowired
	private InvestimentoController investimentoController;

	@MockBean
	private InvestimentoService investimentoService;

	@MockBean
	private ClienteService clienteService;	

	@MockBean
	private PapelService papelService;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.investimentoController);
	}

	@Test
	public void retornarOk_QuandoListarTodos() {		
		given()
		.accept(ContentType.JSON)
		.when()
		.get("/investimento")
		.then()
		.status(HttpStatus.OK);
	}

	@Test
	public void retornarOk_QuandoCpfInvalidoPorQuantidadeNumeros() {
		given()
		.accept(ContentType.JSON)
		.when()
		.post("/investimento/{valorAInvestir}/{cpfCliente}/{quantidadeEmpresaAInvestir}", 100.5, "15214258758", 5)
		.then()
		.statusCode(HttpStatus.OK.value());
	}

}
