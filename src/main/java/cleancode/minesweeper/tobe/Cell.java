package cleancode.minesweeper.tobe;

public class Cell {

    private static final String FLAG_SIGN = "⚑";
    private static final String LAND_MINE_SIGN = "☼";
    private static final String UNCHECKED_SIGN = "□";
    private static final String EMPTY_SIGN = "■";

    private final String sign;
    private int nearbyLandMineCount;
    private boolean isLandMine;
    private boolean isFlagged;
    private boolean isOpened;

    // Cell이 가진 속성 : 근처 지뢰 숫자, 지뢰 여부
    // Cell의 상태 : 깃발 유무, open/close, 사용자의 확인
    private Cell(String sign, int nearbyLandMineCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        this.sign = sign;
        this.nearbyLandMineCount = nearbyLandMineCount;
        this.isLandMine = isLandMine;
        this.isFlagged = isFlagged;
        this.isOpened = isOpened;
    }

    public static Cell of(String sign, int nearbyLandMineCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        return new Cell(sign, nearbyLandMineCount, isLandMine, isFlagged, isOpened);
    }
    
    public static Cell create() {
        return of("", 0, false, false, false);
    }

    public boolean isClosed() {
        return UNCHECKED_SIGN.equals(this.sign);
    }

    public void turnOnLandMine() {
        this.isLandMine = true;
    }

    public void updateNearbyLandMineCount(int count) {
        this.nearbyLandMineCount = count;
    }

    public void flag() {
        this.isFlagged = true;
    }

    public boolean isChecked() {
        return isFlagged || isOpened;
    }

    public boolean isLandMine() {
        return isLandMine;
    }

    public void open() {
        this.isOpened = true;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public boolean hasLandMineCount() {
        return nearbyLandMineCount > 0;
    }

    public String getSign() {
        if (isOpened){
            if (isLandMine) {
                return LAND_MINE_SIGN;
            }
            if (hasLandMineCount()) {
                return String.valueOf(nearbyLandMineCount);
            }
            return EMPTY_SIGN;
        }
        if (isFlagged) {
            return FLAG_SIGN;
        }

        return UNCHECKED_SIGN;
    }
}
