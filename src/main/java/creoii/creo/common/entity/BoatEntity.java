package creoii.creo.common.entity;

import creoii.creo.core.registry.EntityRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("EntityConstructor")
public class BoatEntity extends net.minecraft.entity.vehicle.BoatEntity {
    public static final Map<String, Boat> BOATS = new HashMap<>();
    private static final TrackedData<String> BOAT_TYPE = DataTracker.registerData(BoatEntity.class, TrackedDataHandlerRegistry.STRING);

    public BoatEntity(EntityType<? extends net.minecraft.entity.vehicle.BoatEntity> entityType, World world) {
        super(entityType, world);
        BOATS.put("default", new Boat("default", Blocks.OAK_PLANKS, Items.OAK_BOAT));
    }

    public BoatEntity(World world, double x, double y, double z) {
        super(EntityRegistry.BOAT, world);
        this.setPosition(x, y, z);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        BOATS.put("default", new Boat("default", Blocks.OAK_PLANKS, Items.OAK_BOAT));
    }

    @Override
    public ItemStack getPickBlockStack() {
        return new ItemStack(BOATS.get(this.getBoat().getName()).getItem().asItem());
    }

    public Boat getBoat() {
        return BOATS.get(this.dataTracker.get(BOAT_TYPE));
    }

    public void setBoat(Boat boat) {
        this.dataTracker.set(BOAT_TYPE, boat.getName());
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(BOAT_TYPE, "default");
    }

    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putString("Boat", this.getBoatType().getName());
    }

    protected void readCustomDataFromNbt(NbtCompound nbt) {
        if (nbt.contains("Boat", 8)) {
            this.setBoatType(Type.getType(nbt.getString("Boat")));
        }
    }

    public static class Boat {
        private final String name;
        private final Block base;
        private final Item item;

        public Boat(String name, Block base, Item item) {
            this.name = name;
            this.base = base;
            this.item = item;
        }

        public String getName() {
            return name;
        }

        public Block getBase() {
            return base;
        }

        public Item getItem() {
            return item;
        }
    }
}