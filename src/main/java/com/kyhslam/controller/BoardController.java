package com.kyhslam.controller;

import com.kyhslam.dao.BoardDao;
import com.kyhslam.domain.Board;
import com.kyhslam.domain.Criteria;
import com.kyhslam.domain.PageMaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardDao boardDao;

    @GetMapping("/board/list")
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


    // 페이징 처리 완료
    // 화면에서 페이지버튼 눌렀을때 링크
    @GetMapping("/board/listPage")
    //@RequestMapping(value = "/board/listPage", method = RequestMethod.GET)
    public String listPage(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {
        log.info("listPage :: " + cri.toString());

        model.addAttribute("boards", boardDao.listCriteria(cri));

        log.info("cri.getPage :: " + cri.getPage());
        log.info("cri.getPerPageNum :: " + cri.getPerPageNum());
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        pageMaker.setTotalCount(boardDao.selectTotal());
        model.addAttribute("cri", cri);
        model.addAttribute("pageMaker", pageMaker);

        log.info("pageMaker :: " + pageMaker.getCri().getPage());
        log.info("5555555555555555");
        //return "hello";
        return "board";
    }
}
