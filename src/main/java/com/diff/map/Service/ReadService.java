package com.diff.map.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.diff.map.Mapper.ReadMapper;
import com.diff.map.Mapper.RegMapper;
import com.diff.map.Mapper.UpdateMapper;

@Service
@Transactional
public class ReadService {
	
	@Autowired
	ReadMapper ReadMapper;
	
	@Autowired
	RegMapper RegMapper;
	
	@Autowired
	UpdateMapper UpdateMapper;
	
	public String UserID(HashMap<String, String> map) throws Exception{
		String UserID = ReadMapper.UserID(map);
		if(UserID.equals("")) {
			RegMapper.UserReg(map);
			UserID = ReadMapper.UserID(map);
		}
		
		HashMap<String, String> user_map = new HashMap<String, String>();
		user_map.put("user_id", UserID);
		
		String UserChannelYN = ReadMapper.UserChannelYN(user_map);
		
		if(UserChannelYN.equals("N")) {
			RegMapper.UserChannelDefaultReg(user_map);
		}
		
		//최근접속기록 Update
		UpdateMapper.UserRecentConnectUpdate(user_map);
		
		return UserID;
	}
	
	public List<HashMap<String, String>> UserChannelList(HashMap<String, String> map) throws Exception{
		return ReadMapper.UserChannelList(map);
	}
	
	public List<HashMap<String, String>> UserRestaurantList(HashMap<String, String> map) throws Exception{
		return ReadMapper.UserRestaurantList(map);
	}
	
	public List<HashMap<String, String>> UserSettingChannelList(HashMap<String, String> map) throws Exception{
		return ReadMapper.UserSettingChannelList(map);
	}
	
	public List<HashMap<String, String>> ChannelList(HashMap<String, String> map) throws Exception{
		return ReadMapper.ChannelList(map);
	}
	
	public List<HashMap<String, String>> RestaurantList(HashMap<String, String> map) throws Exception{
		return ReadMapper.RestaurantList(map);
	}
	
	public List<HashMap<String, String>> MarkerList(HashMap<String, String> map) throws Exception{
		return ReadMapper.MarkerList(map);
	}
	
	public List<HashMap<String, String>> GeocodingNonList() throws Exception{
		return ReadMapper.GeocodingNonList();
	}
}
