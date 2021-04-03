package com.kyhslam.dao;

import com.kyhslam.domain.Board;
import com.kyhslam.domain.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    public List<Board> selectAll();
    public List<Board> listCriteria(Criteria criteria);
    public int selectTotal();
}
