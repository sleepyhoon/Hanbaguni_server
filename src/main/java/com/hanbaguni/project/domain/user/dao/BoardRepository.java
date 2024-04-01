package com.hanbaguni.project.domain.user.dao;

import com.hanbaguni.project.domain.user.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {
    Optional<Board> findByTitle(String title);
}
