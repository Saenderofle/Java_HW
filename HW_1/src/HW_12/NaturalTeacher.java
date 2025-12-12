package HW_12;

public class NaturalTeacher implements Visitor {
    private int creditsToGive;

    public NaturalTeacher(int creditsToGive) {
        this.creditsToGive = creditsToGive;
    }

    @Override
    public void visit(HumanitarianStudent student) {
        // Не може вчити гуманітарія
    }

    @Override
    public void visit(NaturalStudent student) {
        student.addCredits(creditsToGive);
    }

    @Override
    public void visit(MixedStudent student) {
        student.addCredits(creditsToGive);
    }
}