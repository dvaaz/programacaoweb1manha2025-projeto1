package com.senac.games.service;

import com.senac.games.dto.request.CategoriaDTORequest;
import com.senac.games.dto.response.CategoriaDTOResponse;
import com.senac.games.entities.Categoria;
import com.senac.games.repository.CategoriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

  @Autowired
  private ModelMapper modelMapper;
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;

    }

    public List<Categoria> listarCategorias() {
        return this.categoriaRepository.findAll();

    }

  public CategoriaDTOResponse criarCategoria(CategoriaDTORequest categoriaDTORequest) {
      Categoria categoria = modelMapper.map(categoriaDTORequest, Categoria.class);
      Categoria categoriaSave = this.categoriaRepository.save(categoria);
      CategoriaDTOResponse categoriaDTOResponse = modelMapper.map(categoriaSave, CategoriaDTOResponse.class);

      return categoriaDTOResponse;
  }
}
