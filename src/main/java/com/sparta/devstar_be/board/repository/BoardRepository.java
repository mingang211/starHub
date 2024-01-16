package com.sparta.devstar_be.board.repository;

import com.sparta.devstar_be.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("SELECT b FROM Board b LEFT JOIN FETCH b.comments WHERE b.id = :id")
    Optional<Board> findByIdWithComments(Long id);
}
