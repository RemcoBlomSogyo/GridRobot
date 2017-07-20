package nl.sogyo.rblom.gridrobot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import nl.sogyo.rblom.lee.CommandLine;
import nl.sogyo.rblom.lee.ShortestPath;

public class MainActivity extends AppCompatActivity {

    ImageView[] selectedView = new ImageView[]{null};
    ImageView[] robotLocation = new ImageView[]{null};
    String robotDirection = null;
    ImageView[] flagLocation = new ImageView[]{null};
    ArrayList<ImageView> obstacleLocations = new ArrayList<>();
    ImageView[][] tileGrid = new ImageView[7][7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView tankButton = (ImageView) findViewById(R.id.tankButton);
        ImageView accessDeniedButton = (ImageView) findViewById(R.id.accessDeniedButton);
        ImageView turnButton = (ImageView) findViewById(R.id.turnButton);
        ImageView wastebinButton = (ImageView) findViewById(R.id.wastebinButton);
        ImageView flagButton = (ImageView) findViewById(R.id.flagButton);
        ImageView goButton = (ImageView) findViewById(R.id.goButton);

        tileGrid[0][0] = (ImageView) findViewById(R.id.imageView);
        tileGrid[1][0] = (ImageView) findViewById(R.id.imageView2);
        tileGrid[2][0] = (ImageView) findViewById(R.id.imageView3);
        tileGrid[3][0] = (ImageView) findViewById(R.id.imageView4);
        tileGrid[4][0] = (ImageView) findViewById(R.id.imageView5);
        tileGrid[5][0] = (ImageView) findViewById(R.id.imageView6);
        tileGrid[6][0] = (ImageView) findViewById(R.id.imageView7);
        tileGrid[0][1] = (ImageView) findViewById(R.id.imageView8);
        tileGrid[1][1] = (ImageView) findViewById(R.id.imageView9);
        tileGrid[2][1] = (ImageView) findViewById(R.id.imageView10);
        tileGrid[3][1] = (ImageView) findViewById(R.id.imageView11);
        tileGrid[4][1] = (ImageView) findViewById(R.id.imageView12);
        tileGrid[5][1] = (ImageView) findViewById(R.id.imageView13);
        tileGrid[6][1] = (ImageView) findViewById(R.id.imageView14);
        tileGrid[0][2] = (ImageView) findViewById(R.id.imageView15);
        tileGrid[1][2] = (ImageView) findViewById(R.id.imageView16);
        tileGrid[2][2] = (ImageView) findViewById(R.id.imageView17);
        tileGrid[3][2] = (ImageView) findViewById(R.id.imageView18);
        tileGrid[4][2] = (ImageView) findViewById(R.id.imageView19);
        tileGrid[5][2] = (ImageView) findViewById(R.id.imageView20);
        tileGrid[6][2] = (ImageView) findViewById(R.id.imageView21);
        tileGrid[0][3] = (ImageView) findViewById(R.id.imageView22);
        tileGrid[1][3] = (ImageView) findViewById(R.id.imageView23);
        tileGrid[2][3] = (ImageView) findViewById(R.id.imageView24);
        tileGrid[3][3] = (ImageView) findViewById(R.id.imageView25);
        tileGrid[4][3] = (ImageView) findViewById(R.id.imageView26);
        tileGrid[5][3] = (ImageView) findViewById(R.id.imageView27);
        tileGrid[6][3] = (ImageView) findViewById(R.id.imageView28);
        tileGrid[0][4] = (ImageView) findViewById(R.id.imageView29);
        tileGrid[1][4] = (ImageView) findViewById(R.id.imageView30);
        tileGrid[2][4] = (ImageView) findViewById(R.id.imageView31);
        tileGrid[3][4] = (ImageView) findViewById(R.id.imageView32);
        tileGrid[4][4] = (ImageView) findViewById(R.id.imageView33);
        tileGrid[5][4] = (ImageView) findViewById(R.id.imageView34);
        tileGrid[6][4] = (ImageView) findViewById(R.id.imageView35);
        tileGrid[0][5] = (ImageView) findViewById(R.id.imageView36);
        tileGrid[1][5] = (ImageView) findViewById(R.id.imageView37);
        tileGrid[2][5] = (ImageView) findViewById(R.id.imageView38);
        tileGrid[3][5] = (ImageView) findViewById(R.id.imageView39);
        tileGrid[4][5] = (ImageView) findViewById(R.id.imageView40);
        tileGrid[5][5] = (ImageView) findViewById(R.id.imageView41);
        tileGrid[6][5] = (ImageView) findViewById(R.id.imageView42);
        tileGrid[0][6] = (ImageView) findViewById(R.id.imageView43);
        tileGrid[1][6] = (ImageView) findViewById(R.id.imageView44);
        tileGrid[2][6] = (ImageView) findViewById(R.id.imageView45);
        tileGrid[3][6] = (ImageView) findViewById(R.id.imageView46);
        tileGrid[4][6] = (ImageView) findViewById(R.id.imageView47);
        tileGrid[5][6] = (ImageView) findViewById(R.id.imageView48);
        tileGrid[6][6] = (ImageView) findViewById(R.id.imageView49);

        tankButton.setTag(R.drawable.tank_button_up);
        tankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aTileIsSelected()) {
                    deletePreviousRobotLocation();
                    clearTileAndDeleteLocation();
                    switch ((int) tankButton.getTag()) {
                        case R.drawable.tank_button_up:
                            selectedView[0].setImageResource(R.drawable.selected_tile_tank_up);
                            selectedView[0].setTag(R.drawable.selected_tile_tank_up);
                            robotDirection = "north";
                            break;
                        case R.drawable.tank_button_right:
                            selectedView[0].setImageResource(R.drawable.selected_tile_tank_right);
                            selectedView[0].setTag(R.drawable.selected_tile_tank_right);
                            robotDirection = "east";
                            break;
                        case R.drawable.tank_button_down:
                            selectedView[0].setImageResource(R.drawable.selected_tile_tank_down);
                            selectedView[0].setTag(R.drawable.selected_tile_tank_down);
                            robotDirection = "south";
                            break;
                        case R.drawable.tank_button_left:
                            selectedView[0].setImageResource(R.drawable.selected_tile_tank_left);
                            selectedView[0].setTag(R.drawable.selected_tile_tank_left);
                            robotDirection = "west";
                    }
                    robotLocation[0] = selectedView[0];
                }
                printLocations();
            }
        });

        accessDeniedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aTileIsSelected()) {
                    clearTileAndDeleteLocation();
                    if (selectedTileIsRobotLocation()) {
                        robotLocation[0] = null;
                    }
                    selectedView[0].setImageResource(R.drawable.selected_tile_access_denied);
                    selectedView[0].setTag(R.drawable.selected_tile_access_denied);
                    if (!selectedTileIsAnObstacleLocation()) {
                        obstacleLocations.add(selectedView[0]);
                    }
                }
                printLocations();
            }
        });

        turnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch ((int) tankButton.getTag()) {
                    case R.drawable.tank_button_up:
                        tankButton.setImageResource(R.drawable.tank_button_right);
                        tankButton.setTag(R.drawable.tank_button_right);
                        break;
                    case R.drawable.tank_button_right:
                        tankButton.setImageResource(R.drawable.tank_button_down);
                        tankButton.setTag(R.drawable.tank_button_down);
                        break;
                    case R.drawable.tank_button_down:
                        tankButton.setImageResource(R.drawable.tank_button_left);
                        tankButton.setTag(R.drawable.tank_button_left);
                        break;
                    case R.drawable.tank_button_left:
                        tankButton.setImageResource(R.drawable.tank_button_up);
                        tankButton.setTag(R.drawable.tank_button_up);
                }
                printLocations();
            }
        });

        wastebinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aTileIsSelected()) {
                    clearTileAndDeleteLocation();
                }
                printLocations();
            }
        });

        flagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aTileIsSelected()) {
                    deletePreviousFlagLocation();
                    clearTileAndDeleteLocation();
                    if (selectedTileIsRobotLocation()) {
                        robotLocation[0] = null;
                    }
                    selectedView[0].setImageResource(R.drawable.selected_tile_flag);
                    selectedView[0].setTag(R.drawable.selected_tile_flag);
                    flagLocation[0] = selectedView[0];
                }
                printLocations();
            }
        });

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //List<int[]> shortestPath = findShortestPath();
//                List<int[]> shortestPath = new ArrayList<>();
//                shortestPath.add(new int[]{0, 1});
//                shortestPath.add(new int[]{0, 0});
//                shortestPath.add(new int[]{1, 0});
//                shortestPath.add(new int[]{2, 0});
//                shortestPath.add(new int[]{3, 0});
//                shortestPath.add(new int[]{4, 0});
//                markShortestPathGreen(shortestPath);

                int[] robot = new int[]{0, 0};
                int[] flag = new int[]{6, 0};

                if (robotLocation[0] != null && flagLocation[0] != null) {
                    int[] robotCoordinates = parseImageViewToCoordinates(robotLocation[0]);
                    int[] flagCoordinates = parseImageViewToCoordinates(flagLocation[0]);
                    ArrayList<int[]> obstaclesCoordinates = new ArrayList<>();
                    for (ImageView obstacleLocation : obstacleLocations) {
                        obstaclesCoordinates.add(parseImageViewToCoordinates(obstacleLocation));
                    }
                    ArrayList<int[]> shortestPathCoordinates = calculateShortestPath(robotCoordinates, flagCoordinates, obstaclesCoordinates);
                    CommandLine commandLine = new CommandLine();
                    String commandsForRobot = commandLine.computeLine(shortestPathCoordinates, robotDirection);

                    sendCommandsToRobot(commandsForRobot);
                }
            }
        });

        for (int y = 0 ; y < 7 ; y++) {
            for (int x = 0 ; x < 7 ; x++) {
                final int xCor = x;
                final int yCor = y;
                tileGrid[x][y].setTag(R.drawable.tile);
                tileGrid[x][y].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (tileIsNotAlreadySelected(tileGrid[xCor][yCor])) {
                            deselectTile(selectedView[0]);
                            selectTile(tileGrid[xCor][yCor]);
                        } else {
                            deselectTile(tileGrid[xCor][yCor]);
                        }
                    }
                });
            }
        }

