package ua.nure.patsera.periodicals.dto;

import ua.nure.patsera.periodicals.dao.BaseEntity;

/**
 * Created by Дарина on 16.09.2017.
 */
public class PeriodicalDto extends BaseEntity<Integer> {
    private String name;
    private String category;
    private double price;
    private String photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

}
