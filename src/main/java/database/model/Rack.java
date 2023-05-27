package database.model;

public class Rack {

    private final Integer productID;
    private final Integer weight;
    private final Integer positionX;
    private final Integer positionY;

    public Rack(Integer productID, Integer weight, Integer positionX, Integer positionY) {
        this.productID = productID;
        this.weight = weight;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Integer getProductID() {
        return productID;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }
}
