package de.igg.citygmldiff.citygmldiff.matching.model;

import java.util.HashSet;
import java.util.Set;

/**
 * This is just a demo class demonstrating the behavior. Used for a question on stackoverwflow.
 */
public class MyMatching {

    private Integer[] id;

    public MyMatching(Integer id1, Integer id2) {
        this.id = new Integer[2];
        id[0] = id1;
        id[1] = id2;
    }

    public static void main(String[] args) {
        MyMatching matching1 = new MyMatching(1571021585, 848339230);
        MyMatching matching2 = new MyMatching(661883516, 310961952);
        Set<MyMatching> matchings = new HashSet<>();
        matchings.add(matching1);
        matchings.add(matching2);

        MyMatching testMatching = new MyMatching(1571021585, 848339230);
        System.out.print("HashSet contains testMatching: ");
        System.out.println(matchings.contains(testMatching));

        Object[] matchingsArray = matchings.toArray();
        for (Object o : matchingsArray) {
            System.out.print("Object equals testMatching: ");
            System.out.println(o.equals(testMatching));
        }
    }

    public String getId() {
        return id[0] + ":" + id[1];
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || (this.getClass() != other.getClass())) {
            return false;
        }
        MyMatching otherMatching = (MyMatching) other;
        return (getId().equals(otherMatching.getId()));
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
