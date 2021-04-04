package com.kyhslam.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Setter @Getter
public class Board {
    private String board_id;
    private String writer;
    private String content;
}
