package com.diff.map.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegMapper {
	public void UserReg(HashMap<String, String> map) throws Exception;
	public void UserChannelDefaultReg(HashMap<String, String> map) throws Exception;
}
