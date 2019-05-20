package io.github.cadiboo.nocubes.config;

import io.github.cadiboo.nocubes.mesh.MeshGeneratorType;
import io.github.cadiboo.nocubes.util.SmoothLeavesType;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

import static io.github.cadiboo.nocubes.NoCubes.MOD_ID;

/**
 * @author Cadiboo
 */
final class ClientConfig {

	ForgeConfigSpec.BooleanValue renderSmoothTerrain;

	ForgeConfigSpec.BooleanValue renderSmoothLeaves;
	ForgeConfigSpec.ConfigValue<List<? extends String>> leavesSmoothable;
	ForgeConfigSpec.ConfigValue<MeshGeneratorType> leavesMeshGenerator;
	ForgeConfigSpec.ConfigValue<SmoothLeavesType> smoothLeavesType;

	ForgeConfigSpec.BooleanValue renderExtendedFluids;

	ForgeConfigSpec.BooleanValue applyDiffuseLighting;

	ForgeConfigSpec.BooleanValue betterTextures;

	ForgeConfigSpec.BooleanValue smoothFluidLighting;
	ForgeConfigSpec.BooleanValue smoothFluidColors;
	ForgeConfigSpec.BooleanValue naturalFluidTextures;

	ClientConfig(final ForgeConfigSpec.Builder builder) {
		builder.push("general");
		renderSmoothTerrain = builder
				.comment("If smooth terrain should be rendered")
				.translation(MOD_ID + ".config.renderSmoothTerrain")
				.define("renderSmoothTerrain", true);

		renderSmoothLeaves = builder
				.comment("If smooth leaves should be rendered")
				.translation(MOD_ID + ".config.renderSmoothLeaves")
				.define("renderSmoothLeaves", false);
		leavesSmoothable = builder
				.comment("The list of leaves smoothable blocks")
				.translation(MOD_ID + ".config.leavesSmoothable")
				.defineList("leavesSmoothable", ConfigHelper.getDefaultLeavesSmoothable(), o -> o instanceof String);
		leavesMeshGenerator = builder
				.comment("The mesh generator that generates leaves")
				.translation(MOD_ID + ".config.leavesMeshGenerator")
				.defineEnum("leavesMeshGenerator", MeshGeneratorType.SurfaceNets);
		smoothLeavesType = builder
				.comment("How leaves should be rendered")
				.translation(MOD_ID + ".config.smoothLeavesType")
				.defineEnum("smoothLeavesType", SmoothLeavesType.TOGETHER);

		renderExtendedFluids = builder
				.comment("If fluids should be rendered extended into smoothable blocks")
				.translation(MOD_ID + ".config.renderExtendedFluids")
				.define("renderExtendedFluids", true);

		applyDiffuseLighting = builder
				.comment("If diffuse lighting should be applied when rendering. Accentuates differences between heights")
				.translation(MOD_ID + ".config.applyDiffuseLighting")
				.define("applyDiffuseLighting", true);

		betterTextures = builder
				.comment("If better textures should be searched for when rendering")
				.translation(MOD_ID + ".config.betterTextures")
				.define("betterTextures", true);

		smoothFluidLighting = builder
				.comment("If fluids should be rendered with smooth lighting")
				.translation(MOD_ID + ".config.smoothFluidLighting")
				.define("smoothFluidLighting", true);
		smoothFluidColors = builder
				.comment("If fluids should be rendered with smooth biome blending")
				.translation(MOD_ID + ".config.smoothFluidColors")
				.define("smoothFluidColors", true);
		naturalFluidTextures = builder
				.comment("If fluids should be rendered with flipped and rotated variants of their textures")
				.translation(MOD_ID + ".config.naturalFluidTextures")
				.define("naturalFluidTextures", true);

		builder.pop();
	}

}
