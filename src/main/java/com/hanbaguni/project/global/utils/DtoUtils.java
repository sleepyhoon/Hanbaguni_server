package com.hanbaguni.project.global.utils;

import com.hanbaguni.project.domain.user.dto.BoardDto;

public class DtoUtils {
    public static boolean isValidDto(BoardDto boardDto) {
        if(boardDto.getTitle()==null){
            return false;
        }if(boardDto.getStaff()==null){
            return false;
        }if(boardDto.getLink()==null){
            return false;
        }if(boardDto.getQuantity()==null){
            return false;
        }if(boardDto.getRecruits()==null){
            return false;
        }if(boardDto.getPrice()==null){
            return false;
        }
        return true;
    }
}
