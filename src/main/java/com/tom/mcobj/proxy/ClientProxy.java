package com.tom.mcobj.proxy;

import com.tom.mcobj.EntityModelLoader;
import com.tom.mcobj.Remap;
import com.tom.mcobj.forge.Fabric.OBJLoader;

import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.*;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy {
	public static EntityModelLoader loader;
	@Override
	public World getWorld() {
		return MinecraftClient.getInstance().world;
	}
	@Override
	public void construct() {
		Remap.init();

	}

	private void loadModelLoader() {
		ResourceManager mngr = MinecraftClient.getInstance().getResourceManager();
		if(mngr instanceof ReloadableResourceManagerImpl) {
			((ReloadableResourceManagerImpl)mngr).registerReloader(new SinglePreparationResourceReloader<EntityModelLoader>(){

				@Override
				protected EntityModelLoader prepare(ResourceManager resourceManagerIn, Profiler profilerIn) {
					OBJLoader.INSTANCE.manager = resourceManagerIn;
					profilerIn.startTick();
					EntityModelLoader modelLoader = new EntityModelLoader(resourceManagerIn);
					profilerIn.endTick();
					return modelLoader;
				}

				@Override
				protected void apply(EntityModelLoader oml, ResourceManager resourceManagerIn, Profiler profilerIn) {
					loader = oml;
					Remap.clearCache();
				}
			});
			loader = new EntityModelLoader(mngr);
		}else{
			loader = new EntityModelLoader(mngr);
		}
	}

	/*@SubscribeEvent
	public void key(InputEvent.KeyInputEvent e){
		if(Remap.RELOAD_KEY_ENABLE && e.getAction() == GLFW.GLFW_RELEASE && e.getKey() == GLFW.GLFW_KEY_KP_7){
			Remap.clearCache();
		}
	}*/
	@Override
	public void setup() {
		loadModelLoader();
	}
}
