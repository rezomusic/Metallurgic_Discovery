package com.rezomediaproductions.metallurgicdiscovery.blocks.entity;

import com.rezomediaproductions.metallurgicdiscovery.items.ItemsMain;
import com.rezomediaproductions.metallurgicdiscovery.screen.BasicMetallurgyStationMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BasicMetallurgyStationBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemStackHandler = new ItemStackHandler(6)
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 140;

    public BasicMetallurgyStationBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(MDBlockEntities.BASIC_METALLURGY_STATION.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> BasicMetallurgyStationBlockEntity.this.progress;
                    case 1 -> BasicMetallurgyStationBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> BasicMetallurgyStationBlockEntity.this.progress = pValue;
                    case 1 -> BasicMetallurgyStationBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName()
    {
        return Component.literal("Basic Metallurgy Station");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new BasicMetallurgyStationMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @NotNull Direction side)
    {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad()
    {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemStackHandler);
    }

    @Override
    public void invalidateCaps()
    {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemStackHandler.serializeNBT());
        pTag.putInt("basic_metallurgy_station.progress", this.progress);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemStackHandler.deserializeNBT(serializeNBT().getCompound("inventory"));
        progress = pTag.getInt("basic_metallurgy_station.progress");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, BasicMetallurgyStationBlockEntity pEntity) {
        // May not be needed
        if(level.isClientSide()) {
            return;
        }

        if(hasRecipe(pEntity)) {
            pEntity.progress++;
            setChanged(level, blockPos, blockState);

            if(pEntity.progress >= pEntity.maxProgress) {
                craftItem(pEntity);
            }
        }
        else {
            pEntity.resetProgress();
            setChanged(level, blockPos, blockState);
        }

    }

    private void resetProgress() {
        this.progress = 0;
    }

    // SLOTS 0, 1, 2, 3 = Alloy Slots
    // SLOT 4 = Metal Slot
    // SLOT 5 = Output
    private static void craftItem(BasicMetallurgyStationBlockEntity pEntity) {
        if (hasRecipe(pEntity)) {
            pEntity.itemStackHandler.extractItem(1, 1, false);
            pEntity.itemStackHandler.setStackInSlot(2, new ItemStack(ItemsMain.CELESTITE_FLAKES.get(),
                    pEntity.itemStackHandler.getStackInSlot(2).getCount() + 1));

            pEntity.resetProgress();
        }
    }

    //TEMPORARY CODE ALWAYS RETURNS FALSE
    // Will check for recipe here once serializer is finished
    private static boolean hasRecipe(BasicMetallurgyStationBlockEntity pEntity) {
        SimpleContainer inventory = new SimpleContainer(pEntity.itemStackHandler.getSlots());
        for (int i = 0; i < pEntity.itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, pEntity.itemStackHandler.getStackInSlot(i));
        }

        boolean recipe = false;

        return recipe && canInsertAmountIntoOutputSlot(inventory) && canInsertItemIntoOutputSlot(inventory, new ItemStack(ItemsMain.CELESTITE_FLAKES.get(), 1));

    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack itemStack) {
        return inventory.getItem(2).getItem() == itemStack.getItem() || inventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }
} 
