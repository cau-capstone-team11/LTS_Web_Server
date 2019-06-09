package first.lifesports.dao.app.map;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import first.common.dao.AbstractDAO;

@Repository("appMapsDAO")
public class AppMapsDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> foo(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("Maps.foo", map);
	}
}
