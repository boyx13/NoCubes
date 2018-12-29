package io.github.cadiboo.nocubes.client;

import io.github.cadiboo.nocubes.NoCubes;
import io.github.cadiboo.nocubes.config.ModConfig;
import io.github.cadiboo.nocubes.util.ModReference;
import io.github.cadiboo.renderchunkrebuildchunkhooks.event.RebuildChunkBlockEvent;
import io.github.cadiboo.renderchunkrebuildchunkhooks.event.RebuildChunkBlockRenderInLayerEvent;
import io.github.cadiboo.renderchunkrebuildchunkhooks.event.RebuildChunkBlockRenderInTypeEvent;
import io.github.cadiboo.renderchunkrebuildchunkhooks.event.RebuildChunkPostEvent;
import io.github.cadiboo.renderchunkrebuildchunkhooks.event.RebuildChunkPreEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.util.ReportedException;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.minecraftforge.fml.relauncher.Side.CLIENT;

/**
 * Subscribe to events that should be handled on the PHYSICAL CLIENT in this class
 *
 * @author Cadiboo
 */
@Mod.EventBusSubscriber(modid = ModReference.MOD_ID, value = CLIENT)
public final class ClientEventSubscriber {

	@SubscribeEvent
	public static void onRebuildChunkPreEvent(final RebuildChunkPreEvent event) {

		if (!NoCubes.isEnabled()) {
			return;
		}

		if (ModConfig.debug.debugEnabled) {
			return;
		}

		Minecraft.getMinecraft().profiler.startSection("Rendering smooth world in Pre");

		if (ModConfig.shouldExtendLiquids)
			ClientUtil.calculateExtendedLiquids(event);

		try {
			ModConfig.activeStableRenderingAlgorithm.renderPre(event);
		} catch (Exception e) {
			CrashReport crashReport = new CrashReport("Error rendering smooth chunk in Pre event!", e);
			crashReport.makeCategory("Rendering smooth chunk");
			throw new ReportedException(crashReport);
		}
		Minecraft.getMinecraft().profiler.endSection();

	}

	@SubscribeEvent
	public static void onRebuildChunkBlockRenderInLayerEvent(final RebuildChunkBlockRenderInLayerEvent event) {

		if (!NoCubes.isEnabled()) {
			return;
		}

		if (ModConfig.debug.debugEnabled) {
			return;
		}

		try {
			Minecraft.getMinecraft().profiler.startSection("Rendering smooth world in Layer");
		} catch (Exception e) {
			CrashReport crashReport = new CrashReport("Error rendering smooth chunk in Layer event!", e);
			crashReport.makeCategory("Rendering smooth chunk");
			throw new ReportedException(crashReport);
		}
		ModConfig.activeStableRenderingAlgorithm.renderLayer(event);

		Minecraft.getMinecraft().profiler.endSection();
	}

	@SubscribeEvent
	public static void onRebuildChunkBlockRenderInTypeEvent(final RebuildChunkBlockRenderInTypeEvent event) {

		if (!NoCubes.isEnabled()) {
			return;
		}

		if (ModConfig.debug.debugEnabled) {
			return;
		}

		Minecraft.getMinecraft().profiler.startSection("Rendering smooth world in Type");

		try {
			ModConfig.activeStableRenderingAlgorithm.renderType(event);
		} catch (Exception e) {
			CrashReport crashReport = new CrashReport("Error rendering smooth chunk in Type event!", e);
			crashReport.makeCategory("Rendering smooth chunk");
			throw new ReportedException(crashReport);
		}
		Minecraft.getMinecraft().profiler.endSection();

	}

	@SubscribeEvent
	public static void onRebuildChunkBlockEvent(final RebuildChunkBlockEvent event) {

		if (!NoCubes.isEnabled()) {
			return;
		}

		if (ModConfig.debug.debugEnabled) {
			return;
		}

		Minecraft.getMinecraft().profiler.startSection("Rendering smooth world in Block");

		try {
			ModConfig.activeStableRenderingAlgorithm.renderBlock(event);
		} catch (Exception e) {
			CrashReport crashReport = new CrashReport("Error rendering smooth chunk in Block event!", e);
			crashReport.makeCategory("Rendering smooth chunk");
			throw new ReportedException(crashReport);
		}

		if (ModConfig.shouldExtendLiquids)
			ClientUtil.handleExtendedLiquidRender(event);

		Minecraft.getMinecraft().profiler.endSection();

	}

	@SubscribeEvent
	public static void onRebuildChunkPostEvent(final RebuildChunkPostEvent event) {

		if (!NoCubes.isEnabled()) {
			return;
		}

		if (ModConfig.debug.debugEnabled) {
			return;
		}

		Minecraft.getMinecraft().profiler.startSection("Rendering smooth world in Post");

		if (ModConfig.shouldExtendLiquids)
			ClientUtil.cleanupExtendedLiquids(event);

		try {
			ModConfig.activeStableRenderingAlgorithm.renderPost(event);
		} catch (Exception e) {
			CrashReport crashReport = new CrashReport("Error rendering smooth chunk in Post event!", e);
			crashReport.makeCategory("Rendering smooth chunk");
			throw new ReportedException(crashReport);
		}

		Minecraft.getMinecraft().profiler.endSection();

	}

}
