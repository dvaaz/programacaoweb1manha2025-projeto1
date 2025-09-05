package com.senac.games.service;

import com.senac.games.dto.request.CategoriaDTORequest;
import com.senac.games.dto.request.CategoriaDTOUpdateStatusRequest;
import com.senac.games.dto.response.CategoriaDTOResponse;
import com.senac.games.dto.response.CategoriaDTOUpdateStatusResponse;
import com.senac.games.entities.Categoria;
import com.senac.games.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final  CategoriaRepository categoriaRepository;
    @Autowired
    private ModelMapper modelMapper;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public CategoriaDTOResponse criarCategoria(CategoriaDTORequest categoriaDTORequest) {
        Categoria categoria = modelMapper.map(categoriaDTORequest, Categoria.class);

        Categoria categoriaSave = this.categoriaRepository.save(categoria);
        CategoriaDTOResponse categoriaDTOResponse = modelMapper.map(categoriaSave, CategoriaDTOResponse.class);

        return categoriaDTOResponse;
    }

    public List<Categoria> listarCategorias() { return this.categoriaRepository.listarCategorias(); }

    public Categoria listarCategoriaPorId(Integer categoriaId) {
        Categoria categoria = this.categoriaRepository.obterCategoriaPorID(categoriaId);
        if (categoria != null) {
            return this.categoriaRepository.obterCategoriaPorID(categoriaId);
        } else return null;
    }

    @Transactional
    public CategoriaDTOUpdateStatusResponse atualizarStatusCategoria(Integer categoriaId, CategoriaDTOUpdateStatusRequest categoriaDTOUpdateStatusRequest) {
        Categoria categoria = this.listarCategoriaPorId(categoriaId);
        if (categoria != null) {
            categoria.setStatus(categoriaDTOUpdateStatusRequest.getStatus());

            Categoria tempResponse = categoriaRepository.save(categoria);
            return modelMapper.map(tempResponse, CategoriaDTOUpdateStatusResponse.class);
        } else return null;
    }
    @Transactional
    public CategoriaDTOResponse atualizarCategoria(Integer categoriaId, CategoriaDTORequest categoriaDTORequest) {
    Categoria categoria = this.listarCategoriaPorId(categoriaId);

    if (categoria != null) {
        modelMapper.map(categoriaDTORequest, categoria);
        Categoria tempResponse = categoriaRepository.save(categoria);
        return modelMapper.map(tempResponse, CategoriaDTOResponse.class);

    } else return null;
    }
    @Transactional
    public void apagarCategoria(Integer categoriaId){
        this.categoriaRepository.apagarLogicoCategoria(categoriaId);
    }
}
