package com.senac.games.controller;

import com.senac.games.dto.request.PatrocinadorDTORequest;
import com.senac.games.dto.request.StatusUpdateDTORequest;
import com.senac.games.dto.response.PatrocinadorDTOResponse;
import com.senac.games.dto.response.StatusUpdateDTOResponse;
import com.senac.games.entities.Patrocinador;
import com.senac.games.service.PatrocinadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patrocinador")
@Tag(name="Patrocinador", description="API para gerenciamento de patrocinadores")
public class PatrocinadorController {

    private final PatrocinadorService patrocinadorService;

    public PatrocinadorController(PatrocinadorService patrocinadorService) {
        this.patrocinadorService = patrocinadorService;

    }

    @PostMapping("/criar")
    @Operation(summary = "Criar nova patrocinador", description = "Endpoint para criar nova Patrocinador")
    public ResponseEntity<PatrocinadorDTOResponse> criarPatrocinador(@Valid @RequestBody PatrocinadorDTORequest patrocinador) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patrocinadorService.criarPatrocinador(patrocinador));
    }

    @GetMapping("/listar")
    @Operation(summary="Listar patrocinadors", description = "Endpoint para listar todos as patrocinadors")
    public ResponseEntity<List<Patrocinador>> listarPatrocinadors() {
        return ResponseEntity.ok(patrocinadorService.listarPatrocinadors());

    }

    @GetMapping("/listarPatrocinadorId/{patrocinadorId}")
    @Operation(summary = "Listar Patrocinador por Id", description="Endpoint para listar patrocinador ativo por ID")
    public ResponseEntity<Patrocinador> listarPatrocinadorPorId(@Valid @PathVariable("patrocinadorId") Integer patrocinadorId) {
        Patrocinador patrocinador = patrocinadorService.listarPatrocinadorPorId(patrocinadorId);

        if(patrocinador != null){
            return ResponseEntity.ok(patrocinadorService.listarPatrocinadorPorId(patrocinadorId));
        } else
            return ResponseEntity.noContent().build();
    }


    @PutMapping("/atualizar/{patrocinadorId}")
    @Operation(summary = "Atualizar patrocinador", description = "Endpoint para atualizar a patrocinador")
    public ResponseEntity<PatrocinadorDTOResponse> atualizarPatrocinador(
            @Valid
            @PathVariable("patrocinadorId") Integer patrocinadorId,
            @RequestBody PatrocinadorDTORequest patrocinadorDTORequest
    ) {
        return ResponseEntity.ok(patrocinadorService.atualizarPatrocinador(patrocinadorId, patrocinadorDTORequest));
    }

    @PatchMapping("/atualizarStatus/{patrocinadorId}")
    @Operation(summary = "Alterar Status da patrocinador", description="Endpoint para alterar o status da patrocinador")
    public ResponseEntity<StatusUpdateDTOResponse> atualizarStatus(
            @Valid
            @PathVariable("patrocinadorId") Integer patrocinadorId,
            @RequestBody StatusUpdateDTORequest statusUpdateDTORequest
    ){
        return ResponseEntity.ok(patrocinadorService.atualizarStatusPatrocinador(patrocinadorId, statusUpdateDTORequest));
    }

    @DeleteMapping("/remover/{patrocinadorId}")
    @Operation(summary = "Remover Patrocinador", description = "Endpoint para a remoção logica de patrocinador")
    public ResponseEntity removerPatrocinador(
            @Valid @PathVariable("patrocinadorId") Integer patrocinadorId
    ){
        this.patrocinadorService.apagarPatrocinador(patrocinadorId);
        return ResponseEntity.noContent().build();
    }

}
