package com.example.mvc;

import com.sun.net.httpserver.Headers;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

class CartItem {
    private String productName;
    private int price;
    private int quantity;

    public CartItem() {

    }

    public CartItem(String productName, int price, int quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class AddCartItemResult {
    private String status;
    private String message;
    private String productName;

    public AddCartItemResult(String productName) {
        this.status = "success";
        this.message = "상품이 장바구니에 추가되었습니다.";
        this.productName = productName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}

class CartItems {
    private ArrayList<CartItemInfo> items;
    private int totalItems;
    private int cartTotal;

    public CartItems() {
        this.totalItems = 0;
        this.cartTotal = 0;
        this.items = new ArrayList<>();
    }

    public void addCartItem(CartItemInfo cartItemInfo) {
        this.items.add(cartItemInfo);

        this.totalItems = 0;
        this.cartTotal = 0;

        for(CartItemInfo data : this.items) {
            this.totalItems += data.getQuantity();
            this.cartTotal += data.getTotalPrice();
        }
    }

    public ArrayList<CartItemInfo> getItems() {
        return items;
    }

    public void setItems(ArrayList<CartItemInfo> items) {
        this.items = items;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(int cartTotal) {
        this.cartTotal = cartTotal;
    }
}

class CartItemInfo extends CartItem {
    private int totalPrice;

    public CartItemInfo(String priceName, int price, int quantity) {
        super(priceName, price, quantity);
        this.totalPrice = super.getPrice() * super.getQuantity();
    }

    public int getTotalPrice() {
        return this.totalPrice;
    }
}

@RestController
@RequestMapping("/cart")
public class CartController {
    CartItems cartItems = new CartItems();

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity addCartItem(@RequestBody CartItem data) {
        AddCartItemResult addCartItemResult = new AddCartItemResult(data.getProductName());
        CartItemInfo cartItemInfo = new CartItemInfo(data.getProductName(), data.getPrice(), data.getQuantity());
        this.cartItems.addCartItem(cartItemInfo);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity(addCartItemResult, header, HttpStatus.CREATED);
    }

    @GetMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity returnCartItems() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity(this.cartItems, header, HttpStatus.OK);
    }
}
