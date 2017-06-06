package common.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

public class MySessionContext {
	
	//声明公共的静态的map，用于存储用户的session信息
	public static Map<String, Map<String, HttpSession>> sessionMap = new HashMap<String, Map<String, HttpSession>>();
	
	//添加session
	public static void addSession(int userID, HttpSession session) {
		if (null != session && 0 != userID) {
			Map<String, HttpSession> m = new HashMap<String, HttpSession>();
			m.put(session.getId(), session);
			//把上面的map放进全局map中，key为用户ID，value为这个map对象
			sessionMap.put(userID + "", m);
		}
	}
	
	//获取session
	public static Map<String, HttpSession> findSession(int userID) {
		Map<String, HttpSession> m = null;
		if (0 != userID) {
			m = sessionMap.get(userID + "");
		}
		return m;
	}
	
	//销毁session
	public static void delSession(int userID, HttpSession session) {
		if (0 != userID) {
			Map<String, HttpSession> m = sessionMap.get(userID + "");
			if (null != m && 0 < m.size()) {
				Set<String> keySet = m.keySet();
				for (String string : keySet) {
					HttpSession httpSession = m.get(string);
					//当新用户ID一样并且sessionID与老的sessionID不相等的时候，销毁原来的
					if (!string.equals(session.getId())) {
						//把map中的session对象转成json字符串
						String json = new Gson().toJson(httpSession);
						System.out.println(json);
						//把这个json字符串转成jsonobject
						boolean isValid = new JsonParser().parse(json).getAsJsonObject()
						.get("session").getAsJsonObject()
						.get("isValid").getAsBoolean();
						if (isValid) {
							//销毁这个session对象
							httpSession.invalidate();
						}
					}
				}
				sessionMap.remove(userID + "");
			}
		}
	}

}
