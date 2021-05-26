package ro.zah.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ro.zah.auction.service.AuctionService;
import ro.zah.auction.service.ProductService;

@Controller
public class HomeController {

    @Autowired
    AuctionService auctionService;

    @Autowired
    ProductService productService;

    @GetMapping({"/", "/home"})
    public String homePage(Model model) {
        return "index";
    }

    @GetMapping("/session")
    public String auctionSession(Model model) {
        model.addAttribute("auctions", auctionService.getAllAuctions());
        model.addAttribute("products", productService.getAllProducts());
        return "session";
    }

    @GetMapping("/session/auction/{id}")
    public String auctionSessionByAuction(@PathVariable Long id, Model model) {
        model.addAttribute("auctions", auctionService.getAllAuctions());
        model.addAttribute("products", productService.getAllProductsByAuctionId(id));
        return "session";
    }

}
