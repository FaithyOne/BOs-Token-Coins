package de.markusbordihn.tokencoins.block.piggybank;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import de.markusbordihn.tokencoins.block.ModBlocks;
import de.markusbordihn.tokencoins.block.PiggyBankBlock;
import de.markusbordihn.tokencoins.block.entity.PiggyBankBlockEntity;
import de.markusbordihn.tokencoins.item.coin.CoinItem;

public class PiggyBankNoteBlockBlock extends PiggyBankBlock {

  public static final String NAME = "piggy_bank_note_block";

  public PiggyBankNoteBlockBlock(Properties properties) {
    super(properties);
  }

  @Override
  public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos,
      CollisionContext collisionContext) {
    return PiggyBankBlock.SHAPE_10_10_10_AABB;
  }

  @Override
  public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player,
      InteractionHand hand, BlockHitResult hitResult) {
    ItemStack handItemStack = player.getItemInHand(hand);
    if (handItemStack.is(Items.POPPY)) {
      playSound(player, SoundEvents.CHORUS_FLOWER_GROW);
      addParticle(level, ParticleTypes.NOTE, blockPos);
      addParticle(level, ParticleTypes.NOTE, blockPos);
      addParticle(level, ParticleTypes.NOTE, blockPos);
      return InteractionResult.SUCCESS;
    } else if (handItemStack.getItem() instanceof CoinItem) {
      addParticle(level, ParticleTypes.NOTE, blockPos);
    }
    return super.use(blockState, level, blockPos, player, hand, hitResult);
  }

  @Override
  public boolean consumeTokenCoinEffects(Level level, BlockPos blockPos, BlockState blockState,
      BlockEntity blockEntity, ItemStack itemStack, UseOnContext context) {
    level.playSound(null, blockPos, SoundEvents.NOTE_BLOCK_BELL, SoundSource.BLOCKS, 1.0F, 1.0F);
    return true;
  }

  @Override
  public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
    return new PiggyBankBlockEntity(ModBlocks.PIGGY_BANK_NOTE_BLOCK_ENTITY.get(), blockPos, blockState);
  }

}
