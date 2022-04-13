package com.zup.edu.gerenciadorde.albumdefigurinhas.controller;

import java.net.URI;

import javax.validation.Valid;

import com.zup.edu.gerenciadorde.albumdefigurinhas.model.Album;
import com.zup.edu.gerenciadorde.albumdefigurinhas.model.AlbumDTO;
import com.zup.edu.gerenciadorde.albumdefigurinhas.repository.AlbumRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(AlbumController.BASE_URI)
public class AlbumController {

    public final static String BASE_URI = "/albuns";

    private final AlbumRepository albumRepository;

    public AlbumController(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid AlbumDTO albumDTO,
                                       UriComponentsBuilder ucb) {
        Album album = albumRepository.save(albumDTO.toModel());

        URI location = ucb.path(BASE_URI + "/{id}").buildAndExpand(album.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Album album = albumRepository.findById(id)
                                     .orElseThrow(
                                         () -> new ResponseStatusException(
                                             HttpStatus.NOT_FOUND,
                                             "Não existe um álbum com o id fornecido."
                                         )
                                     );

        albumRepository.delete(album);

        return ResponseEntity.noContent().build();
    }

}
