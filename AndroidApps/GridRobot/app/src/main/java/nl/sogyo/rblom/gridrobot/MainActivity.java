package nl.sogyo.rblom.gridrobot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ImageView[] selectedView = new ImageView[]{null};
    ImageView[] robotLocation = new ImageView[]{null};
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

        tankButton.setTag(R.drawable.tank_button_up);
        tankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aTileIsSelected()) {
                    deletePreviousRobotLocation();
                    switch ((int) tankButton.getTag()) {
                        case R.drawable.tank_button_up:
                            selectedView[0].setImageResource(R.drawable.selected_tile_tank_up);
                            selectedView[0].setTag(R.drawable.selected_tile_tank_up);
                            break;
                        case R.drawable.tank_button_right:
                            selectedView[0].setImageResource(R.drawable.selected_tile_tank_right);
                            selectedView[0].setTag(R.drawable.selected_tile_tank_right);
                            break;
                        case R.drawable.tank_button_down:
                            selectedView[0].setImageResource(R.drawable.selected_tile_tank_down);
                            selectedView[0].setTag(R.drawable.selected_tile_tank_down);
                            break;
                        case R.drawable.tank_button_left:
                            selectedView[0].setImageResource(R.drawable.selected_tile_tank_left);
                            selectedView[0].setTag(R.drawable.selected_tile_tank_left);
                    }
                    robotLocation[0] = selectedView[0];
                }
            }
        });

        accessDeniedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aTileIsSelected()) {
                    if (selectedTileIsRobotLocation()) {
                        robotLocation[0] = null;
                    }
                    selectedView[0].setImageResource(R.drawable.selected_tile_access_denied);
                    selectedView[0].setTag(R.drawable.selected_tile_access_denied);
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
                    if (selectedTileIsRobotLocation()) {
                        robotLocation[0] = null;
                    }
                    selectedView[0].setImageResource(R.drawable.selected_tile);
                    selectedView[0].setTag(R.drawable.selected_tile);
                }
            }
        });

        flagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aTileIsSelected()) {
                    if (selectedTileIsRobotLocation()) {
                        robotLocation[0] = null;
                    }
                    selectedView[0].setImageResource(R.drawable.selected_tile_flag);
                    selectedView[0].setTag(R.drawable.selected_tile_flag);
                }
            }
        });

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //List<int[]> shortestPath = findShortestPath();
                List<int[]> shortestPath = new ArrayList<>();
                shortestPath.add(new int[]{0, 1});
                shortestPath.add(new int[]{0, 0});
                shortestPath.add(new int[]{1, 0});
                shortestPath.add(new int[]{2, 0});
                shortestPath.add(new int[]{3, 0});
                shortestPath.add(new int[]{4, 0});
                markShortestPathGreen(shortestPath);
            }
        });

        tileGrid[0][0].setTag(R.drawable.tile);
        tileGrid[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tileIsNotAlreadySelected(tileGrid[0][0])) {
                    deselectTile(selectedView[0]);
                    selectTile(tileGrid[0][0]);
                } else {
                    deselectTile(tileGrid[0][0]);
                }
            }
        });

        tileGrid[1][0].setTag(R.drawable.tile);
        tileGrid[1][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tileIsNotAlreadySelected(tileGrid[1][0])) {
                    deselectTile(selectedView[0]);
                    selectTile(tileGrid[1][0]);
                } else {
                    deselectTile(tileGrid[1][0]);
                }
            }
        });

        tileGrid[2][0].setTag(R.drawable.tile);
        tileGrid[2][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tileIsNotAlreadySelected(tileGrid[2][0])) {
                    deselectTile(selectedView[0]);
                    selectTile(tileGrid[2][0]);
                } else {
                    deselectTile(tileGrid[2][0]);
                }
            }
        });

        tileGrid[3][0].setTag(R.drawable.tile);
        tileGrid[3][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tileIsNotAlreadySelected(tileGrid[3][0])) {
                    deselectTile(selectedView[0]);
                    selectTile(tileGrid[3][0]);
                } else {
                    deselectTile(tileGrid[3][0]);
                }
            }
        });

        tileGrid[4][0].setTag(R.drawable.tile);
        tileGrid[4][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tileIsNotAlreadySelected(tileGrid[4][0])) {
                    deselectTile(selectedView[0]);
                    selectTile(tileGrid[4][0]);
                } else {
                    deselectTile(tileGrid[4][0]);
                }
            }
        });

        tileGrid[5][0].setTag(R.drawable.tile);
        tileGrid[5][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tileIsNotAlreadySelected(tileGrid[5][0])) {
                    deselectTile(selectedView[0]);
                    selectTile(tileGrid[5][0]);
                } else {
                    deselectTile(tileGrid[5][0]);
                }
            }
        });

        tileGrid[6][0].setTag(R.drawable.tile);
        tileGrid[6][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tileIsNotAlreadySelected(tileGrid[6][0])) {
                    deselectTile(selectedView[0]);
                    selectTile(tileGrid[6][0]);
                } else {
                    deselectTile(tileGrid[6][0]);
                }
            }
        });

        tileGrid[0][1].setTag(R.drawable.tile);
        tileGrid[0][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tileIsNotAlreadySelected(tileGrid[0][1])) {
                    deselectTile(selectedView[0]);
                    selectTile(tileGrid[0][1]);
                } else {
                    deselectTile(tileGrid[0][1]);
                }
            }
        });

        tileGrid[1][1].setTag(R.drawable.tile);
        tileGrid[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tileIsNotAlreadySelected(tileGrid[1][1])) {
                    deselectTile(selectedView[0]);
                    selectTile(tileGrid[1][1]);
                } else {
                    deselectTile(tileGrid[1][1]);
                }
            }
        });

        tileGrid[2][1].setTag(R.drawable.tile);
        tileGrid[2][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tileIsNotAlreadySelected(tileGrid[2][1])) {
                    deselectTile(selectedView[0]);
                    selectTile(tileGrid[2][1]);
                } else {
                    deselectTile(tileGrid[2][1]);
                }
            }
        });
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

    private boolean selectedTileIsRobotLocation() {
        return (selectedView[0] == robotLocation[0]);
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
}
