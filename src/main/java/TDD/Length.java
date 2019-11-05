package TDD;

class Length {
    private double value;
    private String unit;


    Length(double val, String uint) {
        this.value = val;
        this.unit = uint;
    }

    Length as(String u) {
        //1 英尺(foot) = 12 英寸(inch)
        //1 码(yard) = 3 英尺(foot)

        switch (this.unit){
            case "f":
                this.value=TransToFoot.transfor(u,value);
                break;
            case "yard":
                this.value=TransToYard.transfor(u,value);
                break;
            case "inch":
                this.value=TransToInch.transfor(u,value);
                break;
            default:
                break;
        }
        this.unit=u;
        return this;
    }

    double getVal() {
        return this.value;
    }

    String getUint() {
        return this.unit;
    }
}
