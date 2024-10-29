package edu.miu.cs.cs489appsd.lab1a.productmgmtapp;

import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model.Product;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMgmtApp {

    public static void main(String[] args) {

        List<Product> products = Arrays.asList(
                new Product(3128874119L, "Banana", LocalDate.of(2023, 1, 24), 124, 0.55),
                new Product(2927458265L, "Apple", LocalDate.of(2023, 12, 9), 18, 1.09),
                new Product(9189927460L, "Carrot", LocalDate.of(2023, 3, 31), 89, 2.99)
        );

        ProductMgmtApp app = new ProductMgmtApp();
        app.printProducts(products);
    }

    public void printProducts(List<Product> products) {
        // Sort by product name using Stream API
        List<Product> sortedProducts = products.stream()
                .sorted((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()))
                .collect(Collectors.toList());

        // Print JSON format
        System.out.println("Printed in JSON Format");
        System.out.println(sortedProducts.stream()
                .map(product -> String.format("{ \"productId\": %d, \"name\": \"%s\", \"dateSupplied\": \"%s\", \"quantityInStock\": %d, \"unitPrice\": %.2f }",
                        product.getProductId(), product.getName(), product.getDateSupplied(), product.getQuantityInStock(), product.getUnitPrice()))
                .collect(Collectors.joining(",\n", "[\n", "\n]")));

        // Print XML format
        System.out.println("-----------------------------");
        System.out.println("Printed in XML Format");
        System.out.println("<?xml version=\"1.0\"?>");
        System.out.println("<products>");
        sortedProducts.forEach(product -> System.out.printf("  <product productId=\"%d\" name=\"%s\" dateSupplied=\"%s\" quantityInStock=\"%d\" unitPrice=\"%.2f\" />\n",
                product.getProductId(), product.getName(), product.getDateSupplied(), product.getQuantityInStock(), product.getUnitPrice()));
        System.out.println("</products>");

        // Print CSV format
        System.out.println("-----------------------------");
        System.out.println("Printed in Comma-Separated Value (CSV) Format");
        sortedProducts.forEach(product -> System.out.printf("%d, %s, %s, %d, %.2f\n",
                product.getProductId(), product.getName(), product.getDateSupplied(), product.getQuantityInStock(), product.getUnitPrice()));
    }
}
