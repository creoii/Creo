package creoii.creo.core.util;

import creoii.creo.common.entity.BoatEntity;

public class DefaultCreator {
    public static void createBoat(BoatEntity.Boat boat) {
        BoatEntity.BOATS.put(boat.getName(), boat);
    }

    public static void createWood() {

    }

    public static void createDye() {

    }
}