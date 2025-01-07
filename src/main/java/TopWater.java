public class TopWater extends Lure {
    private final String type;
    public TopWater(Lure lure, String type) throws InvalidInputException {
        super(lure.getQuantity(), lure.getBrand(), lure.getWeight(), lure.getLength(), lure.getColor(), lure.getName());
        if(type.equals(WALKER) || type.equals(WHOPPER) || type.equals(POPPER) || type.equals(BUZZ) || type.equals(FROG))
            this.type = type;
        else
            throw new InvalidInputException("Invalid Topwater Type");
    }
    public String getType() {
        return this.type;
    }
    public boolean equals(Object other) {
        if(super.equals(other)) {
            TopWater otro = (TopWater) other;
            return this.type.equals(otro.type);
        }
        return false;
    }
    public String toString() {
        String s = "Top Water\n\n" + type + "\n";
        s += super.toString();
        return s;
    }
}
