package io.github.cadiboo.nocubes.util;

import javax.annotation.Nonnull;

/**
 * @author Cadiboo
 */
public class SmoothableCache extends XYZCache implements AutoCloseable {

	@Nonnull
	private boolean[] cache;

	private static final ThreadLocal<SmoothableCache> POOL = ThreadLocal.withInitial(() -> new SmoothableCache(0, 0, 0));

	private SmoothableCache(final int sizeX, final int sizeY, final int sizeZ) {
		super(sizeX, sizeY, sizeZ);
		cache = new boolean[sizeX * sizeY * sizeZ];
	}

	@Nonnull
	public boolean[] getSmoothableCache() {
		return cache;
	}

	@Nonnull
	public static SmoothableCache retain(final int sizeX, final int sizeY, final int sizeZ) {

		final SmoothableCache pooled = POOL.get();

		if (pooled.sizeX == sizeX && pooled.sizeY == sizeY && pooled.sizeZ == sizeZ) {
			return pooled;
		}

		pooled.sizeX = sizeX;
		pooled.sizeY = sizeY;
		pooled.sizeZ = sizeZ;

		final int size = sizeX * sizeY * sizeZ;

		if (pooled.cache.length < size) {
			pooled.cache = new boolean[size];
		}

		return pooled;
	}

	@Override
	public void close() {
	}

}