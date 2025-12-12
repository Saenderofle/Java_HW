package HW_12;

public class Accountant implements Visitor {
    private int amount;

    public Accountant(int amount) {
        this.amount = amount;
    }

    private void giveMoney(Student s) {
        s.addMoney(amount);
    }

    @Override public void visit(HumanitarianStudent s) { giveMoney(s); }
    @Override public void visit(NaturalStudent s) { giveMoney(s); }
    @Override public void visit(MixedStudent s) { giveMoney(s); }
}