package com.diff.map.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeleteMapper {
	public void ChannelDelete(HashMap<String, String> map) throws Exception;
}
