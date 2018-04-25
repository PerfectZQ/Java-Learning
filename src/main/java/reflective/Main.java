package reflective;

public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        ObjectAnalyser oa = new ObjectAnalyser();
        System.out.println(oa.toString(car));
    }
}
