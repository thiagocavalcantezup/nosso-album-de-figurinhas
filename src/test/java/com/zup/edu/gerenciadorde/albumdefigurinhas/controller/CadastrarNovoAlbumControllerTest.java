package com.zup.edu.gerenciadorde.albumdefigurinhas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zup.edu.gerenciadorde.albumdefigurinhas.AlbumDeFigurinhasApplication;
import com.zup.edu.gerenciadorde.albumdefigurinhas.controller.request.AlbumComFigurinhaRequest;
import com.zup.edu.gerenciadorde.albumdefigurinhas.controller.request.FigurinhaRequest;
import com.zup.edu.gerenciadorde.albumdefigurinhas.model.Album;
import com.zup.edu.gerenciadorde.albumdefigurinhas.repository.AlbumRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CadastrarNovoAlbumControllerTest {
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private AlbumRepository repository;
    @Autowired
    private MockMvc mockMvc;


    @Test
    void deveCadastrarUmAlbumComFigurinhas() throws Exception {
        String descricao = "Album de Figurinhas do campeonato Brasileiro da temporada 2021," +
                " com diversas estrelas venha colecionar e lembrar os melhores " +
                "elencos deste campeonato inesquecivel, colecione as figurinhas do seu time do coração.";

        FigurinhaRequest figurinhaRequest = new FigurinhaRequest(5, "Guilherme Arana do Atletico-MG");

        AlbumComFigurinhaRequest albumComFigurinhaRequest = new AlbumComFigurinhaRequest("Brasileirao 2021", descricao, 87, List.of(figurinhaRequest));

        String payload = mapper.writeValueAsString(albumComFigurinhaRequest);

        MockHttpServletRequestBuilder request = post("/albums")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload);

        mockMvc.perform(request)
                .andExpect(
                        status().isCreated()
                );

        List<Album> albums = repository.findAll();

        assertFalse(albums.isEmpty());
        assertEquals(1, albums.size());
    }
}