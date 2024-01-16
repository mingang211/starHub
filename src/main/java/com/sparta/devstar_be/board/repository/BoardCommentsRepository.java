package com.sparta.devstar_be.board.repository;


import com.sparta.devstar_be.board.entity.BoardComments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentsRepository extends JpaRepository<BoardComments, Long> {
}
