package TDD;

class TransToYard implements TransToUnit {

    static double transfor(String unit, double value) {
        if ("inch".equals(unit)) {
            return value * 36;
        } else if ("f".equals(unit)){
            return value * 3;
        }else {
            return value;
        }
    }
}
