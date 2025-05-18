package org.example.model;

import java.util.List;

public class OR {
    private int id;
    private List<Equipment> equipment;

    public OR() {}

    public OR(int id, List<Equipment> equipment) {
        this.id = id;
        this.equipment = equipment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }
}
