package com.diff.map.Controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.catalina.valves.rewrite.InternalRewriteMap.UpperCase;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.simple.parser.JSONParser;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.diff.map.Service.DeleteService;

@RestController
@RequestMapping("/Delete")
public class DeleteController {

	@Autowired
	DeleteService DeleteService;
	
	@PostMapping(path = "/ChannelDelete")
	public void ChannelColorUpdate(HttpServletRequest req) throws Exception {
		String user_id = (req.getParameter("user_id")==null)?"":req.getParameter("user_id");
		String channel_id = (req.getParameter("channel_id")==null)?"":req.getParameter("channel_id");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("user_id", user_id);
		map.put("channel_id", channel_id);
		DeleteService.ChannelDelete(map);
	}
}
