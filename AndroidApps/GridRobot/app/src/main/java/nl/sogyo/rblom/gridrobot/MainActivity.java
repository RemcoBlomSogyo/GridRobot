package nl.sogyo.rblom.gridrobot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
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
        final ImageView goButton = (ImageView) findViewById(R.id.goButton);
        ImageView newButton = (ImageView) findViewById(R.id.newButton);

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
            }
        });

        wastebinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aTileIsSelected()) {
                    clearTileAndDeleteLocation();
                }
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
            }
        });

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    goButton.setImageResource(R.drawable.green_go_button);
                }
            }
        });

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goButton.setImageResource(R.drawable.go_button);

                selectedView[0] = null;
                robotLocation[0] = null;
                robotDirection = null;
                flagLocation[0] = null;
                obstacleLocations.clear();

                for (int y = 0 ; y < 7 ; y++) {
                    for (int x = 0 ; x < 7 ; x++) {
                        tileGrid[x][y].setImageResource(R.drawable.tile);
                        tileGrid[x][y].setTag(R.drawable.tile);
                    }
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
        Collections.reverse(shortestPathCoordinates);
        markShortestPathGreen(shortestPathCoordinates);
        return shortestPathCoordinates;
    }

    private void markShortestPathGreen(List<int[]> shortestPath) {
        for (int[] coordinates: shortestPath) {
            changeTileImageToGreen(tileGrid[coordinates[0]][coordinates[1]]);
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
