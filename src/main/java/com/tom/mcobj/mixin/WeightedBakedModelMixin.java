package com.tom.mcobj.mixin;

import java.util.List;
import java.util.function.Supplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.tom.mcobj.Access.MEA;

import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.WeightedBakedModel;
import net.minecraft.util.collection.Weighted;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;

@Mixin(value = WeightedBakedModel.class, priority = 99999)
@SuppressWarnings("unchecked")
public abstract class WeightedBakedModelMixin implements FabricBakedModel {
	@Shadow
	private List models;

	@Shadow
	private BakedModel defaultModel;

	@Shadow
	private int totalWeight;

	@Override
	public boolean isVanillaAdapter() {
		return false;
	}

	@Override
	public void emitBlockQuads(BlockRenderView blockView, BlockState state, BlockPos pos,
			Supplier<Random> randomSupplier, RenderContext context) {
		Random random_1 = randomSupplier.get();
		FabricBakedModel entry = ((MEA)Weighted.of(this.models, Math.abs((int)random_1.nextLong()) % this.totalWeight)).mcobj_getModel();
		entry.emitBlockQuads(blockView, state, pos, randomSupplier, context);
	}
}
