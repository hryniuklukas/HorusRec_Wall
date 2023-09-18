package com.github.hryniuklukas.horus.recruitment.model;


import com.github.hryniuklukas.horus.recruitment.model.interfaces.Block;
import com.github.hryniuklukas.horus.recruitment.model.interfaces.CompositeBlock;
import com.github.hryniuklukas.horus.recruitment.model.interfaces.Structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private List<Block> blocks;
    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    public Optional<Block> findAnyBlockByColorRec(List<Block> blocks, String color) {
        for (Block element :
                blocks) {
            if (element.getColor().equals(color)) {
                return Optional.of(element);
            }
            if (element instanceof CompositeBlock) {
                findAnyBlockByColorRec(((CompositeBlock) element).getBlocks(), color);
            }
        }
        return Optional.empty();
    }

    public List<Block> findAllBlocksByMaterialRec(List<Block> blocks, String material) {
        List<Block> foundBlocks = new ArrayList<>();
        for (Block element :
                blocks) {
            if (element.getMaterial().equals(material)) {
                foundBlocks.add(element);
            }
            if (element instanceof CompositeBlock) {
                foundBlocks.addAll(findAllBlocksByMaterialRec(((CompositeBlock) element).getBlocks(), material));
            }
        }
        return foundBlocks;
    }

    public int countAllBlocksRec(List<Block> blocks) {
        int count = 0;
        for (Block element :
                blocks) {
            count++;
            if (element instanceof CompositeBlock) {
                count += countAllBlocksRec(((CompositeBlock) element).getBlocks());
            }
        }
        return count;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return findAnyBlockByColorRec(this.blocks, color);
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return findAllBlocksByMaterialRec(this.blocks, material);
    }

    @Override
    public int count() {
        return countAllBlocksRec(this.blocks);
    }
}
