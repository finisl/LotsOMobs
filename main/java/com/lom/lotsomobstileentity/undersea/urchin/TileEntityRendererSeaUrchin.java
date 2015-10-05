package com.lom.lotsomobstileentity.undersea.urchin;

import org.lwjgl.opengl.GL11;

import com.lom.lotsomobscore.LotsOMobs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityRendererSeaUrchin extends TileEntitySpecialRenderer
{
	
	public TileEntityRendererSeaUrchin(){
		model = new ModelSeaUrchin();
		}

		public void renderAModelAt(TileEntitySeaUrchin tile, double d, double d1, double d2, float f) {

		int rotation = 0;
		if(tile.getWorldObj() != null)
		{
		rotation = tile.getBlockMetadata();
		}
		
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(LotsOMobs.modid, "LotsOMobs/Blocks/SeaUrchin.png"));	

		GL11.glPushMatrix();
		GL11.glTranslatef((float)d, (float)d1+ 1.5F, (float)d2);
		GL11.glScalef(1, -1, -1);
		GL11.glRotatef(rotation *90, 0.0F, 1.0F, 0.0F);
		model.renderAll();
		GL11.glPopMatrix(); //end

		}

		private ModelSeaUrchin model;

		public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
		{
		this.renderAModelAt((TileEntitySeaUrchin)par1TileEntity, par2, par4, par6, par8);
		}
}