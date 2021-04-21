package ink.gakkiyomi.webflux.r2dbc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.Generated;

/**
 * @author: fangcong
 * @description:
 * @create: Created by work on 2021-04-21 11:31
 **/
@Table("product")
public class Product implements Persistable<Integer> {

    @Id
    private Integer id;
    private String description;
    private Double price;

    @Transient
    private boolean newProduct;

    @Override
    @Transient
    public boolean isNew() {
        return this.newProduct || id == null;
    }

    public Product setAsNew() {
        this.newProduct = true;
        return this;
    }

    public Product() {
    }

    public Product(Integer id, String description, Double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
