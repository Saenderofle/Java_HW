package HW_12;

public class MixedStudent extends Student {
    public MixedStudent(int creditGoal, int initialMoney) {
        super(creditGoal, initialMoney);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}