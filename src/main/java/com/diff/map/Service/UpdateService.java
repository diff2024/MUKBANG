package com.diff.map.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diff.map.Mapper.UpdateMapper;

@Service
@Transactional
public class UpdateService {
	
	@Autowired
	UpdateMapper UpdateMapper;
	
	public void GeocodingUpdate(HashMap<String, String> map) throws Exception{
		UpdateMapper.GeocodingUpdate(map);
	}
	
	public void ChannelColorUpdate(HashMap<String, String> map) throws Exception{
		UpdateMapper.ChannelColorUpdate(map);
	}
}
