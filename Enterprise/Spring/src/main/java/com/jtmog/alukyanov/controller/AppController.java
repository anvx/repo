package com.jtmog.alukyanov.controller;

import com.jtmog.alukyanov.entity.Good;
import com.jtmog.alukyanov.entity.Order;
import com.jtmog.alukyanov.entity.User;
import com.jtmog.alukyanov.servise.GoodService;
import com.jtmog.alukyanov.servise.OrderGoodService;
import com.jtmog.alukyanov.servise.OrderService;
import com.jtmog.alukyanov.servise.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/")
@Controller
@SessionAttributes(names = {"currentUser", "bucket"})
public class AppController {
    private static final Logger logger = Logger.getLogger(AppController.class);

    private GoodService goodService;
    private OrderService orderService;
    private OrderGoodService orderGoodService;
    private UserService userService;

    @Autowired
    public AppController(GoodService goodService,
                         OrderService orderService,
                         OrderGoodService orderGoodService,
                         UserService userService) {
        this.goodService = goodService;
        this.orderService = orderService;
        this.orderGoodService = orderGoodService;
        this.userService = userService;
    }

    @GetMapping(path = {"/", "online-shop"})
    public String shop(@RequestParam(value = "error", required = false) String error,
                       @RequestParam(value = "logout", required = false) String logout,
                       Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password!");
        }
        if (logout != null) {
            model.addAttribute("logout", "Your successfully logout!");
        }
        logger.info("Shop page");
        return "shop";
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

        if (orderService.getOrderByUserId(currentUser.getId()) == null) {
            orderService.createOrder(currentUser.getId());
        }
        model.addAttribute("currentUser", currentUser);

        model.addAttribute("goodsFromDataBase", goodService.getAllGood());

        Order orderByUserId = orderService.getOrderByUserId(currentUser.getId());

        List<Good> goods = orderGoodService.readOrderGoodByOrderIdFromDB(orderByUserId.getId());
        model.addAttribute("bucket", goods);

        orderService.updateTotalPriceInOrder(goods, currentUser.getId());

        return "order";
    }

    @PostMapping(path = "/order")
    public String orderPost(@SessionAttribute("currentUser") User user,
                            @RequestParam("goodId") long goodId,
                            Model model) {
        logger.info("POST request in order page");

        Order orderByUserId = orderService.getOrderByUserId(user.getId());
        orderGoodService.createOrderGood(orderByUserId.getId(), goodId);
        return "redirect:order";
    }

    @GetMapping(path = "/checkout")
    public String getCheckout(@SessionAttribute(value = "currentUser") User currentUser,
                              Model model) {
        logger.info("GET request in checkout page");
        Order orderByUserId = orderService.getOrderByUserId(currentUser.getId());

        List<Good> goods = orderGoodService.readOrderGoodByOrderIdFromDB(orderByUserId.getId());
        model.addAttribute("bucket", goods);

        long totalPrice = orderService.readTotalPriceFromOrderDB(orderByUserId.getUserId());
        model.addAttribute("totalPrice", totalPrice);

        return "checkout";
    }
}
