package com.kyhslam.controller;

import com.kyhslam.dao.BoardDao;
import com.kyhslam.domain.Board;
import com.kyhslam.domain.Criteria;
import com.kyhslam.domain.PageMaker;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/hello2")
    public String home2(Criteria criteria, Model model) {
        //Criteria criteria = new Criteria();
        criteria.setPage(11);
        criteria.setPerPageNum(10);

        List<Board> boards = boardDao.listCriteria(criteria);

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(criteria);
        pageMaker.setTotalCount(200);


        log.info("Pagemaker == " + pageMaker);

        model.addAttribute("boards", boards);
        model.addAttribute("pageMaker", pageMaker);
        return "hello";
    }

    
    //페이징 처리 완료
    // 화면에서 페이지버튼 눌렀을때 링크
    @GetMapping("/listPage")
    public String listPage(Criteria cri, Model model) throws Exception {

        model.addAttribute("boards", boardDao.listCriteria(cri));

        log.info("LISTLISLSI");
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        pageMaker.setTotalCount(boardDao.selectTotal());
        model.addAttribute("pageMaker", pageMaker);
        return "hello";
    }

}
