import java.io.Serializable;

public class Lure implements Updater, Comparable<Lure>, Serializable {
    private static final long serialVersionUID = 1L;
    private int quantity;
    private String brand;
    private double weight;
    private double length;
    private String color;
    private String name;
    public Lure(int quantity, String brand, double weight,
                double length, String color, String name) throws InvalidInputException {
        if (quantity < 1)
            throw new InvalidInputException("Invalid quantity");
        else
            this.quantity = quantity;
        this.brand = brand;
        if (weight < 0) {
            throw new InvalidInputException("Invalid weight");
        }
        else
            this.weight = weight;
        if (length < 0) {
            throw new InvalidInputException("Invalid lenth");
        }
        else
            this.length = length;
        this.color = color;
        this.name = name;
    }
    public Lure(int quantity, String brand, double length, String color, String name) throws InvalidInputException {
        this(quantity, brand, 0, length, color, name);
    }
    public Lure(String brand, String color, double weight, int quantity, String name) throws InvalidInputException {
        this(quantity, brand, weight, 0, color, name);
    }
    public int getQuantity() {
        return this.quantity;
    }
    public String getBrand() {
        return this.brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getColor() {
        return this.color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public double getLength() {
        return this.length;
    }
    public void setLength(double length) {
        if (length > 0)
            this.length = length;
    }
    public double getWeight() {
        return this.weight;
    }
    public void setWeight(double weight) {
        if (weight >= 0)
            this.weight = weight;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean equals(Object other) {
        if (!(other instanceof Lure))
            return false;
        Lure otro = (Lure) other;
        return this.weight == otro.weight && this.brand.equals(otro.brand) && this.color.equals(otro.color)
                && this.length == otro.length && this.name.equals(otro.name);
    }

    @Override
    public void updateQuantity(int num) {
        if (quantity - num <= 0)
            quantity = 0;
        else
            quantity -= num;
    }
    public int compareTo(Lure other) {
        int classComp = this.getClass().getSimpleName().compareTo(other.getClass().getSimpleName());
        if (classComp != 0)
            return classComp;
        if (this.quantity < other.quantity)
            return -1;
        else if(this.quantity > other.quantity)
            return 1;
        else
            return 0;
    }
    public String toString() {
        String s = "";
        s += "Brand: " + this.brand + "\n";
        s += "Name : " + this.name + "\n";
        s += "Quantity: " + this.quantity + "\n";
        s += "Color : " + this.color + "\n";
        if(weight != 0.0)
            s += "Weight : " + this.weight + " oz\n";
        if(length != 0.0)
            s += "Length : " + this.length + " in\n";
        return s;
    }
}
