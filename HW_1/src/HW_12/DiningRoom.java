package HW_12;

public class DiningRoom implements Visitor {
    private int cost;

    public DiningRoom(int cost) {
        this.cost = cost;
    }

    private void charge(Student s) {
        s.spendMoney(cost);
    }

    @Override public void visit(HumanitarianStudent s) { charge(s); }
    @Override public void visit(NaturalStudent s) { charge(s); }
    @Override public void visit(MixedStudent s) { charge(s); }
}