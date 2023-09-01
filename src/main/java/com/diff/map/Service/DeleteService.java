package com.diff.map.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.diff.map.Mapper.DeleteMapper;

@Service
@Transactional
public class DeleteService {
	
	@Autowired
	DeleteMapper DeleteMapper;
	
	public void ChannelDelete(HashMap<String, String> map) throws Exception{
		DeleteMapper.ChannelDelete(map);
	}
}
