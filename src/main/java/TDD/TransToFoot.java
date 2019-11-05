package TDD;

class TransToFoot implements TransToUnit {


    static double transfor(String unit, double value) {
        if ("yard".equals(unit)) {
            return value / 3;
        } else if ("inch".equals(unit)) {
            return value * 12;
        }else {
            return value;
        }
    }
}