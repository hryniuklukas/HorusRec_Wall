package com.github.hryniuklukas.horus.recruitment.model.interfaces;
import java.util.List;

public interface CompositeBlock extends Block {
    List<Block> getBlocks();
}
