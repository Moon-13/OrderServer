package cn.edu.jsnu.action;

import cn.edu.jsnu.bean.Collection;
import cn.edu.jsnu.service.CollectServiceImp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public class CollectAction {
    private ApplicationContext ctx = null;
    private CollectServiceImp collectService = null;

    public CollectAction() {
    }

    public void init() {
        this.ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        this.collectService = (CollectServiceImp)this.ctx.getBean("collectService");
    }

    @ResponseBody
    @RequestMapping({"/getAllUserCollection"})
    public List<Collection> getAllUserCollection(@RequestParam("user_id") int user_id, @RequestParam("flag") int flag) {
        this.init();
        List<Collection> result = this.collectService.getAllUserCollection(user_id, flag);
        return result;
    }

    @ResponseBody
    @RequestMapping({"/userCollectDrink"})
    public Map<String, String> userCollectDrink(@RequestParam("user_id") int user_id, @RequestParam("drink_id") int drink_id) {
        Map map = new HashMap();
        this.init();
        int result = this.collectService.changeDrinkCollection(user_id, drink_id);
        map.put("success", String.valueOf(result));
        return map;
    }

    @ResponseBody
    @RequestMapping({"/userCollectShop"})
    public Map<String, String> userCollectShop(@RequestParam("user_id") int user_id, @RequestParam("shop_id") int shop_id) {
        Map map = new HashMap();
        this.init();
        int result = this.collectService.changeShopCollection(user_id, shop_id);
        map.put("success", String.valueOf(result));
        return map;
    }

    @ResponseBody
    @RequestMapping({"/isCollected"})
    public Map<String, String> isCollected(@RequestParam("user_id") int user_id, @RequestParam("shop_drink_id") int shop_drink_id, @RequestParam("flag") int flag) {
        Map map = new HashMap();
        this.init();
        int result = this.collectService.isCollected(user_id, shop_drink_id, flag);
        map.put("collected", String.valueOf(result));
        return map;
    }
}
