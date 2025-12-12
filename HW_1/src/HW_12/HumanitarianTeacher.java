package HW_12;

public class HumanitarianTeacher implements Visitor {
    private int creditsToGive;

    public HumanitarianTeacher(int creditsToGive) {
        this.creditsToGive = creditsToGive;
    }

    @Override
    public void visit(HumanitarianStudent student) {
        student.addCredits(creditsToGive);
    }

    @Override
    public void visit(NaturalStudent student) {
        // Не може вчити "технаря"
    }

    @Override
    public void visit(MixedStudent student) {
        student.addCredits(creditsToGive);
    }
}