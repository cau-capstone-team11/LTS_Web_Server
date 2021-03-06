package first.lifesports.web.authority;

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
public class AuthorityController {
	
	@Resource(name="AuthorityService")
	AuthorityService authorityService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/login.do")
	public String loginView(HttpServletRequest request, Model model) {
	
	
		return "/authority/login";
	}
	
	@RequestMapping(value = "/logout.do")
	public String logout(HttpServletRequest request, Model model) {
	
		request.getSession().invalidate();
		
		return "redirect:/main.do";
	}
	
	@RequestMapping(value = "/loginProcess.do")
	public String updtTornament(HttpServletRequest request, Model model) {
		
		logger.info("ddsdsdsdsdd");
		
		Map reqMap = CommUtils.getRequestMap(request);
		
		logger.info("ddd");
		
		List<Map<String, Object>> res = authorityService.getUserInfo(reqMap);

		logger.info(reqMap.get("id").toString());
		logger.info(reqMap.get("password").toString());
		
		if (res.size() != 0) {
			String UDID = res.get(0).get("UDID").toString();
			String userId = res.get(0).get("id").toString();
			String name = (String) res.get(0).get("name");

			HttpSession session = request.getSession();
			session.setAttribute("UDID", UDID);
			session.setAttribute("id", userId);
			session.setAttribute("name", name);

			return "redirect:/main.do";
		} else {
			return "authority/login";
		}
	}
	
	//@RequestMapping(value = "/loginProcess.do", method=RequestMethod.POST, headers="Accept=*/*")
	//@ResponseBody
	/*
	 * public String loginProcess(HttpServletRequest request, @RequestBody String
	 * map) throws Exception {
	 * 
	 * logger.info("");
	 * 
	 * Map reqMap = CommUtils.convertJSONstringToMap(map);
	 * 
	 * //Map<String, Object> reqMap = new HashMap(); //reqMap.put("id",
	 * reqMap.get("id")); //reqMap.put("password", reqMap.get("password"));
	 * 
	 * List<Map<String, Object>> res = authorityService.getUserInfo(reqMap);
	 * 
	 * if(res.size() != 0) { String UDID = res.get(0).get("UDID").toString(); String
	 * userId = res.get(0).get("id").toString(); String name = (String)
	 * res.get(0).get("name");
	 * 
	 * HttpSession session = request.getSession(); session.setAttribute("UDID",
	 * UDID); session.setAttribute("id", userId); session.setAttribute("name",
	 * name);
	 * 
	 * return "redirect:/main.do"; } else { return "authority/login"; }
	 * 
	 * 
	 * }
	 */
	
	
	@RequestMapping(value = "/testAjax.do", method=RequestMethod.POST, headers="Accept=*/*",produces = "application/json")
	@ResponseBody
	public String testAjax(@RequestBody String map) throws Exception{
		
		Map reqMap = CommUtils.convertJSONstringToMap(map);
		reqMap.put("test", "star7357");
		//Server Call
		List<Map<String, Object>> res = authorityService.testMethod(reqMap);
		
		//Data Injection
		String id = (String) res.get(0).get("id");
		String name = (String) res.get(0).get("name");
		
		//Map maker
		Map<String, Object> resMap = new HashMap();
		resMap.put("id", id);
		resMap.put("name", name);
		
		return CommUtils.getJsonStringFromMap(resMap).toJSONString();
	}

}
