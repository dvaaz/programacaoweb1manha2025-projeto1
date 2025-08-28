package com.senac.games.controller;

import com.senac.games.entities.Inscricao;
import com.senac.games.service.InscricaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/inscricao")
@Tag(name="Inscrição", description="API para gerenciamento de inscrições")
public class InscricaoController {

  private final InscricaoService inscricaoService;
  public InscricaoController (InscricaoService inscricaoService) {
    this.inscricaoService = inscricaoService;
  }

  @PostMapping("/criar")

  @GetMapping("/listar")
  @Operation(summary="Listar inscrições", description = "Endpoint para listar todas as inscrições")
  public ResponseEntity<List<Inscricao>> listarInscricoes() {
    return ResponseEntity.ok(inscricaoService.listarInscricoes());
  }

}
