package cn.edu.jsnu.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.jsnu.bean.Drink;
import cn.edu.jsnu.bean.DrinkType;
import cn.edu.jsnu.service.DrinkServiceImp;


public class DrinkAction {
    private ApplicationContext ctx = null;
    private DrinkServiceImp drinkService = null;

    public DrinkAction() {
    }

    public void init() {
        this.ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        this.drinkService = (DrinkServiceImp)this.ctx.getBean("drinkService");
    }

    @ResponseBody
    @RequestMapping({"/getDrinkByShop"})
    public List<Drink> getDrink(@RequestParam("shop_id") String shop_id) {
        this.init();
        List<Drink> list = this.drinkService.getDrinksByShop(shop_id);
        return list;
    }

    @ResponseBody
    @RequestMapping({"/getDrinkBySearch"})
    public List<Drink> getDrinkBySearch(@RequestParam("search") String search) {
        this.init();
        List<Drink> list = this.drinkService.getDrinksBySearch(search);
        System.out.println(list);
        return list;
    }

    @ResponseBody
    @RequestMapping({"/getDrinkType"})
    public List<DrinkType> getDrinkType() {
        this.init();
        List<DrinkType> list = this.drinkService.getDrinkType();
        return list;
    }

    @ResponseBody
    @RequestMapping({"/getDrinkById"})
    public Drink getDrinkById(@RequestParam("drink_id") String drink_id) {
        this.init();
        return this.drinkService.getDrinkById(drink_id);
    }

    @RequestMapping({"/drinkManager"})
    public ModelAndView drinkManager(@RequestParam("shop_id") String shop_id) {
        this.init();
        Map<String, Object> model = new HashMap();
        model.put("shop_id", shop_id);
        String forward = "drinkmanager";
        return new ModelAndView(forward, model);
    }

    @RequestMapping({"/newdrinkmanager"})
    public ModelAndView newdrinkmanager(@RequestParam("shop_id") String shop_id) {
        this.init();
        Map<String, Object> model = new HashMap();
        model.put("shop_id", shop_id);
        String forward = "newdrinkmanager";
        return new ModelAndView(forward, model);
    }

    @ResponseBody
    @RequestMapping({"/insertDrink"})
    public Map<String, String> insertDrink(@RequestParam("shop_id") String shop_id, @RequestParam("drinkname") String drinkname, @RequestParam("type_id") String type_id, @RequestParam("pic") String pic, @RequestParam("price") double price, @RequestParam("intro") String intro, @RequestParam("recommand") String recommand) {
        System.out.println(shop_id);
        Map map = new HashMap();
        this.init();
        int result = this.drinkService.insertDrink(shop_id, drinkname, type_id, pic, price, intro, recommand);
        map.put("success", String.valueOf(result));
        return map;
    }

    @RequestMapping({"/editDrink"})
    public ModelAndView editDrink(@RequestParam("drink_id") String drink_id, @RequestParam("shop_id") String shop_id) {
        this.init();
        Drink drink = this.drinkService.getDrinkById(drink_id);
        System.out.println(drink);
        Map map = new HashMap();
        map.put("drink", drink);
        map.put("shop_id", shop_id);
        System.out.println(drink.toString());
        String forward = "newdrinkmanager";
        return new ModelAndView(forward, map);
    }

    @ResponseBody
    @RequestMapping({"/updateDrink"})
    public Map<String, String> updateDrink(@RequestParam("drink_id") int drink_id, @RequestParam("drinkname") String drinkname, @RequestParam("type_id") int type_id, @RequestParam("pic") String pic, @RequestParam("price") double price, @RequestParam("intro") String intro, @RequestParam("recommand") int recommand) {
        System.out.println(drinkname);
        Drink drink = new Drink();
        drink.setDrink_id(drink_id);
        drink.setDrinkname(drinkname);
        drink.setIntro(intro);
        drink.setType_id(type_id);
        drink.setRecommand(recommand);
        drink.setPrice(price);
        drink.setPic(pic);
        Map map = new HashMap();
        this.init();
        int result = this.drinkService.updateDrink(drink);
        map.put("success", String.valueOf(result));
        return map;
    }

    @ResponseBody
    @RequestMapping({"/deleteDrink"})
    public Map<String, String> deleteDrink(@RequestParam("drink_id") String drink_id) {
        System.out.println(drink_id);
        Map map = new HashMap();
        this.init();
        int result = this.drinkService.deleteDrink(drink_id);
        map.put("success", String.valueOf(result));
        return map;
    }
}
