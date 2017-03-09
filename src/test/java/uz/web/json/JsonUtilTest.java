package uz.web.json;

import org.junit.Test;
import uz.UserTestData;
import uz.model.Menu;

import java.util.List;

import static uz.MenuTestData.MATCHER;
import static uz.MenuTestData.MENU1;
import static uz.MenuTestData.MENUS;

/**
 * Created by Admin on 28.02.2017.
 */
public class JsonUtilTest {
    @Test
    public void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(MENU1);
        System.out.println(json);
        Menu menu = JsonUtil.readValue(json, Menu.class);
        MATCHER.assertEquals(MENU1, menu);
    }

    @Test
    public void testReadWriteValues() throws Exception {
        String json = JsonUtil.writeValue(MENUS);
        System.out.println(json);
        List<Menu> menus = JsonUtil.readValues(json, Menu.class);
        MATCHER.assertCollectionEquals(MENUS, menus);
    }
}