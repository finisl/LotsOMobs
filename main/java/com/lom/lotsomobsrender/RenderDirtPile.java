package com.lom.lotsomobsrender;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.lom.lotsomobscore.LotsOMobs;
import com.lom.lotsomobsentity.EntityDirtPile;
import com.lom.lotsomobsentity.EntityMole;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class RenderDirtPile extends RenderLiving
{
	private static final ResourceLocation DirtPiletexture = new ResourceLocation(LotsOMobs.modid, "LotsOMobs/Mobs/Mole.png");
	private static final ResourceLocation DirtPiletextureS = new ResourceLocation(LotsOMobs.modid, "LotsOMobs/Mobs/MoleSand.png");
	private static final ResourceLocation DirtPiletextureRS = new ResourceLocation(LotsOMobs.modid, "LotsOMobs/Mobs/MoleRSand.png");
	 protected ResourceLocation func_110872_a(EntityDirtPile DirtPile)
	    {
		 	if(DirtPile.worldObj.getBlock((int)DirtPile.posX, (int)DirtPile.posY - 1, (int)DirtPile.posZ) == Blocks.sand)
		 	{
	        return DirtPiletextureS;
		 	}
		 	else
		 	{
		 	return DirtPiletexture;
		 	}
	    }
	 @Override
	    protected ResourceLocation getEntityTexture(Entity par1Entity)
	    {
	        return this.func_110872_a((EntityDirtPile)par1Entity);
	    }
	
    public RenderDirtPile(ModelBase var1, float var2)
    {
        super(var1, var2);
    }

    public void renderDirtPile(EntityDirtPile var1, double var2, double var4, double var6, float var8, float var9)
    {
        super.doRender(var1, var2, var4, var6, var8, var9);
    }

    public void doRenderLiving(EntityLiving var1, double var2, double var4, double var6, float var8, float var9)
    {
        this.renderDirtPile((EntityDirtPile)var1, var2, var4, var6, var8, var9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        this.renderDirtPile((EntityDirtPile)var1, var2, var4, var6, var8, var9);
    }
    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void scaleSpider(EntityDirtPile par1EntityDirtPile, float par2)
    {
    	float f = par1EntityDirtPile.size;
        GL11.glScalef(f, f, f);
    }
    
    
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.scaleSpider((EntityDirtPile)par1EntityLivingBase, par2);
    }
    

    
}