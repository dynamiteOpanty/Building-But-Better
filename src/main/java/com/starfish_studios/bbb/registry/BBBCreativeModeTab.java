package com.starfish_studios.bbb.registry;

import com.starfish_studios.bbb.BuildingButBetter;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

import static com.starfish_studios.bbb.registry.BBBItems.*;

public class BBBCreativeModeTab {


    @SuppressWarnings("unused")
    public static final CreativeModeTab ITEM_GROUP = register("item_group", FabricItemGroup.builder().icon(CORNERSTONE::getDefaultInstance).title(Component.translatable("itemGroup.bbb.tab")).displayItems((featureFlagSet, output) -> {

        output.accept(HAMMER);
        output.accept(CHISEL);
        output.accept(ROPE);

        // output.accept(PLASTER);

        // output.accept(COPPER_GATEWAY);

        output.accept(BRAZIER);
        output.accept(SOUL_BRAZIER);
        output.accept(IRON_FENCE);

        output.accept(STONE_FRAME);
        output.accept(BLACKSTONE_FRAME);
        output.accept(SANDSTONE_FRAME);
        output.accept(RED_SANDSTONE_FRAME);
        output.accept(QUARTZ_FRAME);
        output.accept(DEEPSLATE_FRAME);
        output.accept(NETHER_BRICK_FRAME);

        output.accept(POLISHED_STONE);
        output.accept(POLISHED_STONE_STAIRS);
        output.accept(POLISHED_STONE_SLAB);
        output.accept(STONE_TILES);
        output.accept(STONE_TILE_STAIRS);
        output.accept(STONE_TILE_SLAB);
        output.accept(STONE_COLUMN);
        output.accept(STONE_URN);
        output.accept(STONE_FENCE);
        output.accept(STONE_MOULDING);
        output.accept(STONE_BLOCK);

        output.accept(BLACKSTONE_COLUMN);
        output.accept(BLACKSTONE_URN);
        output.accept(BLACKSTONE_FENCE);
        output.accept(BLACKSTONE_MOULDING);
        output.accept(BLACKSTONE_BLOCK);

        output.accept(SANDSTONE_COLUMN);
        output.accept(SANDSTONE_URN);
        output.accept(SANDSTONE_FENCE);
        output.accept(SANDSTONE_MOULDING);
        output.accept(SANDSTONE_BLOCK);

        output.accept(RED_SANDSTONE_COLUMN);
        output.accept(RED_SANDSTONE_URN);
        output.accept(RED_SANDSTONE_FENCE);
        output.accept(RED_SANDSTONE_MOULDING);
        output.accept(RED_SANDSTONE_BLOCK);

        output.accept(QUARTZ_COLUMN);
        output.accept(QUARTZ_URN);
        output.accept(QUARTZ_FENCE);
        output.accept(QUARTZ_MOULDING);
        output.accept(QUARTZ_BLOCK);

        output.accept(DEEPSLATE_COLUMN);
        output.accept(DEEPSLATE_URN);
        output.accept(DEEPSLATE_FENCE);
        output.accept(DEEPSLATE_MOULDING);
        output.accept(DEEPSLATE_BLOCK);

        output.accept(NETHER_BRICK_COLUMN);
        output.accept(NETHER_BRICK_URN);
        output.accept(NETHER_BRICK_FENCE);
        output.accept(NETHER_BRICK_MOULDING);
        output.accept(NETHER_BRICK_BLOCK);


        // output.accept(BIG_DOOR);


        // Oak, Spruce, Birch, Jungle, Acacia, Dark Oak, Crimson, Warped, Mangrove, Bamboo, Cherry

        output.accept(OAK_TRIM);
        output.accept(OAK_BEAM);
        output.accept(OAK_BEAM_STAIRS);
        output.accept(OAK_BEAM_SLAB);
        output.accept(OAK_PALLET);
        output.accept(OAK_SUPPORT);
        output.accept(OAK_BALUSTRADE);
        output.accept(OAK_WALL);
        output.accept(OAK_FRAME);
        output.accept(OAK_LANTERN);
        output.accept(OAK_LADDER);
        output.accept(OAK_LATTICE);


        output.accept(SPRUCE_TRIM);
        output.accept(SPRUCE_BEAM);
        output.accept(SPRUCE_BEAM_STAIRS);
        output.accept(SPRUCE_BEAM_SLAB);
        output.accept(SPRUCE_PALLET);
        output.accept(SPRUCE_SUPPORT);
        output.accept(SPRUCE_BALUSTRADE);
        output.accept(SPRUCE_WALL);
        output.accept(SPRUCE_FRAME);
        output.accept(SPRUCE_LANTERN);
        output.accept(SPRUCE_LADDER);
        output.accept(SPRUCE_LATTICE);

        output.accept(BIRCH_TRIM);
        output.accept(BIRCH_BEAM);
        output.accept(BIRCH_BEAM_STAIRS);
        output.accept(BIRCH_BEAM_SLAB);
        output.accept(BIRCH_PALLET);
        output.accept(BIRCH_SUPPORT);
        output.accept(BIRCH_BALUSTRADE);
        output.accept(BIRCH_WALL);
        output.accept(BIRCH_FRAME);
        output.accept(BIRCH_LANTERN);
        output.accept(BIRCH_LADDER);
        output.accept(BIRCH_LATTICE);

        output.accept(JUNGLE_TRIM);
        output.accept(JUNGLE_BEAM);
        output.accept(JUNGLE_BEAM_STAIRS);
        output.accept(JUNGLE_BEAM_SLAB);
        output.accept(JUNGLE_PALLET);
        output.accept(JUNGLE_SUPPORT);
        output.accept(JUNGLE_BALUSTRADE);
        output.accept(JUNGLE_WALL);
        output.accept(JUNGLE_FRAME);
        output.accept(JUNGLE_LANTERN);
        output.accept(JUNGLE_LADDER);
        output.accept(JUNGLE_LATTICE);

        output.accept(ACACIA_TRIM);
        output.accept(ACACIA_BEAM);
        output.accept(ACACIA_BEAM_STAIRS);
        output.accept(ACACIA_BEAM_SLAB);
        output.accept(ACACIA_PALLET);
        output.accept(ACACIA_SUPPORT);
        output.accept(ACACIA_BALUSTRADE);
        output.accept(ACACIA_WALL);
        output.accept(ACACIA_FRAME);
        output.accept(ACACIA_LANTERN);
        output.accept(ACACIA_LADDER);
        output.accept(ACACIA_LATTICE);

        output.accept(DARK_OAK_TRIM);
        output.accept(DARK_OAK_BEAM);
        output.accept(DARK_OAK_BEAM_STAIRS);
        output.accept(DARK_OAK_BEAM_SLAB);
        output.accept(DARK_OAK_PALLET);
        output.accept(DARK_OAK_SUPPORT);
        output.accept(DARK_OAK_BALUSTRADE);
        output.accept(DARK_OAK_WALL);
        output.accept(DARK_OAK_FRAME);
        output.accept(DARK_OAK_LANTERN);
        output.accept(DARK_OAK_LADDER);
        output.accept(DARK_OAK_LATTICE);

        output.accept(CRIMSON_TRIM);
        output.accept(CRIMSON_BEAM);
        output.accept(CRIMSON_BEAM_STAIRS);
        output.accept(CRIMSON_BEAM_SLAB);
        output.accept(CRIMSON_PALLET);
        output.accept(CRIMSON_SUPPORT);
        output.accept(CRIMSON_BALUSTRADE);
        output.accept(CRIMSON_WALL);
        output.accept(CRIMSON_FRAME);
        output.accept(CRIMSON_LANTERN);
        output.accept(CRIMSON_LADDER);
        output.accept(CRIMSON_LATTICE);

        output.accept(WARPED_TRIM);
        output.accept(WARPED_BEAM);
        output.accept(WARPED_BEAM_STAIRS);
        output.accept(WARPED_BEAM_SLAB);
        output.accept(WARPED_PALLET);
        output.accept(WARPED_SUPPORT);
        output.accept(WARPED_BALUSTRADE);
        output.accept(WARPED_WALL);
        output.accept(WARPED_FRAME);
        output.accept(WARPED_LANTERN);
        output.accept(WARPED_LADDER);
        output.accept(WARPED_LATTICE);

        output.accept(MANGROVE_TRIM);
        output.accept(MANGROVE_BEAM);
        output.accept(MANGROVE_BEAM_STAIRS);
        output.accept(MANGROVE_BEAM_SLAB);
        output.accept(MANGROVE_PALLET);
        output.accept(MANGROVE_SUPPORT);
        output.accept(MANGROVE_BALUSTRADE);
        output.accept(MANGROVE_WALL);
        output.accept(MANGROVE_FRAME);
        output.accept(MANGROVE_LANTERN);
        output.accept(MANGROVE_LADDER);
        output.accept(MANGROVE_LATTICE);

        output.accept(BAMBOO_TRIM);
        output.accept(BAMBOO_BEAM);
        output.accept(BAMBOO_BEAM_STAIRS);
        output.accept(BAMBOO_BEAM_SLAB);
        output.accept(BAMBOO_PALLET);
        output.accept(BAMBOO_SUPPORT);
        output.accept(BAMBOO_BALUSTRADE);
        output.accept(BAMBOO_WALL);
        output.accept(BAMBOO_FRAME);
        output.accept(BAMBOO_LANTERN);
        output.accept(BAMBOO_LADDER);
        output.accept(BAMBOO_LATTICE);

        output.accept(CHERRY_TRIM);
        output.accept(CHERRY_BEAM);
        output.accept(CHERRY_BEAM_STAIRS);
        output.accept(CHERRY_BEAM_SLAB);
        output.accept(CHERRY_PALLET);
        output.accept(CHERRY_SUPPORT);
        output.accept(CHERRY_BALUSTRADE);
        output.accept(CHERRY_WALL);
        output.accept(CHERRY_FRAME);
        output.accept(CHERRY_LANTERN);
        output.accept(CHERRY_LADDER);
        output.accept(CHERRY_LATTICE);


        // region LAYERS
        output.accept(OAK_LAYER);
        output.accept(SPRUCE_LAYER);
        output.accept(BIRCH_LAYER);
        output.accept(JUNGLE_LAYER);
        output.accept(ACACIA_LAYER);
        output.accept(DARK_OAK_LAYER);
        output.accept(CRIMSON_LAYER);
        output.accept(WARPED_LAYER);
        output.accept(MANGROVE_LAYER);
        output.accept(BAMBOO_LAYER);
        output.accept(BAMBOO_MOSAIC_LAYER);
        output.accept(CHERRY_LAYER);
        output.accept(MOSS_LAYER);
        output.accept(STONE_LAYER);
        output.accept(COBBLESTONE_LAYER);
        output.accept(MOSSY_COBBLESTONE_LAYER);
        output.accept(SMOOTH_STONE_LAYER);
        output.accept(POLISHED_STONE_LAYER);
        output.accept(STONE_TILE_LAYER);
        output.accept(STONE_BRICK_LAYER);
        output.accept(MOSSY_STONE_BRICK_LAYER);
        output.accept(GRANITE_LAYER);
        output.accept(POLISHED_GRANITE_LAYER);
        output.accept(DIORITE_LAYER);
        output.accept(POLISHED_DIORITE_LAYER);
        output.accept(ANDESITE_LAYER);
        output.accept(POLISHED_ANDESITE_LAYER);
        output.accept(COBBLED_DEEPSLATE_LAYER);
        output.accept(POLISHED_DEEPSLATE_LAYER);
        output.accept(DEEPSLATE_BRICK_LAYER);
        output.accept(DEEPSLATE_TILE_LAYER);
        output.accept(BRICK_LAYER);
        output.accept(MUD_BRICK_LAYER);
        output.accept(SANDSTONE_LAYER);
        output.accept(SMOOTH_SANDSTONE_LAYER);
        output.accept(RED_SANDSTONE_LAYER);
        output.accept(SMOOTH_RED_SANDSTONE_LAYER);
        output.accept(PRISMARINE_LAYER);
        output.accept(PRISMARINE_BRICK_LAYER);
        output.accept(DARK_PRISMARINE_LAYER);
        output.accept(NETHER_BRICK_LAYER);
        output.accept(RED_NETHER_BRICK_LAYER);
        output.accept(BLACKSTONE_LAYER);
        output.accept(POLISHED_BLACKSTONE_LAYER);
        output.accept(POLISHED_BLACKSTONE_BRICK_LAYER);
        output.accept(END_STONE_BRICK_LAYER);
        output.accept(PURPUR_LAYER);
        output.accept(QUARTZ_LAYER);
        output.accept(CUT_COPPER_LAYER);
        output.accept(EXPOSED_CUT_COPPER_LAYER);
        output.accept(WEATHERED_CUT_COPPER_LAYER);
        output.accept(OXIDIZED_CUT_COPPER_LAYER);
        output.accept(WAXED_CUT_COPPER_LAYER);
        output.accept(WAXED_EXPOSED_CUT_COPPER_LAYER);
        output.accept(WAXED_WEATHERED_CUT_COPPER_LAYER);
        output.accept(WAXED_OXIDIZED_CUT_COPPER_LAYER);
        // endregion

        // output.accept(WRENCH);

        }).build()
    );

    private static CreativeModeTab register(String id, CreativeModeTab tab) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(BuildingButBetter.MOD_ID, id), tab);
    }
}
