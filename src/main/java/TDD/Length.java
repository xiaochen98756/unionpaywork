package TDD;

public class Length {
    private final double value;
    private final String unit;

    public Length(double val, String uinnt) {
        this.value = val;
        this.unit = uinnt;
    }

    public Length as(String u) {
        Length len = this;
        if (this.unit.equals("f")) {
            if (u.equals("yard")) {
                len = new Length(this.value / 3, u);
            } else if (u.equals("inch")) {
                len = new Length(this.value * 12, u);
            }
        }

        if (this.unit.equals("yard")) {
            if (u.equals("inch")) {
                len = new Length(this.value * 36, u);
            } else if (u.equals("f")){
                len = new Length(this.value * 3, u);
            }
        }

        if (this.unit.equals("inch")) {
            if (u.equals("f")) {
                len = new Length(this.value / 12, u);
            } else if (u.equals("yard")) {
                len = new Length(this.value / 36, u);
            }
        }

        return len;
    }

    public double getVal() {
        return this.value;
    }

    public String getUinnt() {
        return this.unit;
    }
}
