package com.kyhslam.controller;

import com.kyhslam.dao.BoardDao;
import com.kyhslam.domain.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final BoardDao boardDao;


    @GetMapping("/home")
    public String home() {
        List<Board> boards = boardDao.selectAll();
        System.out.println(boards.size());
        return "home";
    }
}
