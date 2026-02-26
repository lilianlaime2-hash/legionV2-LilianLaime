//package util;
//
//import model.Algorithm;
//import model.Orientation;
//
//public class GameConfig {
//
//    public Algorithm a;
//    public String t;
//    public Orientation o = Orientation.SOUTH;
//    public int[] u;
//    public int f = 10;
//
//    public Boolean aValid;
//    public Boolean tValid;
//    public Boolean uValid;
//    public Boolean fValid = true;
//    public Boolean oValid;
//
//    public boolean validate(String[] args) {
//        new ParametersParser().parseInto(this, args);
//        return true;
//    }
//
//    public void printState() {
//
//        System.out.println("Algorithm: [" + showState(aValid, a != null ? a.getLongName() : "") + "]");
//        System.out.println("Type: [" + showState(tValid, t != null ? (t.equals("c") ? "Character" : "Number") : "") + "]");
//        System.out.println("Orientation: [" + showState(oValid, o != null ? o.getLongName() : "") + "]");
//
//        int troopsCount = 0;
//        if (u != null) {
//            for (int x : u) troopsCount += x;
//        }
//
//        System.out.println("Troops: [" + showState(uValid, u != null ? String.valueOf(troopsCount) : "") + "]");
//        System.out.println("Battlefield: [" + showState(fValid, f + " x " + f) + "]");
//    }
//
//    private String showState(Boolean valid, String value) {
//        if (valid == null) {
//            return "Not present";
//        }
//        if (!valid) {
//            return "Invalid";
//        }
//        return value;
//    }
//
//    public GameConfig toConfig() {
//        return new GameConfig(a, t, o, u, f);
//    }
//
//
//}