import java.util.ArrayList;
public class SoftPlastic extends Lure {
    private ArrayList<String> types;
    private final boolean floats;
    private final int packQuantity;
    public SoftPlastic (Lure lure, ArrayList<String> types,
                        boolean floats, int packQuantity) throws InvalidInputException {
        super(lure.getQuantity(), lure.getBrand(), lure.getLength(), lure.getColor(), lure.getName());
        if(packQuantity < 1)
            throw new InvalidInputException("Invalid pack quantity");
        this.floats = floats;
        this.types = types;
        this.packQuantity = packQuantity;
    }
    public boolean floats() {
        return floats;
    }
    public int getPackQuantity() {
        return packQuantity;
    }
    public void addType(String type) {
        if (types != null) {
            types.add(type);
        }
    }
    public ArrayList<String> getTypes() {
        return types;
    }
    public boolean equals(Object other) {
        if (super.equals(other)) {
            SoftPlastic otro = (SoftPlastic) other;
            return this.floats == otro.floats && this.packQuantity == otro.packQuantity;
        }
        return false;
    }
    public String toString() {
        String s = "";
        s += "Soft Plastic\n\n";
        s += super.toString();
        s += "Pack Quantity: " + packQuantity + "\n";
        s += "Techniques and Uses: ";
        for(int i = 0; i < types.size(); i ++) {
            if (i == types.size() - 1) {
                s += types.get(i);
            }
            else
                s+= types.get(i) + ", ";
        }
        s += "\nFloats: ";
        s += (floats) ? "Yes" : "No";
        return s;
    }

}
