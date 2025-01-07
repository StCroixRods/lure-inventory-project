public class Jig extends Lure {
    private String jigType;
    public Jig(Lure lure, String jigType) throws InvalidInputException {
        super(lure.getBrand(), lure.getColor(), lure.getWeight(), lure.getQuantity(), lure.getName());
        if (jigType.equals(SWIM) || jigType.equals(FLIP) || jigType.equals(BLADE)
                || jigType.equals(PITCH) || jigType.equals(SPINNER)) {
            this.jigType = jigType;
        }
        else
            throw new InvalidInputException("Invalid jig type");
    }
    public String getJigType() {
        return jigType;
    }
    public void setJigType(String jigType) {
        if (jigType.equals(SWIM) || jigType.equals(FLIP) || jigType.equals(BLADE)
                || jigType.equals(PITCH) || jigType.equals(SPINNER)) {
            this.jigType = jigType;
        }
    }
    public boolean equals(Object other) {
        if (super.equals(other)) {
            Jig otro = (Jig) other;
            return this.jigType.equals(otro.jigType);
        }
        return false;
    }
    public String toString() {
        String s = "";
        s += "Jig\n\n";
        s += "Jig Type: " + jigType + "\n";
        s += super.toString();
        return s;
    }
}
