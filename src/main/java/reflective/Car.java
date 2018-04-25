package reflective;

public class Car {
    private String name = "car";
    private Car car = this;

    public void describe() {
        System.out.println("I'm a car.");
    }
}
