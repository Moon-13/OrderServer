//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.edu.jsnu.action;

import cn.edu.jsnu.bean.Order;
import cn.edu.jsnu.service.OrderServiceImp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public class OrderAction {
    private ApplicationContext ctx = null;
    private OrderServiceImp orderService = null;

    public OrderAction() {
    }

    public void init() {
        this.ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        this.orderService = (OrderServiceImp)this.ctx.getBean("orderService");
    }

    @ResponseBody
    @RequestMapping({"/insertOrder"})
    public Map<String, String> insertOrder(@RequestParam("user_id") int user_id, @RequestParam("drink_id") int drink_id, @RequestParam("num") int num, @RequestParam("sum") double sum, @RequestParam("suggesttime") String suggesttime) {
        this.init();
        Order order = new Order();
        order.setUser_id(user_id);
        order.setDrink_id(drink_id);
        order.setNum(num);
        order.setSum(sum);
        order.setSuggesttime(suggesttime);
        System.out.println(order);
        Map map = new HashMap();
        int result = this.orderService.insertOrder(order);
        map.put("success", String.valueOf(result));
        return map;
    }

    @ResponseBody
    @RequestMapping({"/insertComment"})
    public Map<String, String> insertComment(@RequestParam("order_id") int order_id, @RequestParam("content") String content) {
        this.init();
        Map map = new HashMap();
        int result = this.orderService.insertComment(order_id, content);
        map.put("success", String.valueOf(result));
        return map;
    }

    @ResponseBody
    @RequestMapping({"/updateComment"})
    public Map<String, String> updateComment(@RequestParam("order_id") int order_id, @RequestParam("content") String content) {
        this.init();
        System.out.println(content);
        Map map = new HashMap();
        int result = this.orderService.updateComment(order_id, content);
        map.put("success", String.valueOf(result));
        return map;
    }

    @ResponseBody
    @RequestMapping({"/deleteComment"})
    public Map<String, String> deleteComment(@RequestParam("order_id") int order_id) {
        this.init();
        Map map = new HashMap();
        int result = this.orderService.deleteComment(order_id);
        map.put("success", String.valueOf(result));
        return map;
    }

    @ResponseBody
    @RequestMapping({"/getAllUserOrder"})
    public List<Order> getAllUserOrder(@RequestParam("user_id") String user_id) {
        this.init();
        List<Order> result = this.orderService.getAllUserOrder(user_id);
        System.out.println(result);
        return result;
    }

    @ResponseBody
    @RequestMapping({"/getAllOrder"})
    public List<Order> getAllOrder() {
        this.init();
        List<Order> result = this.orderService.getAllOrder();
        return result;
    }

    @ResponseBody
    @RequestMapping({"/getAllUserDrinkOrder"})
    public List<Order> getAllUserDrinkOrder(@RequestParam("drink_id") String drink_id) {
        this.init();
        List<Order> result = this.orderService.getAllDrinkOrder(drink_id);
        return result;
    }

    @ResponseBody
    @RequestMapping({"/getAllUserComment"})
    public List<Order> getAllUserComment(@RequestParam("user_id") String user_id) {
        this.init();
        List<Order> result = this.orderService.getAllUserComment(user_id);
        return result;
    }

    @RequestMapping({"/orderManager"})
    public ModelAndView orderManager() {
        this.init();
        String forward = "ordermanager";
        return new ModelAndView(forward, (Map)null);
    }
}
