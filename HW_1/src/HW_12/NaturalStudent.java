package HW_12;

public class NaturalStudent extends Student {
    public NaturalStudent(int creditGoal, int initialMoney) {
        super(creditGoal, initialMoney);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}