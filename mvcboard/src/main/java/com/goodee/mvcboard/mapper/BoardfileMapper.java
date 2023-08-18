package com.goodee.mvcboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.mvcboard.vo.Boardfile;

@Mapper
public interface BoardfileMapper {
	int deleteBoardfile(int boardNo);
	
	int deleteBoardfile2(int boardfileNo);
	
	int insertBoardfile(Boardfile boardfile);
	
	List<Boardfile> getSavefileName(int boardNo);
	
	List<Boardfile> getSavefileName2(int boardfileNo);
}
