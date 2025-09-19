package com.senac.games.controller;


import com.senac.games.dto.request.PremioDTORequest;
import com.senac.games.dto.request.StatusUpdateDTORequest;
import com.senac.games.dto.response.PremioDTOResponse;
import com.senac.games.dto.response.StatusUpdateDTOResponse;
import com.senac.games.entities.Premio;
import com.senac.games.service.PremioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/premio")
@Tag(name="Premio", description="API para gerenciamento de premios")
public class PremioController {

    private final PremioService premioService;

    public PremioController(PremioService premioService) {
        this.premioService = premioService;

    }

    @PostMapping("/criar")
    @Operation(summary = "Criar nova premio", description = "Endpoint para criar nova Premio")
    public ResponseEntity<PremioDTOResponse> criarPremio(@Valid @RequestBody PremioDTORequest premio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(premioService.criarPremio(premio));
    }

    @GetMapping("/listar")
    @Operation(summary="Listar premios", description = "Endpoint para listar todos as premios")
    public ResponseEntity<List<Premio>> listarPremios() {
        return ResponseEntity.ok(premioService.listarPremios());

    }

    @GetMapping("/listarPremioId/{premioId}")
    @Operation(summary = "Listar Premio por Id", description="Endpoint para listar premio ativo por ID")
    public ResponseEntity<Premio> listarPremioPorId(@Valid @PathVariable("premioId") Integer premioId) {
        Premio premio = premioService.listarPremioPorId(premioId);

        if(premio != null){
            return ResponseEntity.ok(premioService.listarPremioPorId(premioId));
        } else
            return ResponseEntity.noContent().build();
    }


    @PutMapping("/atualizar/{premioId}")
    @Operation(summary = "Atualizar premio", description = "Endpoint para atualizar a premio")
    public ResponseEntity<PremioDTOResponse> atualizarPremio(
            @Valid
            @PathVariable("premioId") Integer premioId,
            @RequestBody PremioDTORequest premioDTORequest
    ) {
        return ResponseEntity.ok(premioService.atualizarPremio(premioId, premioDTORequest));
    }

    @PatchMapping("/atualizarStatus/{premioId}")
    @Operation(summary = "Alterar Status da premio", description="Endpoint para alterar o status da premio")
    public ResponseEntity<StatusUpdateDTOResponse> atualizarStatus(
            @Valid
            @PathVariable("premioId") Integer premioId,
            @RequestBody StatusUpdateDTORequest statusUpdateDTORequest
    ){
        return ResponseEntity.ok(premioService.atualizarStatusPremio(premioId, statusUpdateDTORequest));
    }

    @DeleteMapping("/remover/{premioId}")
    @Operation(summary = "Remover Premio", description = "Endpoint para a remoção logica de premio")
    public ResponseEntity removerPremio(
            @Valid @PathVariable("premioId") Integer premioId,
            @RequestBody PremioDTORequest premioDTORequest
    ){
        this.premioService.apagarPremio(premioId);
        return ResponseEntity.noContent().build();
    }

}
