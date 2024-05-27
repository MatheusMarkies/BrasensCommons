package com.brasens.messages;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseMessages {
	public static ResponseEntity Client_Register_BadUsername = ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Nome de usuario invalido.");
	
	public static ResponseEntity Client_Register_UsernameAlreadyRegistered = ResponseEntity
            .status(HttpStatus.NOT_ACCEPTABLE)
            .body("Dados invalidos (UsernameAlreadyRegistered).");
	
	public static ResponseEntity Client_Register_BadEmail = ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Endereco de email invalido.");
	
	public static ResponseEntity Client_Register_EmailAlreadyRegistered = ResponseEntity
            .status(HttpStatus.NOT_ACCEPTABLE)
            .body("Dados invalidos (EmailAlreadyRegistered).");
	
	public static ResponseEntity Client_Register_NullPassword = ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Por favor insira uma senha valida.");
	
	public static ResponseEntity Client_FindByLogin_Null = ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body("Usuario nao encontrado.");
	
	public static ResponseEntity Product_FindByName_Null = ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body("Produto nao encontrado.");
	
	public static ResponseEntity Error_In_Date_Time = ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Erro na date e hora requisitada.");
}
