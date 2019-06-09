package first.lifesports.web.app.maps;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import first.lifesports.dao.AuthorityDAO;
import first.lifesports.dao.SampleDAO;
import first.lifesports.dao.app.map.AppMapsDAO;

@Service("AppMapsService")
public class AppMapsService{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource(name = "appMapsDAO")
	private AppMapsDAO appMapsDAO;

	public List<Map<String, Object>> foo(Map<String, Object> map) {
		try {
			return appMapsDAO.foo(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}