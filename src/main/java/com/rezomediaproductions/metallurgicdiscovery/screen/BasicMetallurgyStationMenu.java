package com.rezomediaproductions.metallurgicdiscovery.screen;

import com.rezomediaproductions.metallurgicdiscovery.blocks.BlocksMain;
import com.rezomediaproductions.metallurgicdiscovery.blocks.entity.BasicMetallurgyStationBlockEntity;
import com.rezomediaproductions.metallurgicdiscovery.items.ItemsMain;
import com.rezomediaproductions.metallurgicdiscovery.util.MDTags;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class BasicMetallurgyStationMenu extends AbstractContainerMenu {
    public final BasicMetallurgyStationBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public BasicMetallurgyStationMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(11));
    }

    public BasicMetallurgyStationMenu(int id, Inventory inv, BlockEntity entity, ContainerData data) {
        super(MDMenuTypes.BASIC_METALLURGY_STATION_MENU.get(), id);
        checkContainerSize(inv, 8);
        blockEntity = (BasicMetallurgyStationBlockEntity) entity;
        this.level = inv.player.level;
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            // Top left alloy
            this.addSlot(new SlotItemHandler(handler, 0, 41, 20) {
                public boolean mayPlace(@NotNull ItemStack pStack)
                    { return pStack.is(ItemsMain.MYTHRIL_FLAKES.get()); }
                public int getMaxStackSize() {return 5;}
            });
            // Top right alloy
            this.addSlot(new SlotItemHandler(handler, 1, 103, 20) {
                public boolean mayPlace(@NotNull ItemStack pStack)
                    { return pStack.is(ItemsMain.CHROMIUM_FLAKES.get()); }
                public int getMaxStackSize() {return 5;}
            });
            // Bottom left alloy
            this.addSlot(new SlotItemHandler(handler, 2, 41, 82){
                public boolean mayPlace(@NotNull ItemStack pStack)
                    { return pStack.is(ItemsMain.VANADIUM_FLAKES.get()); }
                public int getMaxStackSize() {return 5;}
            });
            // Bottom right alloy
            this.addSlot(new SlotItemHandler(handler, 3, 103, 82){
                public boolean mayPlace(@NotNull ItemStack pStack)
                    { return pStack.is(ItemsMain.CELESTITE_FLAKES.get()); }
                public int getMaxStackSize() {return 5;}
            });
            // Fuel Input
            this.addSlot(new SlotItemHandler(handler, 4, 14, 56){
                public boolean mayPlace(@NotNull ItemStack pStack) { return ForgeHooks.getBurnTime(pStack, null) > 0; }
            });
            // Stencil Slot
            this.addSlot(new SlotItemHandler(handler, 5, 207, 52){
                public boolean mayPlace(@NotNull ItemStack pStack) { return pStack.is(MDTags.Items.IS_TOOL_STENCIL); }
                public int getMaxStackSize() { return 1; }
            });
            // Center input for metals
            this.addSlot(new SlotItemHandler(handler, 6, 72, 51));
            // Output crafted tool heads
            this.addSlot(new SlotItemHandler(handler, 7, 180, 51) {
                public boolean mayPlace(@NotNull ItemStack pStack) { return false; }
                public boolean mayPickup(Player playerIn) {
                    return data.get(6) == 1;
                }});

        });

        addDataSlots(data);
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public boolean hasRecipe() {
        switch (data.get(4)) {
            case 0 -> { return false; }
            case 1 -> { return true; }
            default -> { return false; }
        }
    }

    public boolean isBurning() {
        return data.get(2) > 0;
    }

    public int getAlloyArrowScaledProgressWidth() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 14;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public int getAlloyArrowScaledProgressHeight() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 17;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public int getScaledMainProgressBar() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 76;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public int getScaledFurnaceFlameHeight() {
        int burnTime = this.data.get(2);
        int currentFuelBurnTime = this.data.get(3);
        int progressArrowSize = 14;

        return currentFuelBurnTime != 0 && burnTime != 0 ? burnTime * progressArrowSize / currentFuelBurnTime : 0;
    }

    // Returns blit values for all 4 tool stats
    public int getScaledStatsBars() {
        Item currentMetal = slots.get(7).getItem().getItem();

        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);  // Max Progress
        int statBarSize = 53; // This is the height in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * statBarSize / maxProgress : 0;
    }


    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 8;  // must be the number of slots you have!

    @Override
    public ItemStack quickMoveStack(@NotNull Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }


    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), pPlayer, BlocksMain.BASIC_METALLURGY_STATION.get());
    }


    // Places inventory slots on the GUI
    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; i++) {
            for (int n = 0; n < 9; n++) {
                this.addSlot(new Slot(playerInventory, n + i * 9 + 9, 39 + n * 18, 127 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 39 + i * 18, 185));
        }
    }
}
