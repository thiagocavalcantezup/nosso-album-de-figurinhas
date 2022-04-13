package com.zup.edu.gerenciadorde.albumdefigurinhas.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zup.edu.gerenciadorde.albumdefigurinhas.model.Album;
import com.zup.edu.gerenciadorde.albumdefigurinhas.model.AlbumDTO;
import com.zup.edu.gerenciadorde.albumdefigurinhas.model.FigurinhaDTO;
import com.zup.edu.gerenciadorde.albumdefigurinhas.repository.AlbumRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@SpringBootTest
@AutoConfigureMockMvc
class AlbumControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveCadastrarUmAlbumComFigurinhas() throws Exception {
        String descricao = "Album de Figurinhas do campeonato Brasileiro da temporada 2021,"
                + " com diversas estrelas venha colecionar e lembrar os melhores "
                + "elencos deste campeonato inesquecivel, colecione as figurinhas do seu time do coração.";

        FigurinhaDTO figurinhaDTO = new FigurinhaDTO(5, "Guilherme Arana do Atletico-MG");

        AlbumDTO albumDTO = new AlbumDTO("Brasileirao 2021", descricao, 87, List.of(figurinhaDTO));

        String payload = objectMapper.writeValueAsString(albumDTO);

        MockHttpServletRequestBuilder request = post("/albums")
                                                               .contentType(
                                                                   MediaType.APPLICATION_JSON
                                                               )
                                                               .content(payload);

        mockMvc.perform(request).andExpect(status().isCreated());

        List<Album> albums = albumRepository.findAll();

        assertFalse(albums.isEmpty());
        assertEquals(1, albums.size());
    }

}
