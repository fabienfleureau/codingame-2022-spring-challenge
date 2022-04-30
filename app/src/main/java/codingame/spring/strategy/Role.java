package codingame.spring.strategy;

public enum Role {
    FARMER,
    ATTACKER,
    DEFENDER;

    public static Role fromId(int id) {
        return id == 1 ? DEFENDER : FARMER;
    }
    
}
