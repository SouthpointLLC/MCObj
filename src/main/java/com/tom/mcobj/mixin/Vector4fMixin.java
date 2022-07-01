package com.tom.mcobj.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.tom.mcobj.Access.V4A;

import net.minecraft.util.math.Vector4f;

@Mixin(Vector4f.class)
public class Vector4fMixin implements V4A {
	@Shadow
	private float w;

	@Override
	public float mcobj_getW() {
		return w;
	}
}
