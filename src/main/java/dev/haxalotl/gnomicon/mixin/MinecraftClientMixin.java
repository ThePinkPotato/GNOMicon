package dev.haxalotl.gnomicon.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = MinecraftClient.class, priority = Integer.MAX_VALUE)
public class MinecraftClientMixin {
    @ModifyReturnValue(method="getWindowTitle", at = @At("RETURN"))
    private String gnomicon$getWindowTitle(String original) {
        return System.getProperty("title") != null ? System.getProperty("title") : "Minecraft";
    }
}
