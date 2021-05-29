//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.edu.jsnu.action;

import cn.edu.jsnu.bean.Shop;
import cn.edu.jsnu.service.ShopServiceImp;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

public class ShopAction {
    private ApplicationContext ctx = null;
    private ShopServiceImp shopService = null;

    public ShopAction() {
    }

    public void init() {
        this.ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        this.shopService = (ShopServiceImp)this.ctx.getBean("shopService");
    }

    @ResponseBody
    @RequestMapping({"/getAllShops"})
    public List<Shop> getAllShops() {
        this.init();
        List<Shop> shops = this.shopService.getAllShops();
        return shops;
    }

    @ResponseBody
    @RequestMapping({"/getAllShop"})
    public List<Shop> getAllShop() {
        this.init();
        List<Shop> shops = this.shopService.getAllShops();
        return shops;
    }

    @ResponseBody
    @RequestMapping({"/getShopById"})
    public Shop getShopById(@RequestParam("shop_id") String shop_id) {
        this.init();
        Shop shop = this.shopService.getShopById(shop_id);
        return shop;
    }

    @RequestMapping({"/shopManager"})
    public ModelAndView shopManager() {
        String forward = "shopmanager";
        return new ModelAndView(forward, (Map)null);
    }

    @RequestMapping({"/editShop"})
    public ModelAndView editShop(@RequestParam("shop_id") String shop_id) {
        this.init();
        Shop shop = this.shopService.getShopById(shop_id);
        Map map = new HashMap();
        map.put("shop", shop);
        String forward = "newshopmanager";
        return new ModelAndView(forward, map);
    }

    @ResponseBody
    @RequestMapping({"/insertShop"})
    public Map<String, String> insertShop(@RequestParam("shopname") String shopname, @RequestParam("address") String address, @RequestParam("phonenum") String phonenum, @RequestParam("level") int level, @RequestParam("pic") String pic, @RequestParam("intro") String intro) {
        Shop shop = new Shop();
        shop.setShopname(shopname);
        shop.setAddress(address);
        shop.setIntro(intro);
        shop.setLevel(level);
        shop.setPhonenum(phonenum);
        shop.setPic(pic);
        System.out.println(shop.toString());
        Map map = new HashMap();
        this.init();
        int result = this.shopService.insertShop(shop);
        map.put("success", String.valueOf(result));
        return map;
    }

    @ResponseBody
    @RequestMapping({"/updateShop"})
    public Map<String, String> updateShop(@RequestParam("shopname") String shopname, @RequestParam("address") String address, @RequestParam("phonenum") String phonenum, @RequestParam("level") int level, @RequestParam("pic") String pic, @RequestParam("intro") String intro, @RequestParam("shop_id") int shop_id) {
        System.out.println(shopname);
        Shop shop = new Shop();
        shop.setShop_id(shop_id);
        shop.setShopname(shopname);
        shop.setAddress(address);
        shop.setIntro(intro);
        shop.setLevel(level);
        shop.setPhonenum(phonenum);
        shop.setPic(pic);
        Map map = new HashMap();
        this.init();
        int result = this.shopService.updateShop(shop);
        map.put("success", String.valueOf(result));
        return map;
    }

    @ResponseBody
    @RequestMapping({"/deleteShop"})
    public Map<String, String> deleteShop(@RequestParam("shop_id") String shop_id) {
        System.out.println(shop_id);
        Map map = new HashMap();
        this.init();
        int result = this.shopService.deleteShop(shop_id);
        map.put("success", String.valueOf(result));
        return map;
    }

    @RequestMapping({"/uploadFile"})
    @ResponseBody
    public Map<String, String> uploadFile( HttpServletRequest request) throws IllegalStateException, IOException {
        System.out.println("uploadFile==" + request);
        Map map = new HashMap();
        int result = 0;

        try {
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            if (multipartResolver.isMultipart(request)) {
                System.out.println("==============");
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
                Iterator<String> iter = multiRequest.getFileNames();
                if (iter.hasNext()) {
                    MultipartFile file = multiRequest.getFile((String)iter.next());
                    String originalFileName = file.getOriginalFilename();
                    System.out.println(originalFileName);
                    String suffixString = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
                    Shop shop = new Shop();
                    shop.setShopname(request.getParameter("shopname"));
                    shop.setAddress(request.getParameter("address"));
                    shop.setIntro(request.getParameter("intro"));
                    shop.setLevel(Integer.parseInt(request.getParameter("level")));
                    shop.setPhonenum(request.getParameter("phonenum"));
                    shop.setPic(request.getParameter("pic"));
                    this.init();
                    result = this.shopService.insertShop(shop);
                    String fileName = shop.getPic();
                    String basepath = request.getSession().getServletContext().getRealPath("/");
                    String strdirection = "/upload/shop/";
                    File direction = new File(basepath + strdirection);
                    if (!direction.exists()) {
                        direction.mkdirs();
                    }

                    long t1 = System.currentTimeMillis();
                    String relative_path = strdirection + fileName + "." + suffixString;
                    String path = basepath + relative_path;

                    try {
                        File localFile = new File(path);
                        if (!localFile.exists()) {
                            localFile.createNewFile();
                        }

                        file.transferTo(localFile);
                    } catch (IllegalStateException var20) {
                        var20.printStackTrace();
                    } catch (IOException var21) {
                        var21.printStackTrace();
                    }
                }
            }
        } catch (Exception var22) {
        }

        map.put("success", String.valueOf(result));
        return map;
    }
}
