package spring.blog.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KyiSinShoonLaeLinn
 *
 */
public class PropertyUtils {
	
	/**
	 * @param properties
	 * @return
	 */
	public static List<String> parseStringListProperty(String properties){
        List<String> datas = new ArrayList<>();
        if(!properties.contains(",")) return datas;
        String[] propertyList = properties.split(",");
        for(String prop:propertyList) {
            datas.add(prop.trim());
        }
        return datas;
    }
}