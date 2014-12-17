package com.lom.lotsomobsentity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.lom.lotsomobsinit.LotsOMobsItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntitySaberTooth extends EntityAgeableMob
{
    /**
     * Ticker used to determine the time remaining for this zombie to convert into a villager when cured.
     */
    private int conversionTime = 0;
	private float moveSpeed;

    public EntitySaberTooth(World par1World)
    {
        super(par1World);
        this.moveSpeed = 0.2F;
        this.setSize(2F, 2F);
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, this.moveSpeed + 0.2F, false));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityTriceratops.class, this.moveSpeed, true));
       // this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityEmpirosaurus.class, this.moveSpeed, true));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, this.moveSpeed));
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Items.wheat, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
        this.tasks.addTask(0, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        //this.tasks.addTask(3, new EntityAITempt(this, 0.25F, Item.wheat.itemID, false));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityTriceratops.class, 0, false));
      //  this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityEmpirosaurus.class, 0, false));
        this.experienceValue = 1000;
    }
    /**
     * This method returns a value to be applied directly to entity speed, this factor is less than 1 when a slowdown
     * potion effect is applied, more than 1 when a haste potion effect is applied and 2 for fleeing entities.
     */



@SideOnly(Side.CLIENT)

protected void applyEntityAttributes()
{
    super.applyEntityAttributes();
    this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(90.0D);
    this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000000298023224D);
    //this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
}


    /**
     * Returns true if the newer Entity AI code should be run
     */
    protected boolean isAIEnabled()
    {
        return true;
    }
    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote && !this.isChild())
        {
            float var1 = this.getBrightness(100.0F);

            if (var1 > 0.5F && this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)))
            {
                boolean var2 = true;
                if (var2)
                {
                    this.setFire(-99);
                }
            }
        }

        super.onLivingUpdate();
    }


     /**
     * Returns the amount of damage a mob should deal.
     */
    public int getAttackStrength(Entity par1Entity)
    {
        ItemStack var2 = this.getHeldItem();
        int var3 = 10;

 

        return var3;
    }
    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "lom:lion";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "lom:lion";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "lom:lion";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.playSound("lom:lion", 0.15F, 1.0F);
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected void dropFewItems(boolean par1, int par2)
    {
     int j = this.rand.nextInt(3) + this.rand.nextInt(1 + par2);
     int k;

     for (k = 0; k < j; ++k)
     {
         this.dropItem(LotsOMobsItems.WoolyFur, 2);
     }

     j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + par2);

     for (k = 0; k < j; ++k)
     {
         if (this.isBurning())
         {
             this.dropItem(LotsOMobsItems.LionMeat, 2);
         }
         else
         {
             this.dropItem(LotsOMobsItems.RawLion, 2);
         }
     }
    }
    /**
     * How large the spider should be scaled.
     */
    public float spiderScaleAmount()
    {
return 1.8F;
    }
    /**
     * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
     */
    public EntitySaberTooth spawnBabyAnimal(EntityAgeable par1EntityAgeable)
    {
        return new EntitySaberTooth(this.worldObj);
    }

    public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
    {
        return this.spawnBabyAnimal(par1EntityAgeable);
    }

}
