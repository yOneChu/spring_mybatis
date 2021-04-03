package com.kyhslam.controller;

import com.kyhslam.dao.BoardDao;
import com.kyhslam.domain.Board;
import com.kyhslam.domain.Criteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final BoardDao boardDao;


    @GetMapping("/hello")
    public String home(Model model) {

        Criteria criteria = new Criteria();
        criteria.setPage(2);
        criteria.setPerPageNum(10);

        List<Board> boards = boardDao.listCriteria(criteria);

        model.addAttribute("boards", boards);

        System.out.println(boards.size());
        return "hello";
    }
}
