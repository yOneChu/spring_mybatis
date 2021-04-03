package com.kyhslam.domain;

import lombok.Getter;
import lombok.Setter;

public class Criteria {
    private int page;
    private int perPageNum;
    
    public Criteria() {
        this.page = 1;
        this.perPageNum = 10;
    }

    public void setPage(int page) {
        if(page <= 0) {
            this.page = 1;
            return;
        }
        this.page = page;
    }

    public void setPerPageNum(int perPageNum) {
        // 0 이하이거나 100이상일 경우
        if(perPageNum <= 0 || perPageNum > 100) {
            this.perPageNum = 10;
            return;
        }
        
        this.perPageNum = perPageNum;
    }

    public int getPage() {
        return page;
    }

    // limit구문에서 시작위치를 지정할 때 사용
    // ex) 10개씩 출력하는 경우  3페이지의 경우 limit 20,10 과 같은 형태가 되어야 한다.
    public int getPageStart() {
        return (this.page - 1) * perPageNum;
    }

    // limit 뒤의 숫자를 의미해서 한페이지당 보여지는 개수르 의미한다
    public int getPerPageNum() {
        return perPageNum;
    }

    @Override
    public String toString() {
        return "Criteria [page=" + page + ", "
                    + "perPageNum=" + perPageNum + "]";
    }
}
