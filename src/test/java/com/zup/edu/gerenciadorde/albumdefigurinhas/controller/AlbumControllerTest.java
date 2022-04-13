package com.zup.edu.gerenciadorde.albumdefigurinhas.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zup.edu.gerenciadorde.albumdefigurinhas.model.Album;
import com.zup.edu.gerenciadorde.albumdefigurinhas.model.AlbumDTO;
import com.zup.edu.gerenciadorde.albumdefigurinhas.model.Figurinha;
import com.zup.edu.gerenciadorde.albumdefigurinhas.model.FigurinhaDTO;
import com.zup.edu.gerenciadorde.albumdefigurinhas.repository.AlbumRepository;
import com.zup.edu.gerenciadorde.albumdefigurinhas.repository.FigurinhaRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@SpringBootTest
@AutoConfigureMockMvc
class AlbumControllerTest {

    private final static String DESCRICAO = "Album de Figurinhas do campeonato Brasileiro da temporada 2021,"
            + " com diversas estrelas venha colecionar e lembrar os melhores "
            + "elencos deste campeonato inesquecivel, colecione as figurinhas do seu time do coração.";

    private final static FigurinhaDTO FIGURINHA_DTO = new FigurinhaDTO(
        5, "Guilherme Arana do Atletico-MG"
    );

    private final static AlbumDTO ALBUM_DTO = new AlbumDTO(
        "Brasileirao 2021", DESCRICAO, 87, Set.of(FIGURINHA_DTO)
    );

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private FigurinhaRepository figurinhaRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveCadastrarUmAlbumComFigurinhas() throws Exception {
        String payload = objectMapper.writeValueAsString(ALBUM_DTO);

        MockHttpServletRequestBuilder request = post(AlbumController.BASE_URI)
                                                                              .contentType(
                                                                                  APPLICATION_JSON
                                                                              )
                                                                              .content(payload);

        mockMvc.perform(request).andExpect(status().isCreated());

        List<Album> albums = albumRepository.findAll();
        List<Figurinha> figurinhas = figurinhaRepository.findAll();

        assertFalse(albums.isEmpty());
        assertFalse(figurinhas.isEmpty());
        assertEquals(1, albums.size());
        assertEquals(1, figurinhas.size());
    }

    @Test
    void deveRemoverUmAlbumComFigurinhas() throws Exception {
        String payload = objectMapper.writeValueAsString(ALBUM_DTO);

        MockHttpServletRequestBuilder request = post(AlbumController.BASE_URI)
                                                                              .contentType(
                                                                                  APPLICATION_JSON
                                                                              )
                                                                              .content(payload);
        MockHttpServletRequestBuilder deleteRequest = delete(AlbumController.BASE_URI + "/1");

        mockMvc.perform(request).andExpect(status().isCreated());
        mockMvc.perform(deleteRequest).andExpect(status().isNoContent());

        List<Album> albums = albumRepository.findAll();
        List<Figurinha> figurinhas = figurinhaRepository.findAll();

        assertTrue(albums.isEmpty());
        assertTrue(figurinhas.isEmpty());
        assertEquals(0, albums.size());
        assertEquals(0, figurinhas.size());
    }

}
