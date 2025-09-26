package com.senac.games.controller;

import com.senac.games.entities.dto.request.CategoriaDTORequest;
import com.senac.games.entities.dto.request.CategoriaDTOUpdateStatusRequest;
import com.senac.games.entities.dto.response.CategoriaDTOResponse;
import com.senac.games.entities.dto.response.CategoriaDTOUpdateStatusResponse;
import com.senac.games.entities.Categoria;
import com.senac.games.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categoria")
@Tag(name="Categoria", description="API para gerenciamento de categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;

    }

    @PostMapping("/criar")
    @Operation(summary = "Criar nova categoria", description = "Endpoint para criar nova Categoria")
    public ResponseEntity<CategoriaDTOResponse> criarCategoria(@Valid @RequestBody CategoriaDTORequest categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.criarCategoria(categoria));
    }

    @GetMapping("/listar")
    @Operation(summary="Listar categorias", description = "Endpoint para listar todos as categorias")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarCategorias());

    }

    @GetMapping("/listarCategoriaId/{categoriaId}")
    @Operation(summary = "Listar Categoria por Id", description="Endpoint para listar categoria ativo por ID")
    public ResponseEntity<Categoria> listarCategoriaPorId(@Valid @PathVariable("categoriaId") Integer categoriaId) {
        Categoria categoria = categoriaService.listarCategoriaPorId(categoriaId);

        if(categoria != null){
            return ResponseEntity.ok(categoriaService.listarCategoriaPorId(categoriaId));
        } else
            return ResponseEntity.noContent().build();
    }


    @PutMapping("/atualizar/{categoriaId}")
    @Operation(summary = "Atualizar categoria", description = "Endpoint para atualizar a categoria")
    public ResponseEntity<CategoriaDTOResponse> atualizarCategoria(
            @Valid
            @PathVariable("categoriaId") Integer categoriaId,
            @RequestBody CategoriaDTORequest categoriaDTORequest
    ) {
        return ResponseEntity.ok(categoriaService.atualizarCategoria(categoriaId, categoriaDTORequest));
    }

    @PatchMapping("/atualizarStatus/{categoriaId}")
    @Operation(summary = "Alterar Status da categoria", description="Endpoint para alterar o status da categoria")
    public ResponseEntity<CategoriaDTOUpdateStatusResponse> atualizarStatus(
            @Valid
            @PathVariable("categoriaId") Integer categoriaId,
            @RequestBody CategoriaDTOUpdateStatusRequest categoriaDTOUpdateStatusRequest
    ){
        return ResponseEntity.ok(categoriaService.atualizarStatusCategoria(categoriaId, categoriaDTOUpdateStatusRequest));
    }

    @DeleteMapping("/remover/{categoriaId}")
    @Operation(summary = "Remover Categoria", description = "Endpoint para a remoção logica de categoria")
    public ResponseEntity removerCategoria(
            @Valid @PathVariable("categoriaId") Integer categoriaId,
            @RequestBody CategoriaDTORequest categoriaDTORequest
    ){
        this.categoriaService.apagarCategoria(categoriaId);
        return ResponseEntity.noContent().build();
    }

}
