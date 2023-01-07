package com.rezomediaproductions.metallurgicdiscovery.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rezomediaproductions.metallurgicdiscovery.MetallurgicDiscovery;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BasicMetallurgyStationRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack input1;
    private final Ingredient input2;
    private final ItemStack output;

    public BasicMetallurgyStationRecipe(ResourceLocation id, ItemStack input1, Ingredient input2, ItemStack output) {
        this.id = id;
        this.input1 = input1;
        this.input2 = input2;
        this.output = output;
    }

    @Override
    public boolean matches(@NotNull SimpleContainer pContainer, @NotNull Level pLevel) {
        if (pLevel.isClientSide) {
            return false;
        }

        // Compares input slot for metals (1) and stencil slot (0) against recipe
        boolean result = (input1.getItem() == pContainer.getItem(1).getItem()) && (input1.getCount() == pContainer.getItem(1).getCount()) && (input2.test(pContainer.getItem(0)));
        return result;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull SimpleContainer pContainer) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public int getRequiredAmount() { return input1.getCount(); };

    //---------------------------------------------------------------------//

    public static class Type implements RecipeType<BasicMetallurgyStationRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "basic_metallurgy";
    }

    public static class Serializer implements RecipeSerializer<BasicMetallurgyStationRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(MetallurgicDiscovery.MOD_ID, "basic_metallurgy");

        @Override
        public @NotNull BasicMetallurgyStationRecipe fromJson(@NotNull ResourceLocation pRecipeId, @NotNull JsonObject pSerializedRecipe) {
            ItemStack input1 = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "metalstack"));
            Ingredient input2 = Ingredient.fromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "stencil"));
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "result"));

            return new BasicMetallurgyStationRecipe(pRecipeId, input1, input2, output);
        }

        @Override
        public @Nullable BasicMetallurgyStationRecipe fromNetwork(@NotNull ResourceLocation id, FriendlyByteBuf buf) {
            ItemStack input1 = buf.readItem();
            Ingredient input2 = Ingredient.fromNetwork(buf);
            ItemStack output = buf.readItem();

            return new BasicMetallurgyStationRecipe(id, input1, input2, output);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, BasicMetallurgyStationRecipe recipe) {
            buf.writeItemStack(recipe.input1, false);
            recipe.input2.toNetwork(buf);
            buf.writeItemStack(recipe.getResultItem(), false);
        }
    }
}
