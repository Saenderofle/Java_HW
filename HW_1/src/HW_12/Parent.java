package HW_12;

public class Parent implements Visitor {
    private int amount;

    public Parent(int amount) {
        this.amount = amount;
    }

    private void giveMoney(Student s) {
        s.addMoney(amount);
    }

    @Override public void visit(HumanitarianStudent s) { giveMoney(s); }
    @Override public void visit(NaturalStudent s) { giveMoney(s); }
    @Override public void visit(MixedStudent s) { giveMoney(s); }
}