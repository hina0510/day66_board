package com.care.root.mybatis.board;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;

public interface BoardMapper {
	public List<BoardDTO> boardAllList(@Param("s") int end, @Param("e") int start);
	//여러개의 값을 넘겨줄 때 @Param("s")를 사용한다
	public int writeSave(BoardDTO dto);
	public BoardDTO getContent(int writeNo);
	public void upHit(int writeNo);
	public int modify(BoardDTO dto);
	public int delete(int writeNo);
	public void addReply(BoardRepDTO dto);
	public List<BoardRepDTO> getRepList(int write_group);
	public int selectBoardCount();
}
