package com.tom.mcobj.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.tom.mcobj.Access.BXA;

import net.minecraft.client.model.ModelPart.Cuboid;
import net.minecraft.util.math.Vec3f;

@Mixin(Cuboid.class)
public class BoxMixin implements BXA {
	public int mcobj_texU, mcobj_texV;
	public Vec3f mcobj_delta;

	@Inject(method = "<init>(IIFFFFFFFFFZFF)V", at = @At("RETURN"))
	public void onInit(int iu, int iv, float minX, float minY, float minZ,
			float xs, float ys, float zs, float dx, float dy, float dz,
			boolean boolean_1, float u, float v, CallbackInfo info) {
		mcobj_texU = iu;
		mcobj_texV = iv;
		mcobj_delta = new Vec3f(dx, dy, dz);
	}

	@Override
	public int mcobj_getTexU() {
		return mcobj_texU;
	}

	@Override
	public int mcobj_getTexV() {
		return mcobj_texV;
	}

	@Override
	public Vec3f mcobj_getDelta() {
		return mcobj_delta;
	}
}
