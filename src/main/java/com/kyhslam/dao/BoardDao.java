package com.kyhslam.dao;

import com.kyhslam.domain.Board;
import com.kyhslam.domain.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDao {
    protected static final String NAMESPACE = "com.kyhslam.dao.BoardMapper";

    @Autowired private SqlSession sqlSession;

    public List<Board> selectAll(){
        return sqlSession.selectList(NAMESPACE + ".selectAll");
    }

    public List<Board> listCriteria(Criteria criteria) {
        return sqlSession.selectList(NAMESPACE + ".listCriteria", criteria);
    }

    public int selectTotal() {
        return sqlSession.selectOne(NAMESPACE + ".selectTotal");
    }
}
