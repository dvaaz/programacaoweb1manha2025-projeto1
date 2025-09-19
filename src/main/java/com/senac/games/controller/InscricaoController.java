package com.senac.games.controller;

import com.senac.games.dto.request.inscricao.InscricaoDTORequest;
import com.senac.games.dto.request.StatusUpdateDTORequest;
import com.senac.games.dto.request.inscricao.InscricaoUpdateDTORequest;
import com.senac.games.dto.request.inscricao.InscricaoUpdateJogoDTORequest;
import com.senac.games.dto.request.inscricao.InscricaoUpdateParticipanteDTORequest;
import com.senac.games.dto.response.inscricao.InscricaoDTOResponse;
import com.senac.games.dto.response.StatusUpdateDTOResponse;
import com.senac.games.dto.response.inscricao.InscricaoUpdateDTOResponse;
import com.senac.games.dto.response.inscricao.InscricaoUpdateJogoDTOResponse;
import com.senac.games.dto.response.inscricao.InscricaoUpdateParticipanteDTOResponse;
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
    public ResponseEntity<InscricaoUpdateDTOResponse> atualizarInscricao(
            @Valid
            @PathVariable("inscricaoId") Integer inscricaoId,
            @RequestBody InscricaoUpdateDTORequest dtoRequest
    ) {
        return ResponseEntity.ok(inscricaoService.atualizarInscricao(inscricaoId, dtoRequest));
    }

    @PutMapping("/atualizar/jogo{inscricaoId}")
    @Operation(summary="Atualizar jogo da inscricao", description = "Endpoint para atualizar o jogo na inscricao")
    public ResponseEntity<InscricaoUpdateJogoDTOResponse> atualizarJogo(
        @Valid @PathVariable("inscricaoId") Integer inscricaoId,
        @RequestBody InscricaoUpdateJogoDTORequest dtoRequest
    ){
      return ResponseEntity.ok(inscricaoService.alterarJogoInscricao(inscricaoId, dtoRequest));
    }

    @PutMapping("atualizar/participante/{inscricaoId}")
    @Operation(summary = "Atualizar participante da inscricao", description = "Endpoint para atualizar o participante na inscricao")
    public ResponseEntity<InscricaoUpdateParticipanteDTOResponse> atualizarParticipante(
        @Valid @PathVariable ("inscricaoId") Integer inscricaoId,
        @RequestBody InscricaoUpdateParticipanteDTORequest dtoRequest
    ) {
      return ResponseEntity.ok(inscricaoService.alterarParticipanteInscricao(inscricaoId, dtoRequest));
    }

    @PatchMapping("/atualizarStatus/{inscricaoId}")
    @Operation(summary = "Alterar Status da inscricao", description="Endpoint para alterar o status da inscricao")
    public ResponseEntity<StatusUpdateDTOResponse> atualizarStatus(
            @Valid
            @PathVariable("inscricaoId") Integer inscricaoId,
            @RequestBody StatusUpdateDTORequest statusUpdateDTORequest
    ){
        return ResponseEntity.ok(inscricaoService.atualizarStatusInscricao(inscricaoId, statusUpdateDTORequest));
    }

    @DeleteMapping("/remover/{inscricaoId}")
    @Operation(summary = "Remover Inscricao", description = "Endpoint para a remoção logica de inscricao")
    public ResponseEntity removerInscricao(
            @Valid @PathVariable("inscricaoId") Integer inscricaoId
    ){
        this.inscricaoService.apagarInscricao(inscricaoId);
        return ResponseEntity.noContent().build();
    }
}
