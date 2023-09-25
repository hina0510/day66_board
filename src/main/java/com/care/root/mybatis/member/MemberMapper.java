package com.care.root.mybatis.member;

import java.util.List;
import java.util.Map;

import com.care.root.member.dto.MemberDTO;

public interface MemberMapper {
	public MemberDTO getMember(String id);
	public List<MemberDTO> getList();
	public void register(MemberDTO dto);
	public void modify(MemberDTO dto);
	public void keepLogin(Map<String, Object> map);
	public MemberDTO getUserSessionId(String sessionId);
}
