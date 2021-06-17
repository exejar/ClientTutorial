package me.exejar.clienttutorial.hooks.mixin;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Minecraft.class)
@SideOnly(Side.CLIENT)
public class MixinMinecraft {



}
