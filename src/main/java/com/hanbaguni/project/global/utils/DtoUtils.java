package com.hanbaguni.project.global.utils;

import com.hanbaguni.project.domain.user.dto.BoardDto;

public class DtoUtils {
    public static boolean isValidDto(BoardDto boardDto) {
        if(boardDto.getTitle()==null){
            return false;
        }else if(boardDto.getStaff()==null){
            return false;
        }else if(boardDto.getLink()==null){
            return false;
        }else if(boardDto.getQuantity()==null){
            return false;
        }else if(boardDto.getRecruits()==null){
            return false;
        }else if(boardDto.getPrice()==null){
            return false;
        }
        return true;
    }
}
