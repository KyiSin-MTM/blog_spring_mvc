package spring.blog.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * <h2>PropertyUtils Class</h2>
 * <p>
 * Process for Displaying PropertyUtils
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
public class PropertyUtils {

    /**
     * <h2>parseStringListProperty</h2>
     * <p>
     * 
     * </p>
     *
     * @param properties
     * @return
     * @return List<String>
     */
    public static List<String> parseStringListProperty(String properties) {
        List<String> datas = new ArrayList<>();
        if (!properties.contains(","))
            return datas;
        String[] propertyList = properties.split(",");
        for (String prop : propertyList) {
            datas.add(prop.trim());
        }
        return datas;
    }
}