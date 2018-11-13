package cadiboo.nocubes.util;

import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;

import cadiboo.renderchunkrebuildchunkhooks.event.RebuildChunkBlockEvent;
import cadiboo.renderchunkrebuildchunkhooks.event.RebuildChunkBlocksEvent;

/**
 * Holds all enums and enum-related stuff for this mod
 *
 * @author Cadiboo
 */
public final class ModEnums {

	/**
	 * provides some default methods for formatting enum names
	 *
	 * @author Cadiboo
	 */
	public interface IEnumNameFormattable {

		/**
		 * Converts the name to lowercase as per {@link java.lang.String#toLowerCase() String.toLowerCase}.
		 */
		default String getNameLowercase() {
			return this.name().toLowerCase();
		}

		/**
		 * Converts the name to uppercase as per {@link java.lang.String#toUpperCase() String.toUpperCase}.
		 */
		default String getNameUppercase() {
			return this.getNameLowercase().toUpperCase();
		}

		/**
		 * Capitalizes the name of the material as per {@link org.apache.commons.lang3.StringUtils#capitalize(String) StringUtils.capitalize}.
		 */
		default String getNameFormatted() {
			return StringUtils.capitalize(this.getNameLowercase());
		}

		// HACK
		String name(); /* not exactly hacky, but this method is provided by enum */

	}

	public static enum RenderAlgorithm implements IEnumNameFormattable {

		SURFACE_NETS(

				(event) -> ModUtil.renderChunkSurfaceNets(event),

				(event) -> ModUtil.renderBlockSurfaceNets(event)

		),

		MARCHING_CUBES(

				(event) -> ModUtil.renderChunkMarchingCubes(event),

				(event) -> ModUtil.renderBlockMarchingCubes(event)

		),

		;

		private final Function<RebuildChunkBlocksEvent, Integer>	renderAllBlocks;
		private final Consumer<RebuildChunkBlockEvent>				renderBlock;

		private RenderAlgorithm(final Function<RebuildChunkBlocksEvent, Integer> renderAllBlocks, final Consumer<RebuildChunkBlockEvent> renderBlock) {
			this.renderAllBlocks = renderAllBlocks;
			this.renderBlock = renderBlock;
		}

		public int renderAllBlocks(final RebuildChunkBlocksEvent event) {
			return this.renderAllBlocks.apply(event);
		}

		public void renderBlock(final RebuildChunkBlockEvent event) {
			this.renderBlock.accept(event);
		}

	}

	public static enum RenderType implements IEnumNameFormattable {

		ALL_BLOCKS,

		SINGLE_BLOCK,

		;

	}

}