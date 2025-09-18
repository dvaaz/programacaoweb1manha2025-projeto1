package com.senac.games.controller;

import com.senac.games.dto.request.JogoDTORequest;
import com.senac.games.dto.request.JogoDTOUpdateStatusRequest;
import com.senac.games.dto.response.JogoDTOResponse;
import com.senac.games.dto.response.JogoDTOUpdateStatusResponse;
import com.senac.games.entities.Jogo;
import com.senac.games.service.JogoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jogo")
@Tag(name="Jogo", description="API para gerenciamento de jogos")
public class JogoController {

    private final JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;

    }

    @PostMapping("/criar")
    @Operation(summary = "Criar nova jogo", description = "Endpoint para criar nova Jogo")
    public ResponseEntity<JogoDTOResponse> criarJogo(@Valid @RequestBody JogoDTORequest jogo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jogoService.criarJogo(jogo));
    }

    @GetMapping("/listar")
    @Operation(summary="Listar jogos", description = "Endpoint para listar todos as jogos")
    public ResponseEntity<List<JogoDTOResponse>> listarJogos() {
        return ResponseEntity.ok(jogoService.listarJogos());
    }

    @GetMapping("/listarJogoId/{jogoId}")
    @Operation(summary = "Listar Jogo por Id", description="Endpoint para listar jogo ativo por ID")
    public ResponseEntity<JogoDTOResponse> listarJogoPorId(@Valid @PathVariable("jogoId") Integer jogoId) {
        return ResponseEntity.ok(jogoService.listarJogoPorId(jogoId));
    }

    @PutMapping("/atualizar/{jogoId}")
    @Operation(summary = "Atualizar jogo", description = "Endpoint para atualizar a jogo")
    public ResponseEntity<JogoDTOResponse> atualizarJogo(
            @Valid
            @PathVariable("jogoId") Integer jogoId,
            @RequestBody JogoDTORequest jogoDTORequest
    ) {
        return ResponseEntity.ok(jogoService.atualizarJogo(jogoId, jogoDTORequest));
    }

    @PatchMapping("/atualizarStatus/{jogoId}")
    @Operation(summary = "Alterar Status da jogo", description="Endpoint para alterar o status da jogo")
    public ResponseEntity<JogoDTOUpdateStatusResponse> atualizarStatus(
            @Valid
            @PathVariable("jogoId") Integer jogoId,
            @RequestBody JogoDTOUpdateStatusRequest jogoDTOUpdateStatusRequest
    ){
        return ResponseEntity.ok(jogoService.atualizarStatusJogo(jogoId, jogoDTOUpdateStatusRequest));
    }

    @DeleteMapping("/remover/{jogoId}")
    @Operation(summary = "Remover Jogo", description = "Endpoint para a remoção logica de jogo")
    public ResponseEntity removerJogo(
            @Valid @PathVariable("jogoId") Integer jogoId,
            @RequestBody JogoDTORequest jogoDTORequest
    ){
        this.jogoService.apagarJogo(jogoId);
        return ResponseEntity.noContent().build();
    }

}
