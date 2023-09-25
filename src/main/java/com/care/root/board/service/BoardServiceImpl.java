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
		int pageLetter = 3; //�� �� ��
		int allCount = mapper.selectBoardCount(); //�� �� ����
		int repeat = allCount/pageLetter; //�� ������ ��
		if(allCount%pageLetter !=0) {//������ �������� ������
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
		if(image_file_name.isEmpty()) {//������ ���� ���
			dto.setImage_file_name("nan");
		}else{//������ �����ϴ� ���
			dto.setImage_file_name(fs.saveFile(image_file_name));
		}
		int result = mapper.writeSave(dto);
		String msg = "", url="";
		if(result==1) {//DB�� ���������� ����
			msg="�� ���� �߰��Ǿ����ϴ�";
			url="/root/board/boardAllList";
		}else {//DB�� ���� ����
			msg="������ �߻��߽��ϴ�";
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
		if(!file.isEmpty()) { //������
			originName = dto.getImage_file_name();
			dto.setImage_file_name(fs.saveFile(file));
		}
		int result = mapper.modify(dto);
		String msg = "", url="";
		if(result==1) {
			//�⺻ �̹��� ���� originName
			fs.deleteImage(originName);
			msg="�����Ǿ����ϴ�";
			url="/root/board/content_view?writeNo="+dto.getWriteNo();
		}else {
			//���� �̹��� ���� dto.getImage_file_name()
			fs.deleteImage(dto.getImage_file_name());
			msg="���� �߻�";
			url="/root/board/modify_form?writeNo="+dto.getWriteNo();
		}
		return fs.getMessage(msg, url);
	}
	public String delete(int writeNo, String fileName) {
		int result = mapper.delete(writeNo);
		String msg = "", url="";
		if(result==1) {
			fs.deleteImage(fileName);
			msg="�����Ǿ����ϴ�";
			url="/root/board/boardAllList";
		}else {
			msg="���� �߻�";
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
