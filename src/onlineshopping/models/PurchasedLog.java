package onlineshopping.models;

import java.util.Date;

public record PurchasedLog(String purchaser, Product product, Date date) {
}
