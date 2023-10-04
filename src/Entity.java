import java.util.Random;

public class Entity {

    int attack;
    int defense;
    float healthPoints;
    int minDamage;
    int maxDamage;

    public Entity(int attack, int defense, int healthPoints, int minDamage, int maxDamage) {
        // проверка на правильность введенных данных
        if (attack < 1 || attack > 30)
            throw new IllegalArgumentException("Атака должна быть от 1 до 30");
        if (defense < 1 || defense > 30)
            throw new IllegalArgumentException("Защита должна быть от 1 до 30");
        if (healthPoints < 1)
            throw new IllegalArgumentException("Здоровье не может быть отрицательным");
        if (minDamage < 1)
            throw new IllegalArgumentException("Минимальный уровень атаки должен быть не меньше одного");

        this.attack = attack;
        this.defense = defense;
        this.healthPoints = healthPoints;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public boolean isAlive() {
        return healthPoints > 0;
    }

    public void attack(Entity target) {
        Random random = new Random();
        int attackModifier = Math.max(attack - target.defense + 1, 1); // расчитываем модификатор атаки

        int[] diceRolls = new int[attackModifier];
        for (int i = 0; i < attackModifier; i++) {
            diceRolls[i] = random.nextInt(6) + 1; // записываем в массив diceRolls все результаты костей
        }

        boolean attackSuccess = false;
        for(int roll : diceRolls) {
            if(roll == 5 || roll == 6) {
                attackSuccess = true;
                break;
            }
        }

        if(attackSuccess) {
            int damage = random.nextInt(maxDamage - minDamage + 1) + minDamage;
            target.healthPoints -= damage;

            if(target.healthPoints <= 0)
                target.healthPoints = 0;

            System.out.println("Атака успешна! Было нанесено " + damage + " единиц урона.");
        }
        else
            System.out.println("Атака неудачна :(");
    }

    public String getStats() {
        String nameOfEntity;

        if(this instanceof Player)
            nameOfEntity = "Игрок";
        else
            nameOfEntity = "Монстр";

        return "Сущность: " + nameOfEntity + ", текущее здоровье: " + getHealthPoints();
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public float getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(float healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }
}
