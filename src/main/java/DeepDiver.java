public class DeepDiver extends Lure {
    private int divesTo;
    public DeepDiver(Lure lure, int divesTo) throws InvalidInputException{
        super(lure.getQuantity(), lure.getBrand(), lure.getWeight(),lure.getLength(), lure.getColor(), lure.getName());
        if (divesTo < 1) {
            throw new InvalidInputException("Invalid depth");
        }
        else
            this.divesTo = divesTo;
    }
    public int getDivesTo() {
        return divesTo;
    }
    public void setDivesTo(int depth) {
        if (divesTo > 0)
            this.divesTo = depth;
    }
    public boolean equals(Object other) {
        if(super.equals(other)) {
            if (!(other instanceof DeepDiver))
                return false;
            DeepDiver otro = (DeepDiver) other;
            return this.divesTo == otro.divesTo;
        }
        return false;
    }
    public String toString() {
        String s = "";
        s += "DeepDiver" + "\n\n";
        s += "Dives To : " + this.divesTo + " feet\n";
        s += super.toString();
        return s;
    }
}
