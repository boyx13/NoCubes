package io.github.cadiboo.nocubes.config;

import io.github.cadiboo.nocubes.mesh.MeshGeneratorType;
import io.github.cadiboo.nocubes.util.ExtendFluidsRange;
import io.github.cadiboo.nocubes.util.SmoothLeavesType;
import net.minecraft.block.Block;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Cadiboo
 */
@SuppressWarnings("WeakerAccess")
public final class Config {

	static final Logger LOGGER = LogManager.getLogger();
	static final HashSet<Block> leavesSmoothableBlocks = new HashSet<>(); // Client

	// Client
	public static boolean renderSmoothTerrain;
	public static boolean renderSmoothLeaves;
	public static MeshGeneratorType leavesMeshGenerator;
	public static Set<String> leavesSmoothable;
	public static SmoothLeavesType smoothLeavesType;

	public static boolean renderExtendedFluids;

	public static boolean applyDiffuseLighting;

	public static boolean betterTextures;

	public static boolean smoothFluidLighting;
	public static boolean smoothFluidColors;
	public static boolean naturalFluidTextures;

	// Server
	public static ExtendFluidsRange extendFluidsRange;

	public static MeshGeneratorType terrainMeshGenerator;
	public static boolean terrainCollisions;
	public static Set<String> terrainSmoothable;

	// Client
	public static HashSet<Block> getLeavesSmoothableBlocks() {
		return leavesSmoothableBlocks;
	}

}
