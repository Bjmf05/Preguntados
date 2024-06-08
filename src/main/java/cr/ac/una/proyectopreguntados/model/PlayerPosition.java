package cr.ac.una.proyectopreguntados.model;

/**
 *
 * @author PC
 */
public class PlayerPosition {

    public final int[][] playerOnePositions2 = {
        {225, 210},
        {303, 136},
        {429, 136},
        {508, 210},};
    public final int[][] playerTwoPositions2 = {
        {225, 342},
        {304, 417},
        {429, 427},
        {508, 342},};

    public final int[][] playerOnePositions3 = {
        {476, 178},
        {411, 142},
        {328, 141},
        {260, 179},};

    public final int[][] playerTwoPositions3 = {
        {221, 255},
        {219, 329},
        {254, 396},
        {321, 439},};

    public final int[][] playerThreePositions3 = {
        {416, 435},
        {482, 396},
        {518, 327},
        {522, 253},};

    public final int[][] playerOnePositions4 = {
        {332, 111},
        {276, 137},
        {237, 177},
        {215, 227},};
    public final int[][] playerTwoPositions4 = {
        {216, 298},
        {238, 351},
        {277, 389},
        {333, 411},};
    public final int[][] playerThreePositions4 = {
        {399, 414},
        {452, 390},
        {492, 353},
        {519, 297},};
    public final int[][] playerFourPositions4 = {
        {515, 226},
        {494, 176},
        {451, 135},
        {398, 111},};

    public final int[][] playerOnePositions5 = {
        {435, 151},
        {391, 135},
        {346, 136},
        {303, 147},};

    public final int[][] playerTwoPositions5 = {
        {260, 185},
        {233, 223},
        {220, 268},
        {218, 311},};

    public final int[][] playerThreePositions5 = {
        {233, 363},
        {258, 399},
        {299, 425},
        {343, 439},};

    public final int[][] playerFourPositions5 = {
        {397, 441},
        {442, 425},
        {479, 400},
        {508, 363},};

    public final int[][] playerFivePositions5 = {
        {523, 309},
        {520, 265},
        {505, 220},
        {481, 185},};

    public final int[][] playerOnePositions6 = {
        {341, 108},
        {306, 120},
        {272, 138},
        {246, 166},};
    public final int[][] playerTwoPositions6 = {
        {484, 166},
        {455, 138},
        {422, 119},
        {388, 107},};
    public final int[][] playerThreePositions6 = {
        {222, 206},
        {216, 243},
        {215, 281},
        {225, 319},};
    public final int[][] playerFourPositions6 = {
        {508, 315},
        {517, 278},
        {517, 240},
        {508, 202},};

    public final int[][] playerFivePositions6 = {
        {247, 357},
        {274, 383},
        {304, 403},
        {342, 417},};
    public final int[][] playerSixPositions6 = {
        {389, 415},
        {426, 407},
        {457, 388},
        {484, 360},};
    public int[][] playerOnePositions(int numPlayers) {
        switch (numPlayers) {
            case 2:
                return playerOnePositions2;
            case 3:
                return playerOnePositions3;
            case 4:
                return playerOnePositions4;
            case 5:
                return playerOnePositions5;
            case 6:
                return playerOnePositions6;
            default:
                return null;
        }
    }
    public int[][] playerTwoPositions(int numPlayers) {
        switch (numPlayers) {
            case 2:
                return playerTwoPositions2;
            case 3:
                return playerTwoPositions3;
            case 4:
                return playerTwoPositions4;
            case 5:
                return playerTwoPositions5;
            case 6:
                return playerTwoPositions6;
            default:
                return null;
        }
    }
    public int[][] playerThreePositions(int numPlayers) {
        switch (numPlayers) {
            case 3:
                return playerThreePositions3;
            case 4:
                return playerThreePositions4;
            case 5:
                return playerThreePositions5;
            case 6:
                return playerThreePositions6;
            default:
                return null;
        }
    }
    public int[][] playerFourPositions(int numPlayers) {
        switch (numPlayers) {
            case 4:
                return playerFourPositions4;
            case 5:
                return playerFourPositions5;
            case 6:
                return playerFourPositions6;
            default:
                return null;
        }
    }
    public int[][] playerFivePositions(int numPlayers) {
        switch (numPlayers) {
            case 5:
                return playerFivePositions5;
            case 6:
                return playerFivePositions6;
            default:
                return null;
        }
    }
}
