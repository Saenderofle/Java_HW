package HW_12;

public abstract class Student {
    protected int credits = 0;
    protected int money;
    protected int creditGoal;
    protected boolean isExpelled = false;

    public Student(int creditGoal, int initialMoney) {
        this.creditGoal = creditGoal;
        this.money = initialMoney;
    }

    public abstract void accept(Visitor visitor);

    public void addCredits(int amount) {
        if (!isExpelled) {
            this.credits += amount;
        }
    }

    public void addMoney(int amount) {
        if (!isExpelled) {
            this.money += amount;
        }
    }

    public boolean spendMoney(int amount) {
        if (isExpelled) return false;

        if (this.money >= amount) {
            this.money -= amount;
            return true;
        } else {
            this.isExpelled = true; // Гроші скінчилися - відрахування
            return false;
        }
    }

    public boolean hasDiploma() {
        return !isExpelled && credits >= creditGoal;
    }

    public boolean isExpelled() {
        return isExpelled;
    }

    public int getCredits() {
        return credits;
    }

    public int getMoney() {
        return money;
    }
}