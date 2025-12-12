package HW_12;

public class HumanitarianStudent extends Student {
    public HumanitarianStudent(int creditGoal, int initialMoney) {
        super(creditGoal, initialMoney);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}