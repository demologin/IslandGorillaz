package com.javarush.island.arsinov.model.animals;

public enum AnimalType {

    HORSE("Horse"),
    WOLF("Wolf"),
    BOA("Boa"),
    FOX("Fox"),
    GRIZZLY("Grizzly"),
    DEER("Deer"),
    EAGLE("Eagle"),
    RABBIT("Rabbit"),
    MOUSE("Mouse"),
    GOAT("Goat"),
    SHEEP("Sheep"),
    WILD_BOAR("Wild_boar"),
    BUFFALO("Buffalo"),
    DUCK("Duck");

    private final String typeName;


    AnimalType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public static AnimalType fromString(String typeName) {
        for (AnimalType animalType : AnimalType.values()) {
            if(animalType.getTypeName().equalsIgnoreCase(typeName)){
                return animalType;
            }
        }
     throw new IllegalArgumentException("Invalid animal type: " + typeName);
    }
}
