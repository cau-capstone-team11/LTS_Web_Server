package first.lifesports.web.app.maps;

import java.text.DateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jackson.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import first.utils.CommUtils;
import net.minidev.json.JSONObject;
import net.sf.json.JSONArray;
import first.lifesports.web.main.HomeController;;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AppMapsController {
	
	@Resource(name="AppMapsService")
	AppMapsService appMapsService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/*
	 * @RequestMapping(value = "/reactMapRequest.do") public String
	 * loginView(HttpServletRequest request, Model model) {
	 * 
	 * appMapsService.foo(null);
	 * 
	 * return "/authority/login"; }
	 */
	
	/*
	 * Ajax Request for schedule
	 */
	@RequestMapping(value = "/reactMapRequest.do", method = RequestMethod.POST, headers = "Accept=*/*", produces = "application/json")
	@ResponseBody
	public String scheduleRequestAjax(@RequestBody String map) throws Exception {

		
		  Map<String, Object> reqMap = CommUtils.convertJSONstringToMap(map);
		  
		  // Server Call List<Map<String, Object>> resMap2 =
		  List<Map<String, Object>> resMap2 = appMapsService.foo(reqMap);
		  
		/* String res = resMap2.get(0).get("name").toString(); */
		  
		  
		  // Map maker 
		 Map<String, Object> resMap = new HashMap();
		 
		resMap.put("resultList", "test");
		resMap.put("resMap", resMap2);

		return CommUtils.getJsonStringFromMap(resMap).toJSONString();
	}
}
