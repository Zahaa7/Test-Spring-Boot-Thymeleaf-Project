package ro.zah.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.zah.auction.dto.ProductDTO;
import ro.zah.auction.model.Auction;
import ro.zah.auction.model.Product;
import ro.zah.auction.service.AuctionService;
import ro.zah.auction.service.ProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {

    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    @Autowired
    private AuctionService auctionService;

    @Autowired
    private ProductService productService;

    @GetMapping("/admin")
    public String adminHome() {
        return "adminHome";
    }

    // Auction Section

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

    // Product Section

    @GetMapping("/admin/products")
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String getAddProducts(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("auctions", auctionService.getAllAuctions());
        return "addProducts";
    }

    @PostMapping("/admin/products/add")
    public String postAddProducts(@ModelAttribute("productDTO") ProductDTO productDTO,
                                  @RequestParam("productImage") MultipartFile file,
                                  @RequestParam("imgName") String imageName) throws IOException {
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setTitle(productDTO.getTitle());
        product.setAuthorName(productDTO.getAuthorName());
        product.setAuction(auctionService.editAuctionById(productDTO.getAuctionId()).get());
        product.setBasePrice(productDTO.getBasePrice());
        product.setDescription(productDTO.getDescription());
        String imageUUID;
        if (!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = imageName;
        }
        product.setProductImageURL(imageUUID);
        productService.addProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        productService.deleteProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String editProduct(@PathVariable long id, Model model) {
        Product product = productService.editProductById(id).get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setAuthorName(product.getAuthorName());
        productDTO.setTitle(product.getTitle());
        productDTO.setDescription(product.getDescription());
        productDTO.setBasePrice(product.getBasePrice());
        productDTO.setSettledPrice(product.getSettledPrice());
        productDTO.setBids(product.getBids());
        productDTO.setAuctionId(product.getAuction().getAuctionId());
        productDTO.setProductImageURL(product.getProductImageURL());
        model.addAttribute("auctions", auctionService.getAllAuctions());
        model.addAttribute("productDTO", productDTO);
        return "addProducts";
    }
}
