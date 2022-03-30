package com.dev.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.dev.domain.Status;
import com.dev.service.ClienteService;
import com.dev.service.InvestimentoService;
import com.dev.service.PapelService;
import io.restassured.http.ContentType;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@WebMvcTest
public class PapelControllerTest {

	@Autowired
	private PapelController papelController;

	@MockBean
	private PapelService papelService;
	
	@MockBean
	private ClienteService clienteService;
	
	@MockBean
	private InvestimentoService investimentoService;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.papelController);
	}

	@Test
	public void retornarOk_QuandoListarTodos() {		
		given()
		.accept(ContentType.JSON)
		.when()
		.get("/papel")
		.then()
		.status(HttpStatus.OK);
	}
	
	@Test
	public void retornarOk_QuandoStatusAtiva() {
		Status status = Status.ATIVA;
		given()
		.accept(ContentType.JSON)
		.when()
		.get("/papel/{status}", status)
		.statusCode();
	}
}
