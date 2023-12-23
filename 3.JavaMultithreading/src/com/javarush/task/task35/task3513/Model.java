package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();
    private boolean isSaveNeeded = true;
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    int score;
    int maxTile;

    public Model() {
        resetGameTiles();
    }

    public void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < gameTiles.length; i++) {
            for(int j = 0; j < gameTiles[0].length; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }
    //Сохраняем состояние игры
    private void saveState(Tile[][] tiles) {
        Tile[][] prevTiles = new Tile[tiles.length][tiles.length];

        for (int i = 0; i < prevTiles.length; i++) {
            for (int j = 0; j < prevTiles.length; j++) {
                prevTiles[i][j] = new Tile(tiles[i][j].value);
            }
        }
        previousStates.push(prevTiles);
        previousScores.push(score);
        isSaveNeeded = false;
    }
    //Восстанавливаем состояние игрового поля (аналог отмены действия)
    public void rollback() {

        if ((!previousStates.isEmpty()) && (!previousScores.isEmpty())) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

    // Эмуляция рандомного хода

    public void randomMove() {
        int random = ((int) (Math.random() * 100)) % 4;

        switch (random) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    //Добавляет плитку
    private void addTile() {
        List<Tile> tiles = getEmptyTiles();
        if (!tiles.isEmpty()) {
            int index = (int) (Math.random() * tiles.size());
            Tile random = tiles.get(index);
            random.value = Math.random() < 0.9 ? 2 : 4;
        }
    }
    //Возвращает список пустых плиток
    private List<Tile> getEmptyTiles() {
        List<Tile> emptyTiles = new ArrayList<>();

        for (int i = 0; i < gameTiles.length; i++) {
            for(int j = 0; j < gameTiles[0].length; j++) {
                if (gameTiles[i][j].isEmpty()) {
                    emptyTiles.add(gameTiles[i][j]);
                }
            }
        }

        return emptyTiles;
    }

    //Сжатие плиток, таким образом, чтобы все пустые плитки были справа, т.е. ряд {4, 2, 0, 4} становится рядом {4, 2, 4, 0}
    private boolean compressTiles(Tile[] tiles) {
        int insertPosition = 0;
        boolean result = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (!tiles[i].isEmpty()) {
                if (i != insertPosition) {
                    tiles[insertPosition] = tiles[i];
                    tiles[i] = new Tile();
                    result = true;
                }
                insertPosition++;
            }
        }
        //Arrays.stream(tiles).forEach(t -> System.out.println(t.value));
        return result;
    }
    //Слияние плиток одного номинала, т.е. ряд {4, 4, 2, 0} становится рядом {8, 2, 0, 0}. Обрати внимание, что ряд {4, 4, 4, 4} превратится в {8, 8, 0, 0}, а {4, 4, 4, 0} в {8, 4, 0, 0}
    private boolean mergeTiles(Tile[] tiles) {
        boolean result = false;
        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i].isEmpty()) {
                continue;
            }
            if (tiles[i].value == tiles[i + 1].value) {
                tiles[i].value += tiles[i + 1].value;
                if (tiles[i].value > maxTile) {
                    maxTile = tiles[i].value;
                }
                score += tiles[i].value;
                tiles[i + 1].value = 0;
                compressTiles(tiles);
                result = true;
            }
        }
        //Arrays.stream(tiles).forEach(t -> System.out.println(t.value));
        return result;
    }

    // Будет возвращать true, в случае, если вес плиток в массиве gameTiles отличается от веса плиток в верхнем массиве стека previousStates
    private boolean hasBoardChanged() {
        Tile[][] stackTiles = previousStates.peek();
        int score1 = 0;
        int score2 = 0;

        for (Tile[] tiles : gameTiles) {
            for (Tile tile : tiles) {
                score1 += tile.value;
            }
        }

        for (Tile[] tiles : stackTiles) {
            for (Tile tile : tiles) {
                score2 += tile.value;
            }
        }

        if (score1 != score2) {
            return true;
        } else {
            return false;
        }
    }

    public void autoMove() {
        Queue<MoveEfficiency> efficiencyQueue = new PriorityQueue<MoveEfficiency>(4, Collections.reverseOrder());
        efficiencyQueue.add(getMoveEfficiency(this::left));
        efficiencyQueue.add(getMoveEfficiency(this::right));
        efficiencyQueue.add(getMoveEfficiency(this::up));
        efficiencyQueue.add(getMoveEfficiency(this::down));
        efficiencyQueue.peek().getMove().move();
    }
    private MoveEfficiency getMoveEfficiency(Move move) {
        MoveEfficiency moveEfficiency = new MoveEfficiency(-1, 0, move);
        move.move();
        if (hasBoardChanged()) {
            moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        }
        rollback();

        return moveEfficiency;
    }
    public void left() {
        if (isSaveNeeded) {
            saveState(gameTiles);
        }
        boolean moveFlag = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) {
                moveFlag = true;
            }
        }
        if (moveFlag) {
            addTile();
        }
        isSaveNeeded = true;
    }

    public void up() {
        saveState(gameTiles);
        gameTiles = rotate(gameTiles);
        gameTiles = rotate(gameTiles);
        gameTiles = rotate(gameTiles);
        left();
        gameTiles = rotate(gameTiles);
    }

    public void right() {
        saveState(gameTiles);
        gameTiles = rotate(gameTiles);
        gameTiles = rotate(gameTiles);
        left();
        gameTiles = rotate(gameTiles);
        gameTiles = rotate(gameTiles);
    }

    public void down() {
        saveState(gameTiles);
        gameTiles = rotate(gameTiles);
        left();
        gameTiles = rotate(gameTiles);
        gameTiles = rotate(gameTiles);
        gameTiles = rotate(gameTiles);
    }

    private boolean isFull() {
        return getEmptyTiles().isEmpty();
    }

    public boolean canMove() {
        if (!isFull()) {
            return true;
        }
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles.length; j++) {
                Tile tile = gameTiles[i][j];
                if ((i < gameTiles.length - 1 && tile.value == gameTiles[i + 1][j].value) ||
                        (j < gameTiles.length - 1 && tile.value == gameTiles[i][j + 1].value)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Tile[][] rotate(Tile[][] tiles) {
        Tile[][] copyArray = new Tile[tiles.length][tiles.length];

        for (int i = 0; i < tiles.length; i++) {
            int k = 0;
            for (int j = tiles.length - 1; j >= 0; j--) {
                copyArray[i][k++] = tiles[j][i];
            }
        }

        return copyArray;
        /*for (Tile[] tile : tiles) {
            for (Tile tile1 : tile) {
                System.out.print(tile1.value + " ");
            }
            System.out.println();
        }
        System.out.println();
        for (Tile[] tile : copyArray) {
            for (Tile tile1 : tile) {
                System.out.print(tile1.value + " ");
            }
            System.out.println();
        }*/
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }
}
