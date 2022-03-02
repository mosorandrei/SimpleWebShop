package com.simplewebshop.models;

import java.io.*;
import java.util.*;

public class ProductCatalogUtils {
    public final static String catalog = "C:\\Users\\ROG\\Desktop\\java p\\SimpleWebShop\\src\\main\\java\\com\\" +
            "simplewebshop\\databases\\Catalog.txt";
    public final static String cart = "C:\\Users\\ROG\\Desktop\\java p\\SimpleWebShop\\src\\main\\java\\com\\" +
            "simplewebshop\\databases\\Cart.txt";
    public static List<Product> getProductsByCategory(String category) {
        List<Product> products = new ArrayList<>();
        try {
            File myObj = new File(ProductCatalogUtils.catalog);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(" ");
                if(!Objects.equals(data[1], category)) continue;
                Product temp = new Product();
                temp.setName(data[0]);
                temp.setCategory(category);
                products.add(temp);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading from database");
        }
        return products;
    }

    public static List<String> getCategories(){
        Set<String> categories = new HashSet<>();
        try {
            File myObj = new File(ProductCatalogUtils.catalog);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(" ");
                categories.add(data[1]);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading from database");
        }
        return new ArrayList<>(categories);
    }

    public static void addToCart(Product p)
    {
        try {
            File file = new File(ProductCatalogUtils.cart);
            FileWriter myWriter = new FileWriter(file,true);
            BufferedWriter writer = new BufferedWriter(myWriter);
            writer.write(p.getName()+" "+p.getCategory()+"\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to the database");
        }
    }

    public static List<Product> viewCart()
    {
        List<Product> cartProducts = new ArrayList<>();
        try {
            File myObj = new File(ProductCatalogUtils.cart);
            Scanner myCartReader = new Scanner(myObj);
            int ok=0;
            while (myCartReader.hasNextLine()) {
                if(ok == 0) {
                    ok++;
                    continue;
                }

                String[] data = myCartReader.nextLine().split(" ");
                if(data.length!=2) continue;
                Product temp = new Product();
                temp.setName(data[0]);
                temp.setCategory(data[1]);
                cartProducts.add(temp);
            }
            myCartReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading from database");
        }
        Product emptyProduct=new Product();
        emptyProduct.setName("No products in your cart");
        if(cartProducts.isEmpty())
            cartProducts.add(emptyProduct);
        return cartProducts;
    }
}
