package com.senac.games.controller;

import com.senac.games.dto.request.InscricaoDTORequest;
import com.senac.games.dto.response.InscricaoDTOResponse;
import com.senac.games.entities.Inscricao;
import com.senac.games.service.InscricaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  @Operation(summary = "Criar nova inscricao", description = "Endpoint para criar inscricao")
  public ResponseEntity<InscricaoDTOResponse> criarInscricao(@Valid @RequestBody InscricaoDTORequest inscricao) {
    return ResponseEntity.status(HttpStatus.CREATED).body(inscricaoService.criarInscricao(inscricao));
  }

  @GetMapping("/listar")
  @Operation(summary="Listar inscrições", description = "Endpoint para listar todas as inscrições")
  public ResponseEntity<List<Inscricao>> listarInscricoes() {
    return ResponseEntity.ok(inscricaoService.listarInscricoes());
  }

}
