package com.care.root.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;
import com.care.root.mybatis.board.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired BoardMapper mapper;
	@Autowired BoardFileService fs;
	
	public Map<String, Object> boardAllList(int num){
		int pageLetter = 3; //몇 개 글
		int allCount = mapper.selectBoardCount(); //글 총 개수
		int repeat = allCount/pageLetter; //총 페이지 수
		if(allCount%pageLetter !=0) {//나머지 페이지가 있으면
			repeat++;
		}
		int end = num * pageLetter;
		int start = end + 1 - pageLetter;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("repeat", repeat);
		map.put("list", mapper.boardAllList(start, end));
		
		return map;
	}
	public String writeSave(BoardDTO dto, MultipartFile image_file_name) {
		if(image_file_name.isEmpty()) {//파일이 없는 경우
			dto.setImage_file_name("nan");
		}else{//파일이 존재하는 경우
			dto.setImage_file_name(fs.saveFile(image_file_name));
		}
		int result = mapper.writeSave(dto);
		String msg = "", url="";
		if(result==1) {//DB에 성공적으로 저장
			msg="새 글이 추가되었습니다";
			url="/root/board/boardAllList";
		}else {//DB에 저장 실패
			msg="문제가 발생했습니다";
			url="/root/board/writeForm";
		}
		return fs.getMessage(msg, url);
	}
	public BoardDTO contentView(int writeNo) {
		upHit(writeNo);
		return mapper.getContent(writeNo);
	}
	private void upHit(int writeNo) {
		mapper.upHit(writeNo);
	}
	public BoardDTO getContent(int writeNo) {
		return mapper.getContent(writeNo);
	}
	public String modify(BoardDTO dto, MultipartFile file) {
		String originName = null;
		if(!file.isEmpty()) { //수정됨
			originName = dto.getImage_file_name();
			dto.setImage_file_name(fs.saveFile(file));
		}
		int result = mapper.modify(dto);
		String msg = "", url="";
		if(result==1) {
			//기본 이미지 삭제 originName
			fs.deleteImage(originName);
			msg="수정되었습니다";
			url="/root/board/content_view?writeNo="+dto.getWriteNo();
		}else {
			//수정 이미지 삭제 dto.getImage_file_name()
			fs.deleteImage(dto.getImage_file_name());
			msg="문제 발생";
			url="/root/board/modify_form?writeNo="+dto.getWriteNo();
		}
		return fs.getMessage(msg, url);
	}
	public String delete(int writeNo, String fileName) {
		int result = mapper.delete(writeNo);
		String msg = "", url="";
		if(result==1) {
			fs.deleteImage(fileName);
			msg="삭제되었습니다";
			url="/root/board/boardAllList";
		}else {
			msg="문제 발생";
			url="/root/board/content_view?writeNo="+writeNo;
		}
		return fs.getMessage(msg, url);
	}
	public void addReply(BoardRepDTO dto) {
		mapper.addReply(dto);
	}
	public List<BoardRepDTO> getRepList(int write_group){
		return mapper.getRepList(write_group);
	}
}
