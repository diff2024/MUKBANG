package com.diff.map.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UpdateMapper {
	public void GeocodingUpdate(HashMap<String, String> map) throws Exception;
	public void UserRecentConnectUpdate(HashMap<String, String> map) throws Exception;
	public void ChannelColorUpdate(HashMap<String, String> map) throws Exception;
}
