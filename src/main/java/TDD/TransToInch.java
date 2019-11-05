package TDD;

class TransToInch implements TransToUnit {

    static double transfor(String unit, double value) {
        if ("f".equals(unit)) {
            return value / 12;
        } else if ("yard".equals(unit)) {
            return value / 36;
        }else {
            return value;
        }
    }
}
