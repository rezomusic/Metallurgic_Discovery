package com.rezomediaproductions.metallurgicdiscovery.blocks.entity;

import com.rezomediaproductions.metallurgicdiscovery.recipe.BasicMetallurgyStationRecipe;
import com.rezomediaproductions.metallurgicdiscovery.screen.BasicMetallurgyStationMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
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
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class BasicMetallurgyStationBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemStackHandler = new ItemStackHandler(8)
    {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public final ContainerData data;
    private int progress = 0; // Crafting progress
    private int maxProgress = 500; // Completion progress
    private int burnTime = 0; // Remaining time for fuel
    private int burnTimeForCurrentFuel = 0;
    private int shouldCraft = 0; // Forge button has been pressed (begin forging)
    private int craftFinished = 0; // Forging has finished, output item may be taken
    private int hasValidRecipe = 0; // Denotes whether forge button is able to be pressed

    public BasicMetallurgyStationBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(MDBlockEntities.BASIC_METALLURGY_STATION.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> BasicMetallurgyStationBlockEntity.this.progress;
                    case 1 -> BasicMetallurgyStationBlockEntity.this.maxProgress;
                    case 2 -> BasicMetallurgyStationBlockEntity.this.burnTime;
                    case 3 -> BasicMetallurgyStationBlockEntity.this.burnTimeForCurrentFuel;
                    case 4 -> BasicMetallurgyStationBlockEntity.this.hasValidRecipe;
                    case 5 -> BasicMetallurgyStationBlockEntity.this.shouldCraft;
                    case 6 -> BasicMetallurgyStationBlockEntity.this.craftFinished;

                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> BasicMetallurgyStationBlockEntity.this.progress = pValue;
                    case 1 -> BasicMetallurgyStationBlockEntity.this.maxProgress = pValue;
                    case 2 -> BasicMetallurgyStationBlockEntity.this.burnTime = pValue;
                    case 3 -> BasicMetallurgyStationBlockEntity.this.hasValidRecipe = pValue;
                    case 4 -> BasicMetallurgyStationBlockEntity.this.burnTimeForCurrentFuel = pValue;
                    case 5 -> BasicMetallurgyStationBlockEntity.this.shouldCraft = pValue;
                    case 6 -> BasicMetallurgyStationBlockEntity.this.craftFinished = pValue;
                }
            }

            @Override
            public int getCount() { return 7; }
        };
    }

    @Override
    public @NotNull Component getDisplayName()
    {
        return Component.literal("Basic Metallurgy Station");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer) {
        return new BasicMetallurgyStationMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @NotNull Direction side)
    {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
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
        pTag.putInt("basic_metallurgy_station.burnTime", this.burnTime);
        pTag.putInt("basic_metallurgy_station.currentFuelBurnTime", this.burnTimeForCurrentFuel);
        pTag.putInt("basic_metallurgy_station.shouldCraft", this.shouldCraft);
        pTag.putInt("basic_metallurgy_station.craftFinished", this.craftFinished);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        itemStackHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("basic_metallurgy_station.progress");
        burnTime = pTag.getInt("basic_metallurgy_station.burnTime");
        burnTimeForCurrentFuel = pTag.getInt("basic_metallurgy_station.currentFuelBurnTime");
        shouldCraft = pTag.getInt("basic_metallurgy_station.shouldCraft");
        craftFinished = pTag.getInt("basic_metallurgy_station.craftFinished");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public void setShouldCraft() {
        if (this.shouldCraft == 1) {
            this.shouldCraft = 0;
        } else {
            this.shouldCraft = 1;
        }
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, BasicMetallurgyStationBlockEntity pEntity) {
        if(level.isClientSide()) {
            return;
        }
        /* TO DO
        - Check for items in alloy slots
        - Create custom tool head items to add nbt
        - Only render alloy arrows when forging if alloy is in slot
        - Create system for showing stat preview in GUI
        - Fix alloy max stack bug
        - Fix forge button flash on craft finish bug
        - Make it so that only 5 total alloys can be added to the craft
        - Craft takes longer depending on how many alloys are being used
         */

        if (!pEntity.itemStackHandler.getStackInSlot(7).isEmpty()) {
            pEntity.craftFinished = 1;
        } else {
            pEntity.craftFinished = 0;
        }

        // Check for recipe
        if (hasRecipe(pEntity)) {
            pEntity.hasValidRecipe = 1;
        } else {
            pEntity.hasValidRecipe = 0;
            pEntity.shouldCraft = 0;
        }

        // Shrink fuel stack while crafting
        if(pEntity.burnTime <= 1 && pEntity.shouldCraft == 1) {
            ItemStack fuel = pEntity.itemStackHandler.getStackInSlot(4);

            if(!fuel.isEmpty()) {
                fuel.shrink(1);
                int selectedFuel = ForgeHooks.getBurnTime(fuel, null);
                pEntity.burnTimeForCurrentFuel = selectedFuel;
                pEntity.burnTime += selectedFuel ;
            }
        } else if (pEntity.burnTime > 0) {
            pEntity.burnTime--;
        }

        // Increment progress while crafting
        if(hasRecipe(pEntity) && pEntity.shouldCraft == 1 && pEntity.burnTime > 0) {
            pEntity.progress++;
            pEntity.burnTime--;
            setChanged(level, blockPos, blockState);

            if(pEntity.progress >= pEntity.maxProgress) {
                pEntity.shouldCraft = 0;
                pEntity.resetProgress();
                craftItem(pEntity);
            }
        }
        else {
            pEntity.resetProgress();
            setChanged(level, blockPos, blockState);
        }
    }

    public static boolean hasRecipe(BasicMetallurgyStationBlockEntity pEntity) {
        Level level = pEntity.level;
        SimpleContainer inventory = new SimpleContainer(2);

        inventory.setItem(0, pEntity.itemStackHandler.getStackInSlot(5)); // Stencil Slot Item
        inventory.setItem(1, pEntity.itemStackHandler.getStackInSlot(6)); //   Input Slot Item

        Optional<BasicMetallurgyStationRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(BasicMetallurgyStationRecipe.Type.INSTANCE, inventory, level);

        ItemStack fuel = pEntity.itemStackHandler.getStackInSlot(4);

        return (recipe.isPresent()) && (pEntity.itemStackHandler.getStackInSlot(7).isEmpty()) && (!fuel.isEmpty() || pEntity.burnTime > 0);
    }


    private static void craftItem(BasicMetallurgyStationBlockEntity pEntity) {
        Level level = pEntity.level;
        SimpleContainer inventory = new SimpleContainer(2);

        inventory.setItem(0, pEntity.itemStackHandler.getStackInSlot(5)); // Stencil Slot Item
        inventory.setItem(1, pEntity.itemStackHandler.getStackInSlot(6)); //   Input Slot Item

        if (hasRecipe(pEntity)) {
            Optional<BasicMetallurgyStationRecipe> recipe = level.getRecipeManager()
                    .getRecipeFor(BasicMetallurgyStationRecipe.Type.INSTANCE, inventory, level);

            pEntity.itemStackHandler.extractItem(6, recipe.get().getRequiredAmount(), false);
            // Will need to add nbt once custom item is created
            pEntity.itemStackHandler.setStackInSlot(7, new ItemStack(recipe.get().getResultItem().getItem()));
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }
} 
