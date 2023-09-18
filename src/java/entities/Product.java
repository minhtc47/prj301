/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author H
 */
public class Product {
    private int product_id,model_year,list_price;
    private String product_name,brand_name,category_name,image;

    public Product(int product_id, int model_year, int list_price, String product_name, String brand_name, String category_name) {
        this.product_id = product_id;
        this.model_year = model_year;
        this.list_price = list_price;
        this.product_name = product_name;
        this.brand_name = brand_name;
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return "Product{" + "product_id=" + product_id + ", model_year=" + model_year + ", list_price=" + list_price + ", product_name=" + product_name + ", brand_name=" + brand_name + ", category_name=" + category_name + ", image=" + image + '}';
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getModel_year() {
        return model_year;
    }

    public void setModel_year(int model_year) {
        this.model_year = model_year;
    }

    public int getList_price() {
        return list_price;
    }

    public void setList_price(int list_price) {
        this.list_price = list_price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
	
}
