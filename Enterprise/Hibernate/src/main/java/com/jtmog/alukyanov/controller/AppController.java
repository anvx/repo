package com.jtmog.alukyanov.controller;

import com.jtmog.alukyanov.entity.Good;
import com.jtmog.alukyanov.entity.Order;
import com.jtmog.alukyanov.entity.User;
import com.jtmog.alukyanov.servise.GoodService;
import com.jtmog.alukyanov.servise.OrderService;
import com.jtmog.alukyanov.servise.UserService;
import com.jtmog.alukyanov.util.TestData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/")
@Controller
@SessionAttributes(names = {"currentUser", "bucket"})
public class AppController {
    private static final Logger logger = Logger.getLogger(AppController.class);

    private GoodService goodService;
    private OrderService orderService;
    private UserService userService;
    @Autowired
    private TestData testData;

    public AppController(GoodService goodService,
                         OrderService orderService,
                         UserService userService) {
        this.goodService = goodService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping(path = {"/", "online-shop"})
    public ModelAndView shop(@RequestParam(value = "error", required = false) String error,
                             @RequestParam(value = "logout", required = false) String logout,
                             Model model) {

        testData.inputTestData(); //create and input test data into DB

        ModelAndView modelAndView = new ModelAndView("shop");
        if (error != null) {
            modelAndView.addObject("error", "Invalid username or password!");
        }
        if (logout != null) {
            modelAndView.addObject("logout", "Your successfully logout!");
        }
        logger.info("Shop page");
        return modelAndView;
    }

    @GetMapping(path = "/order")
    public String order(Model model) {
        logger.info("GET request in order page");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User currentUser = userService.getUserByLogin(username);

        long currentUserId = currentUser.getId();

        if (orderService.getOrderByUserId(currentUserId) == null) {
            orderService.createOrder(currentUser);
        }

        model.addAttribute("currentUser", currentUser);

        model.addAttribute("goodsFromDataBase", goodService.getAllGood());

        List<Good> goods = orderService.getGoodByUserId(currentUserId);

        model.addAttribute("bucket", goods);

        orderService.updateTotalPriceInOrder(goods, currentUserId);

        return "order";
    }

    @PostMapping(path = "/order")
    public String orderPost(@SessionAttribute("currentUser") User user,
                            @RequestParam("goodId") long goodId,
                            Model model) {
        logger.info("POST request in order page");

        Order orderByUserId = orderService.getOrderByUserId(user.getId());
        orderService.addGoodToOrder(orderByUserId.getId(), goodId);
        return "redirect:order";
    }

    @GetMapping(path = "/checkout")
    public String getCheckout(@SessionAttribute(value = "currentUser") User currentUser,
                              Model model) {
        logger.info("GET request in checkout page");
        Order orderByUserId = orderService.getOrderByUserId(currentUser.getId());

        List<Good> goods = orderService.getGoodByUserId(currentUser.getId());

        model.addAttribute("bucket", goods);

        long totalPrice = orderService.readTotalPriceFromOrderDB(orderByUserId.getUser().getId());
        model.addAttribute("totalPrice", totalPrice);

        return "checkout";
    }
}
