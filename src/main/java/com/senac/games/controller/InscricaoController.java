package com.senac.games.controller;

import com.senac.games.dto.request.InscricaoDTORequest;
import com.senac.games.dto.request.InscricaoDTOUpdateStatusRequest;
import com.senac.games.dto.response.InscricaoDTOResponse;
import com.senac.games.dto.response.InscricaoDTOUpdateStatusResponse;
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

    public InscricaoController(InscricaoService inscricaoService) {
        this.inscricaoService = inscricaoService;

    }

    @PostMapping("/criar")
    @Operation(summary = "Criar nova inscricao", description = "Endpoint para criar nova Inscricao")
    public ResponseEntity<InscricaoDTOResponse> criarInscricao(@Valid @RequestBody InscricaoDTORequest inscricao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inscricaoService.criarInscricao(inscricao));
    }

    @GetMapping("/listar")
    @Operation(summary="Listar inscricaos", description = "Endpoint para listar todos as inscricaos")
    public ResponseEntity<List<Inscricao>> listarInscricaos() {
        return ResponseEntity.ok(inscricaoService.listarInscricaos());

    }

    @GetMapping("/listarInscricaoId/{inscricaoId}")
    @Operation(summary = "Listar Inscricao por Id", description="Endpoint para listar inscricao ativo por ID")
    public ResponseEntity<Inscricao> listarInscricaoPorId(@Valid @PathVariable("inscricaoId") Integer inscricaoId) {
        Inscricao inscricao = inscricaoService.listarInscricaoPorId(inscricaoId);

        if(inscricao != null){
            return ResponseEntity.ok(inscricaoService.listarInscricaoPorId(inscricaoId));
        } else
            return ResponseEntity.noContent().build();
    }


    @PutMapping("/atualizar/{inscricaoId}")
    @Operation(summary = "Atualizar inscricao", description = "Endpoint para atualizar a inscricao")
    public ResponseEntity<InscricaoDTOResponse> atualizarInscricao(
            @Valid
            @PathVariable("inscricaoId") Integer inscricaoId,
            @RequestBody InscricaoDTORequest inscricaoDTORequest
    ) {
        return ResponseEntity.ok(inscricaoService.atualizarInscricao(inscricaoId, inscricaoDTORequest));
    }

    @PatchMapping("/atualizarStatus/{inscricaoId}")
    @Operation(summary = "Alterar Status da inscricao", description="Endpoint para alterar o status da inscricao")
    public ResponseEntity<InscricaoDTOUpdateStatusResponse> atualizarStatus(
            @Valid
            @PathVariable("inscricaoId") Integer inscricaoId,
            @RequestBody InscricaoDTOUpdateStatusRequest inscricaoDTOUpdateStatusRequest
    ){
        return ResponseEntity.ok(inscricaoService.atualizarStatusInscricao(inscricaoId, inscricaoDTOUpdateStatusRequest));
    }

    @DeleteMapping("/remover/{inscricaoId}")
    @Operation(summary = "Remover Inscricao", description = "Endpoint para a remoção logica de inscricao")
    public ResponseEntity removerInscricao(
            @Valid @PathVariable("inscricaoId") Integer inscricaoId,
            @RequestBody InscricaoDTORequest inscricaoDTORequest
    ){
        this.inscricaoService.apagarInscricao(inscricaoId);
        return ResponseEntity.noContent().build();
    }
}
