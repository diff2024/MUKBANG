package com.diff.map.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReadMapper {
	public String UserID(HashMap<String, String> map) throws Exception;
	public String UserChannelYN(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> UserChannelList(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> UserRestaurantList(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> UserSettingChannelList(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> ChannelList(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> RestaurantList(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> MarkerList(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> GeocodingNonList() throws Exception;
}
