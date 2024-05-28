package com.brasens.messages;

public class ResponseMessages {
    public static org.springframework.http.ResponseEntity<String> Client_Register_BadUsername = org.springframework.http.ResponseEntity
            .status(org.springframework.http.HttpStatus.BAD_REQUEST)
            .body("Nome de usuario invalido.");

    public static org.springframework.http.ResponseEntity<String> Client_Register_UsernameAlreadyRegistered = org.springframework.http.ResponseEntity
            .status(org.springframework.http.HttpStatus.NOT_ACCEPTABLE)
            .body("Dados invalidos (UsernameAlreadyRegistered).");

    public static org.springframework.http.ResponseEntity<String> Client_Register_BadEmail = org.springframework.http.ResponseEntity
            .status(org.springframework.http.HttpStatus.BAD_REQUEST)
            .body("Endereco de email invalido.");

    public static org.springframework.http.ResponseEntity<String> Client_Register_EmailAlreadyRegistered = org.springframework.http.ResponseEntity
            .status(org.springframework.http.HttpStatus.NOT_ACCEPTABLE)
            .body("Dados invalidos (EmailAlreadyRegistered).");

    public static org.springframework.http.ResponseEntity<String> Client_Register_NullPassword = org.springframework.http.ResponseEntity
            .status(org.springframework.http.HttpStatus.BAD_REQUEST)
            .body("Por favor insira uma senha valida.");

    public static org.springframework.http.ResponseEntity<String> Client_FindByLogin_Null = org.springframework.http.ResponseEntity
            .status(org.springframework.http.HttpStatus.NOT_FOUND)
            .body("Usuario nao encontrado.");

    public static org.springframework.http.ResponseEntity<String> Product_FindByName_Null = org.springframework.http.ResponseEntity
            .status(org.springframework.http.HttpStatus.NOT_FOUND)
            .body("Produto nao encontrado.");

    public static org.springframework.http.ResponseEntity<String> Error_In_Date_Time = org.springframework.http.ResponseEntity
            .status(org.springframework.http.HttpStatus.BAD_REQUEST)
            .body("Erro na date e hora requisitada.");
}
