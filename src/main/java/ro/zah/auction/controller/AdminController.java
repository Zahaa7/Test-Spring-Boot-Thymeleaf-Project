package ro.zah.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ro.zah.auction.model.Auction;
import ro.zah.auction.service.AuctionService;

import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private AuctionService auctionService;

    @GetMapping("/admin")
    public String adminHome() {
        return "adminHome";
    }

    @GetMapping("/admin/auctions")
    public String getAuctions(Model model) {
        model.addAttribute("auctions", auctionService.getAllAuctions());
        return "auctions";
    }

    @GetMapping("/admin/auctions/add")
    public String getAddAuctions(Model model) {
        model.addAttribute("auction", new Auction());   // asta provine de pe th:object din addAuctions.html
        return "addAuctions";
    }

    @PostMapping("/admin/auctions/add")
    public String postAddAuctions(@ModelAttribute("auction") Auction auction) {
        auctionService.addAuction(auction);
        return "redirect:/admin/auctions";
    }

    @GetMapping("/admin/auctions/delete/{id}")
    public String deleteAuction(@PathVariable long id) {
        auctionService.deleteAuctionById(id);
        return "redirect:/admin/auctions";
    }

    @GetMapping("/admin/auctions/update/{id}")
    public String editAuction(@PathVariable long id, Model model) {
        Optional<Auction> auctionOptional = auctionService.editAuctionById(id);
        if (auctionOptional.isPresent()) {
            model.addAttribute("auction", auctionOptional.get());
            return "addAuctions";
        } else {
            return "404";
        }
    }
}
