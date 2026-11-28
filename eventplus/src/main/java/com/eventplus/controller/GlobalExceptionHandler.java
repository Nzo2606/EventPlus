package com.eventplus.controller;

import jakarta.xml.bind.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

// Centraliza o tratamento de erros de TODOS os controllers.
// Sem esta classe, qualquer ValidationException lançada pelas regras de negócio
// chegava ao cliente como HTTP 500 (erro interno), o que não é o caso: é erro do
// usuário (dado inválido ou regra não atendida), então o correto é HTTP 400.
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Regras de negócio lançam ValidationException. O projeto usa DOIS tipos com o mesmo nome:
    //  - jakarta.xml.bind.ValidationException: usado nos validadores de inscrição;
    //  - jakarta.validation.ValidationException: usado nos services (categoria, participante, evento...).
    // Os dois são tratados aqui, para que ambos virem HTTP 400 e não 500.
    // Transformamos a exceção em um JSON simples: {"status", "error"}.
    @ExceptionHandler({
            jakarta.xml.bind.ValidationException.class,
            jakarta.validation.ValidationException.class
    })
    public ResponseEntity<Map<String, Object>> handleValidation(Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    // Erros de Bean Validation (@NotNull, @NotBlank, @Future etc. nos DTOs com @Valid).
    // O Spring já reconhece essas falhas, mas por padrão as devolve em formato técnico.
    // Aqui devolvemos o nome do campo e a mensagem de cada erro.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> fields = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> fields.put(error.getField(), error.getDefaultMessage()));

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Invalid request data");
        body.put("fields", fields);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
