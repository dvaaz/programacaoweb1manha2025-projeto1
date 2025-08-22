package com.senac.games.controller;

import com.senac.games.dto.request.ParticipanteDTORequest;
import com.senac.games.dto.response.ParticipanteDTOResponse;
import com.senac.games.entities.Participante;
import com.senac.games.service.ParticipanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/participante")
@Tag(name="Participante", description="API para gerenciamento de participantes")
public class ParticipanteController {

    private final ParticipanteService participanteService;
    public ParticipanteController (ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    @GetMapping("/listar")
    @Operation(summary="Listar participantes", description = "Endpoint para listar todos os participantes")
    public ResponseEntity<List<Participante>> listarParticipantes(){
        return ResponseEntity.ok(participanteService.listarParticipantes());
    }

    @GetMapping("/listarPorParticipanteId/{participanteId}")
    @Operation(summary = "Lista participante por ID", description = "Endpoint para listar participante por ID")
    public ResponseEntity<Participante> listarPorParticipanteId(@PathVariable("participanteId") Integer participanteId) {
        Participante participante = participanteService.listarPorParticipanteId(participanteId);
        /**
         * Caso o participante não seja null retorna o Participante por participanteId
         */
        if(participante != null){
            return ResponseEntity.ok(participanteService.listarPorParticipanteId(participanteId));
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo participante", description = "Endpoint para criar um novo registro de participante")
    public ResponseEntity<ParticipanteDTOResponse> criarParticipante(@Valid @RequestBody ParticipanteDTORequest participante) {
        return ResponseEntity.status(HttpStatus.CREATED).body(participanteService.criarParticipante(participante));

    }
}
