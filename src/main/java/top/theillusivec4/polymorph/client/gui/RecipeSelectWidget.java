/*
 * Copyright (c) 2020 C4
 *
 * This file is part of Polymorph, a mod made for Minecraft.
 *
 * Polymorph is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Polymorph is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Polymorph.  If not, see <https://www.gnu.org/licenses/>.
 */

package top.theillusivec4.polymorph.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import top.theillusivec4.polymorph.Polymorph;

public class RecipeSelectWidget extends Widget {

  private static final ResourceLocation TOGGLE = new ResourceLocation(Polymorph.MODID,
      "textures/gui/toggle.png");
  public IRecipe<CraftingInventory> recipe;
  public CraftingInventory craftingMatrix;

  public RecipeSelectWidget(CraftingInventory craftingMatrix, IRecipe<CraftingInventory> recipe) {
    super(0, 0, 25, 25, StringTextComponent.EMPTY/**/);
    this.recipe = recipe;
    this.craftingMatrix = craftingMatrix;
  }

  public void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public void renderButton(@Nonnull MatrixStack matrixStack, int p_renderButton_1_,
      int p_renderButton_2_, float p_renderButton_3_) {
    Minecraft minecraft = Minecraft.getInstance();
    minecraft.getTextureManager().bindTexture(TOGGLE);
    int i = 16;
    int j = 0;

    if (this.x + 25 > p_renderButton_1_ && this.x <= p_renderButton_1_) {

      if (this.y + 25 > p_renderButton_2_ && this.y <= p_renderButton_2_) {
        j += 25;
      }
    }
    this.blit(matrixStack, this.x, this.y, i, j, this.width, this.height);
    int k = 4;
    minecraft.getItemRenderer()
        .renderItemAndEffectIntoGUI(this.recipe.getCraftingResult(this.craftingMatrix), this.x + k,
            this.y + k);
  }

  public List<ITextComponent> getTooltipText(Screen screen) {
    return screen.getTooltipFromItem(this.recipe.getCraftingResult(this.craftingMatrix));
  }

  @Override
  public int getWidth() {
    return 25;
  }

  @Override
  protected boolean isValidClickButton(int p_isValidClickButton_1_) {
    return p_isValidClickButton_1_ == 0 || p_isValidClickButton_1_ == 1;
  }
}