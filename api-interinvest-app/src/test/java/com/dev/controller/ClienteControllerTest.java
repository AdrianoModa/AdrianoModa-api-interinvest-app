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
public class ClienteControllerTest {
	
	@Autowired
	private ClienteController clienteController;
	
	@MockBean
	private ClienteService clienteService;
	
	@MockBean
	private InvestimentoService investimentoService;
	
	@MockBean
	private PapelService papelService;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.clienteController);
	}
	
	@Test
	public void retornarOk_QuandoListarTodos() {		
		given()
		.accept(ContentType.JSON)
		.when()
		.get("/cliente")
		.then()
		.status(HttpStatus.OK);
	}

}
