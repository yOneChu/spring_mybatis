package com.kyhslam.domain;

public class PageMaker {

    private int totalCount;
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;

    //화면에 보여지는 페이지번호의 숫자 개수
    private int displayPageNum = 10;

    private Criteria cri;

    public void setCri(Criteria cri) {
        this.cri = cri;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }

    public void calcData() {
        endPage = (int) (Math.ceil(cri.getPage() /
                (double) displayPageNum) * displayPageNum);

        startPage = (endPage - displayPageNum) + 1;
        int tempEndPage = (int) (Math.ceil( totalCount / (double) cri.getPerPageNum() ) );
        if(endPage >tempEndPage) {
            endPage = tempEndPage;
        }

        prev = startPage == 1 ? false : true;
        next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public void setDisplayPageNum(int displayPageNum) {
        this.displayPageNum = displayPageNum;
    }
}
