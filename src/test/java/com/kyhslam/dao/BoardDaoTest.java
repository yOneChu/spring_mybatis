package com.kyhslam.dao;

import com.kyhslam.domain.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardDaoTest {

    @Autowired BoardDao boardDao;


    @Test
    public void test() {
        List<Board> boards = boardDao.selectAll();

        for (Board board : boards) {
            System.out.println(board.getContent() + " :: " + board.getWriter());
        }

        System.out.println(boards.size());
    }


}