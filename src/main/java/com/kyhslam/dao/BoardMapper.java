package com.kyhslam.dao;

import com.kyhslam.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    public List<Board> selectAll();
}