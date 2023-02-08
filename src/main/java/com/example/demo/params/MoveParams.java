package com.example.demo.params;

import fr.le_campus_numerique.square_games.engine.CellPosition;

import javax.validation.constraints.NotNull;

public record MoveParams(@NotNull CellPosition position){
}