//        tileGrid[0][0].setTag(R.drawable.tile);
//        tileGrid[0][0].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (tileIsNotAlreadySelected(tileGrid[0][0])) {
//                    deselectTile(selectedView[0]);
//                    selectTile(tileGrid[0][0]);
//                } else {
//                    deselectTile(tileGrid[0][0]);
//                }
//            }
//        });
//
//        tileGrid[1][0].setTag(R.drawable.tile);
//        tileGrid[1][0].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (tileIsNotAlreadySelected(tileGrid[1][0])) {
//                    deselectTile(selectedView[0]);
//                    selectTile(tileGrid[1][0]);
//                } else {
//                    deselectTile(tileGrid[1][0]);
//                }
//            }
//        });
//
//        tileGrid[2][0].setTag(R.drawable.tile);
//        tileGrid[2][0].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (tileIsNotAlreadySelected(tileGrid[2][0])) {
//                    deselectTile(selectedView[0]);
//                    selectTile(tileGrid[2][0]);
//                } else {
//                    deselectTile(tileGrid[2][0]);
//                }
//            }
//        });
//
//        tileGrid[3][0].setTag(R.drawable.tile);
//        tileGrid[3][0].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (tileIsNotAlreadySelected(tileGrid[3][0])) {
//                    deselectTile(selectedView[0]);
//                    selectTile(tileGrid[3][0]);
//                } else {
//                    deselectTile(tileGrid[3][0]);
//                }
//            }
//        });
//
//        tileGrid[4][0].setTag(R.drawable.tile);
//        tileGrid[4][0].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (tileIsNotAlreadySelected(tileGrid[4][0])) {
//                    deselectTile(selectedView[0]);
//                    selectTile(tileGrid[4][0]);
//                } else {
//                    deselectTile(tileGrid[4][0]);
//                }
//            }
//        });
//
//        tileGrid[5][0].setTag(R.drawable.tile);
//        tileGrid[5][0].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (tileIsNotAlreadySelected(tileGrid[5][0])) {
//                    deselectTile(selectedView[0]);
//                    selectTile(tileGrid[5][0]);
//                } else {
//                    deselectTile(tileGrid[5][0]);
//                }
//            }
//        });
//
//        tileGrid[6][0].setTag(R.drawable.tile);
//        tileGrid[6][0].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (tileIsNotAlreadySelected(tileGrid[6][0])) {
//                    deselectTile(selectedView[0]);
//                    selectTile(tileGrid[6][0]);
//                } else {
//                    deselectTile(tileGrid[6][0]);
//                }
//            }
//        });
//
//        tileGrid[0][1].setTag(R.drawable.tile);
//        tileGrid[0][1].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (tileIsNotAlreadySelected(tileGrid[0][1])) {
//                    deselectTile(selectedView[0]);
//                    selectTile(tileGrid[0][1]);
//                } else {
//                    deselectTile(tileGrid[0][1]);
//                }
//            }
//        });
//
//        tileGrid[1][1].setTag(R.drawable.tile);
//        tileGrid[1][1].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (tileIsNotAlreadySelected(tileGrid[1][1])) {
//                    deselectTile(selectedView[0]);
//                    selectTile(tileGrid[1][1]);
//                } else {
//                    deselectTile(tileGrid[1][1]);
//                }
//            }
//        });
//
//        tileGrid[2][1].setTag(R.drawable.tile);
//        tileGrid[2][1].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (tileIsNotAlreadySelected(tileGrid[2][1])) {
//                    deselectTile(selectedView[0]);
//                    selectTile(tileGrid[2][1]);
//                } else {
//                    deselectTile(tileGrid[2][1]);
//                }
//            }
//        });
//
//        tileGrid[2][1].setTag(R.drawable.tile);
//        tileGrid[2][1].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (tileIsNotAlreadySelected(tileGrid[2][1])) {
//                    deselectTile(selectedView[0]);
//                    selectTile(tileGrid[2][1]);
//                } else {
//                    deselectTile(tileGrid[2][1]);
//                }
//            }
//        });
    }

    private void deselectTile(ImageView imageView) {
        if (aTileIsSelected()) {
            changeTileImage(imageView);
            selectedView[0] = null;
        }
    }

    private boolean tileIsNotAlreadySelected(ImageView imageView) {
        if (imageView.getTag().equals(R.drawable.tile)
                || imageView.getTag().equals(R.drawable.tile_access_denied)
                || imageView.getTag().equals(R.drawable.tile_tank_up)
                || imageView.getTag().equals(R.drawable.tile_tank_right)
                || imageView.getTag().equals(R.drawable.tile_tank_down)
                || imageView.getTag().equals(R.drawable.tile_tank_left)
                || imageView.getTag().equals(R.drawable.tile_flag)) {
            return true;
        } else {
            return false;
        }
    }

    private void selectTile(ImageView imageView) {
        changeTileImage(imageView);
        selectedView[0] = imageView;
    }

    private boolean aTileIsSelected() {
        return (selectedView[0] != null);
    }

    private void changeTileImage(ImageView imageView) {
        int drawableID = 0;
        switch((int) imageView.getTag()) {
            case R.drawable.tile:
                imageView.setImageResource(drawableID = R.drawable.selected_tile);
                break;
            case R.drawable.tile_access_denied:
                imageView.setImageResource(drawableID = R.drawable.selected_tile_access_denied);
                break;
            case R.drawable.tile_tank_up:
                imageView.setImageResource(drawableID = R.drawable.selected_tile_tank_up);
                break;
            case R.drawable.tile_tank_right:
                imageView.setImageResource(drawableID = R.drawable.selected_tile_tank_right);
                break;
            case R.drawable.tile_tank_down:
                imageView.setImageResource(drawableID = R.drawable.selected_tile_tank_down);
                break;
            case R.drawable.tile_tank_left:
                imageView.setImageResource(drawableID = R.drawable.selected_tile_tank_left);
                break;
            case R.drawable.tile_flag:
                imageView.setImageResource(drawableID = R.drawable.selected_tile_flag);
                break;
            case R.drawable.selected_tile:
                imageView.setImageResource(drawableID = R.drawable.tile);
                break;
            case R.drawable.selected_tile_access_denied:
                imageView.setImageResource(drawableID = R.drawable.tile_access_denied);
                break;
            case R.drawable.selected_tile_tank_up:
                imageView.setImageResource(drawableID = R.drawable.tile_tank_up);
                break;
            case R.drawable.selected_tile_tank_right:
                imageView.setImageResource(drawableID = R.drawable.tile_tank_right);
                break;
            case R.drawable.selected_tile_tank_down:
                imageView.setImageResource(drawableID = R.drawable.tile_tank_down);
                break;
            case R.drawable.selected_tile_tank_left:
                imageView.setImageResource(drawableID = R.drawable.tile_tank_left);
                break;
            case R.drawable.selected_tile_flag:
                imageView.setImageResource(drawableID = R.drawable.tile_flag);
        }
        imageView.setTag(drawableID);
    }

    private void deletePreviousRobotLocation() {
        if (robotLocation[0] != null) {
            robotLocation[0].setImageResource(R.drawable.tile);
            robotLocation[0].setTag(R.drawable.tile);
            robotLocation[0] = null;
        }
    }

    private void deletePreviousFlagLocation() {
        if (flagLocation[0] != null) {
            flagLocation[0].setImageResource(R.drawable.tile);
            flagLocation[0].setTag(R.drawable.tile);
            flagLocation[0] = null;
        }
    }

    private void clearTileAndDeleteLocation() {
        selectedView[0].setImageResource(R.drawable.selected_tile);
        selectedView[0].setTag(R.drawable.selected_tile);
        if (selectedTileIsRobotLocation()) {
            robotLocation[0] = null;
        } else if (selectedTileIsFlagLocation()) {
            flagLocation[0] = null;
        } else if (selectedTileIsAnObstacleLocation()) {
            obstacleLocations.remove(selectedView[0]);
        }
    }

    private boolean selectedTileIsRobotLocation() {
        return (selectedView[0] == robotLocation[0]);
    }

    private boolean selectedTileIsFlagLocation() {
        return (selectedView[0] == flagLocation[0]);
    }

    private boolean selectedTileIsAnObstacleLocation() {
        return (obstacleLocations.contains(selectedView[0]));
    }

    private ArrayList<int[]> calculateShortestPath(int[] coordinatesRobot, int[]coordinatesFlag, ArrayList<int[]> coordinatesObstacles) {
        ShortestPath shortestPath = new ShortestPath();
        ArrayList<int[]> shortestPathCoordinates;
        if (coordinatesObstacles.isEmpty()) {
            shortestPathCoordinates = shortestPath.pathComputer(coordinatesRobot, coordinatesFlag);
        } else {
            shortestPathCoordinates = shortestPath.pathComputer(coordinatesRobot, coordinatesFlag, coordinatesObstacles);
        }
        markShortestPathGreen(shortestPathCoordinates);
        return shortestPathCoordinates;
    }

    private void markShortestPathGreen(List<int[]> shortestPath) {
        for (int[] coordinates: shortestPath) {
            changeTileImageToGreen(tileGrid[coordinates[0]][coordinates[1]]);
            System.out.println(coordinates[0] + ", " + coordinates[1]);
        }
    }

    private void changeTileImageToGreen(ImageView imageView) {
        if (imageView == null) {
            System.out.println("imageView is null");
        } else {
            int drawableID = 0;
            switch ((int) imageView.getTag()) {
                case R.drawable.tile:
                    imageView.setImageResource(drawableID = R.drawable.green_tile);
                    break;
                case R.drawable.tile_tank_up:
                    imageView.setImageResource(drawableID = R.drawable.green_tile_tank_up);
                    break;
                case R.drawable.tile_tank_right:
                    imageView.setImageResource(drawableID = R.drawable.green_tile_tank_right);
                    break;
                case R.drawable.tile_tank_down:
                    imageView.setImageResource(drawableID = R.drawable.green_tile_tank_down);
                    break;
                case R.drawable.tile_tank_left:
                    imageView.setImageResource(drawableID = R.drawable.green_tile_tank_left);
                    break;
                case R.drawable.tile_flag:
                    imageView.setImageResource(drawableID = R.drawable.green_tile_flag);
                    break;
                case R.drawable.selected_tile:
                    imageView.setImageResource(drawableID = R.drawable.green_tile);
                    selectedView[0] = null;
                    break;
                case R.drawable.selected_tile_tank_up:
                    imageView.setImageResource(drawableID = R.drawable.green_tile_tank_up);
                    selectedView[0] = null;
                    break;
                case R.drawable.selected_tile_tank_right:
                    imageView.setImageResource(drawableID = R.drawable.green_tile_tank_right);
                    selectedView[0] = null;
                    break;
                case R.drawable.selected_tile_tank_down:
                    imageView.setImageResource(drawableID = R.drawable.green_tile_tank_down);
                    selectedView[0] = null;
                    break;
                case R.drawable.selected_tile_tank_left:
                    imageView.setImageResource(drawableID = R.drawable.green_tile_tank_left);
                    selectedView[0] = null;
                    break;
                case R.drawable.selected_tile_flag:
                    imageView.setImageResource(drawableID = R.drawable.green_tile_flag);
                    selectedView[0] = null;
            }
            imageView.setTag(drawableID);
        }
    }

    private void printLocations() {
        System.out.println("robotLocation: " + (robotLocation[0] != null));
        System.out.println("flagLocation: " + (flagLocation[0] != null));
        System.out.println("obstacleLocations: " + obstacleLocations.size());
        System.out.println();
    }

    private int[] parseImageViewToCoordinates(ImageView imageView) {
        for (int y = 0 ; y < 7 ; y++) {
            for (int x = 0 ; x < 7 ; x++) {
                if (imageView == tileGrid[x][y]) {
                    return new int[]{x, y};
                }
            }
        }
        // this code will never be reached
        return new int[]{0,0};
    }

    private void sendCommandsToRobot(String commandsForRobot) {
        CommandsSender sender = new CommandsSender();
        sender.execute(commandsForRobot);
    }
}