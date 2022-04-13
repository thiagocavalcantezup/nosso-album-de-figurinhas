package com.zup.edu.gerenciadorde.albumdefigurinhas.repository;

import com.zup.edu.gerenciadorde.albumdefigurinhas.model.Album;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {

}
