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

import com.diff.map.Service.ReadService;
import com.diff.map.Service.UpdateService;

@RestController
@RequestMapping("/Read")
public class ReadController {

	@Autowired
	ReadService ReadService;
	
	@Autowired
	UpdateService UpdateService;
	
	@PostMapping(path = "/UserID")
	public String UserID(HttpServletRequest req) throws Exception {
		String android_id = (req.getParameter("android_id")==null)?"":req.getParameter("android_id");
		String ios_id = (req.getParameter("ios_id")==null)?"":req.getParameter("ios_id");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("android_id", android_id);
		map.put("ios_id", ios_id);
		
		return ReadService.UserID(map);
	}
	
	@PostMapping(path = "/UserChannelList")
	public List<HashMap<String, String>> UserChannelList(HttpServletRequest req) throws Exception {
		String user_id = (req.getParameter("user_id")==null)?"":req.getParameter("user_id");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("user_id", user_id);
		
		return ReadService.UserChannelList(map);
	}
	
	@PostMapping(path = "/UserRestaurantList")
	public List<HashMap<String, String>> UserRestaurantList(HttpServletRequest req) throws Exception {
		String user_id = (req.getParameter("user_id")==null)?"":req.getParameter("user_id");
		String channel_id = (req.getParameter("channel_id")==null)?"":req.getParameter("channel_id");
		
		channel_id = "'" + (channel_id.toUpperCase()).replaceAll(",", "','") + "'";
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("user_id", user_id);
		map.put("channel_id", channel_id);
		
		return ReadService.UserRestaurantList(map);
	}
	
	@PostMapping(path = "/UserSettingChannelList")
	public List<HashMap<String, String>> UserSettingChannelList(HttpServletRequest req) throws Exception {
		String user_id = (req.getParameter("user_id")==null)?"":req.getParameter("user_id");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("user_id", user_id);
		
		return ReadService.UserSettingChannelList(map);
	}
	
	@GetMapping(path = "/ChannelList")
	public List<HashMap<String, String>> ChannelList(HttpServletRequest req) throws Exception {
		String channel_id = (req.getParameter("channel_id")==null)?"":req.getParameter("channel_id");
		if(!channel_id.equals("")) {
			channel_id = "'" + channel_id.replaceAll(",", "','") + "'";
		}
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("channel_id", channel_id);
		
		return ReadService.ChannelList(map);
	}
	
	@GetMapping(path = "/RestaurantList")
	public List<HashMap<String, String>> RestaurantList(HttpServletRequest req) throws Exception {
		String restaurant_id = (req.getParameter("restaurant_id")==null)?"":req.getParameter("restaurant_id");
		if(!restaurant_id.equals("")) {
			restaurant_id = "'" + restaurant_id.replaceAll(",", "','") + "'";
		}
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("restaurant_id", restaurant_id);
		
		return ReadService.RestaurantList(map);
	}
	
	@GetMapping(path = "/MarkerList")
	public List<HashMap<String, String>> MarkerList(HttpServletRequest req) throws Exception {
		String channel_id = (req.getParameter("channel_id")==null)?"":req.getParameter("channel_id");
		if(!channel_id.equals("")) {
			channel_id = "'" + channel_id.replaceAll(",", "','") + "'";
		}
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("channel_id", channel_id);
		
		return ReadService.MarkerList(map);
	}
	
	@GetMapping(path = "/Geocoding_Search")
	public String Geocoding_Search(HttpServletRequest req) throws Exception {
		String address = (req.getParameter("address")==null)?"":req.getParameter("address");
		
		if(address.equals("")) {
			return "입력된 주소가 없습니다.";
		} else {
			String return_value = "";
			address = URLEncoder.encode(address, "UTF-8");

			String clientId = "mbc3h7xajq";
			String clientSecret = "kMMbJldxO7aPO7rSUw3fCT0HmHQukquBsFPKDSDw";
			
			String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + address;	// JSON
			
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			// Geocoding 개요에 나와있는 요청 헤더 입력.
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
			
			// 요청 결과 확인. 정상 호출인 경우 200
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			
			JSONTokener tokener = new JSONTokener(response.toString());
			JSONObject object = new JSONObject(tokener);			
			JSONArray arr = object.getJSONArray("addresses");
			
			for (int i = 0; i < arr.length(); i++) {
				JSONObject temp = (JSONObject) arr.get(i);
				return_value = "주소 : " + temp.get("roadAddress") + "<br>";
				return_value += "지번주소 : " + temp.get("jibunAddress") + "<br>";
				return_value += "위도 : " + temp.get("y") + "<br>";
				return_value += "경도 : " + temp.get("x");
				// System.out.println("address : " + temp.get("roadAddress"));
				// System.out.println("jibunAddress : " + temp.get("jibunAddress"));
				// System.out.println("위도 : " + temp.get("y"));
				// System.out.println("경도 : " + temp.get("x"));
			}
			return return_value;
		}
	}
	@GetMapping(path = "/GeocodingUpdate")
	public String GeocodingUpdate(HttpServletRequest req) throws Exception {
		List<HashMap<String, String>> list = ReadService.GeocodingNonList();
		
		int ListCount = list.size();
		int SuccessCount = 0;
		for(int i=0; i<list.size(); i++) {
			String _restaurant_id = list.get(i).get("_restaurant_id");
			String _restaurant_address = list.get(i).get("_restaurant_address");
			String _restaurant_latitude = "";
			String _restaurant_longitude = "";
			
			String address = URLEncoder.encode(_restaurant_address, "UTF-8");

			String clientId = "mbc3h7xajq";
			String clientSecret = "kMMbJldxO7aPO7rSUw3fCT0HmHQukquBsFPKDSDw";
			
			String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + address;	// JSON
			
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			// Geocoding 개요에 나와있는 요청 헤더 입력.
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
			
			// 요청 결과 확인. 정상 호출인 경우 200
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			
			JSONTokener tokener = new JSONTokener(response.toString());
			JSONObject object = new JSONObject(tokener);			
			JSONArray arr = object.getJSONArray("addresses");
			
			for (int x = 0; x < arr.length(); x++) {
				JSONObject temp = (JSONObject) arr.get(x);
				_restaurant_latitude = (String) temp.get("y");
				_restaurant_longitude = (String) temp.get("x");
			}
			
			if(!_restaurant_latitude.equals("") && !_restaurant_longitude.equals("")) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("_restaurant_id", _restaurant_id);
				map.put("_restaurant_latitude", _restaurant_latitude);
				map.put("_restaurant_longitude", _restaurant_longitude);
				UpdateService.GeocodingUpdate(map);
				SuccessCount++;
			}
		}
		
		return "<업데이트 내역><br>전체조회 : " + Integer.toString(ListCount) + "<br>" + "업데이트된 위도·경도 : " + Integer.toString(SuccessCount);
	}
}
