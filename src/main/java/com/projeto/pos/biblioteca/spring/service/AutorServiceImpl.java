package com.projeto.pos.biblioteca.spring.service;



import com.projeto.pos.biblioteca.spring.dto.AutorDTO;
import com.projeto.pos.biblioteca.spring.exception.ResourceNotFoundException;
import com.projeto.pos.biblioteca.spring.model.Autor;
import com.projeto.pos.biblioteca.spring.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do serviço de Autor
 */
@Service
@Transactional
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    private AutorDTO toDTO(Autor a) {
        AutorDTO dto = new AutorDTO();
        dto.setId(a.getId());
        dto.setNome(a.getNome());
        return dto;
    }

    @Override
    public AutorDTO create(AutorDTO dto) {
        Autor a = new Autor();
        a.setNome(dto.getNome());
        Autor saved = autorRepository.save(a);
        return toDTO(saved);
    }

    @Override
    public AutorDTO update(Long id, AutorDTO dto) {
        Autor a = autorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Autor", id));
        a.setNome(dto.getNome());
        return toDTO(autorRepository.save(a));
    }

    @Override
    public void delete(Long id) {
        if (!autorRepository.existsById(id)) throw new ResourceNotFoundException("Autor", id);
        autorRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public AutorDTO findById(Long id) {
        Autor a = autorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Autor", id));
        return toDTO(a);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AutorDTO> findAll() {
        return autorRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }
}
