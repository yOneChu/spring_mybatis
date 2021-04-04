package com.kyhslam.api;

import com.kyhslam.dao.BoardDao;
import com.kyhslam.domain.Board;
import com.kyhslam.domain.Criteria;
import com.kyhslam.domain.PageMaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardApiController {

    private final BoardDao boardDao;


    @GetMapping("/api/pageGo")
    public HashMap<String,Object> pageGo(@RequestParam("goPage") String goPage
                            ,@RequestParam("perPageNum") String perPageNum ) {
        log.info("goPage === " + goPage);
        HashMap<String,Object> resultMap = new HashMap<>();
        List<Board> resultList = new ArrayList<>();

        // 게시판 내용
        Criteria criteria = new Criteria();
        criteria.setPage(Integer.parseInt(goPage));
        criteria.setPerPageNum(Integer.parseInt(perPageNum));

        List<Board> boards = boardDao.listCriteria(criteria);

        resultMap.put("boardList", boards);

        // 페이징 번호
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(criteria);
        pageMaker.setTotalCount(boardDao.selectTotal());


        resultMap.put("prev", pageMaker.isPrev());
        resultMap.put("next", pageMaker.isNext());
        resultMap.put("startPage", pageMaker.getStartPage());
        resultMap.put("endPage", pageMaker.getEndPage());

        log.info("pageMaker.pre == " + pageMaker.isPrev());
        log.info("pageMaker.isNext == " + pageMaker.isNext());
        log.info("pageMaker.startPage == " + pageMaker.getStartPage());
        log.info("pageMaker.endPage == " + pageMaker.getEndPage());
        log.info("pageMaker.getCri().getPage() == " + pageMaker.getCri().getPage());
        //return boards;

        return resultMap;
    }

}
