package nl.sogyo.rblom.gridrobot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView[] selectedView = new ImageView[]{null};
    ImageView[] robotLocation = new ImageView[]{null};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView tankButton = (ImageView) findViewById(R.id.tankButton);
        ImageView accessDeniedButton = (ImageView) findViewById(R.id.accessDeniedButton);
        ImageView turnButton = (ImageView) findViewById(R.id.turnButton);
        ImageView wastebinButton = (ImageView) findViewById(R.id.wastebinButton);
        ImageView flagButton = (ImageView) findViewById(R.id.flagButton);

        final ImageView[][] tileGrid = new ImageView[7][7];
        tileGrid[0][0] = (ImageView) findViewById(R.id.imageView);
        tileGrid[0][1] = (ImageView) findViewById(R.id.imageView2);
        tileGrid[0][2] = (ImageView) findViewById(R.id.imageView3);
        tileGrid[0][3] = (ImageView) findViewById(R.id.imageView4);
        tileGrid[0][4] = (ImageView) findViewById(R.id.imageView5);
        tileGrid[0][5] = (ImageView) findViewById(R.id.imageView6);
        tileGrid[0][6] = (ImageView) findViewById(R.id.imageView7);
        tileGrid[1][0] = (ImageView) findViewById(R.id.imageView8);
        tileGrid[1][1] = (ImageView) findViewById(R.id.imageView9);
        tileGrid[1][2] = (ImageView) findViewById(R.id.imageView10);

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

        tileGrid[0][0].setTag(R.drawable.tile);
        tileGrid[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tileGrid[0][0].setImageDrawable(getDrawable(R.drawable.selected_tile));
                if (tileIsNotAlreadySelected(tileGrid[0][0])) {
                    deselectTile(selectedView[0]);
                    selectTile(tileGrid[0][0]);
                } else {
                    deselectTile(tileGrid[0][0]);
                }
            }
        });

        tileGrid[0][1].setTag(R.drawable.tile);
        tileGrid[0][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tileGrid[0][1].setImageDrawable(getDrawable(R.drawable.selected_tile));
                if (tileIsNotAlreadySelected(tileGrid[0][1])) {
                    deselectTile(selectedView[0]);
                    selectTile(tileGrid[0][1]);
                } else {
                    deselectTile(tileGrid[0][1]);
                }
            }
        });

        tileGrid[0][2].setTag(R.drawable.tile);
        tileGrid[0][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tileGrid[0][2].setImageDrawable(getDrawable(R.drawable.selected_tile));
                if (tileIsNotAlreadySelected(tileGrid[0][2])) {
                    deselectTile(selectedView[0]);
                    selectTile(tileGrid[0][2]);
                } else {
                    deselectTile(tileGrid[0][2]);
                }
            }
        });

        tileGrid[0][3].setTag(R.drawable.tile);
        tileGrid[0][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tileGrid[0][3].setImageDrawable(getDrawable(R.drawable.selected_tile));
                if (tileIsNotAlreadySelected(tileGrid[0][3])) {
                    deselectTile(selectedView[0]);
                    selectTile(tileGrid[0][3]);
                } else {
                    deselectTile(tileGrid[0][3]);
                }
            }
        });

        tileGrid[0][4].setTag(R.drawable.tile);
        tileGrid[0][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tileGrid[0][4].setImageDrawable(getDrawable(R.drawable.selected_tile));
                if (tileIsNotAlreadySelected(tileGrid[0][4])) {
                    deselectTile(selectedView[0]);
                    selectTile(tileGrid[0][4]);
                } else {
                    deselectTile(tileGrid[0][4]);
                }
            }
        });

        tileGrid[0][5].setTag(R.drawable.tile);
        tileGrid[0][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tileGrid[0][5].setImageDrawable(getDrawable(R.drawable.selected_tile));
                if (tileIsNotAlreadySelected(tileGrid[0][5])) {
                    deselectTile(selectedView[0]);
                    selectTile(tileGrid[0][5]);
                } else {
                    deselectTile(tileGrid[0][5]);
                }
            }
        });

        tileGrid[0][6].setTag(R.drawable.tile);
        tileGrid[0][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tileGrid[0][6].setImageDrawable(getDrawable(R.drawable.selected_tile));
                if (tileIsNotAlreadySelected(tileGrid[0][6])) {
                    deselectTile(selectedView[0]);
                    selectTile(tileGrid[0][6]);
                } else {
                    deselectTile(tileGrid[0][6]);
                }
            }
        });

        tileGrid[1][0].setTag(R.drawable.tile);
        tileGrid[1][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tileGrid[1][0].setImageDrawable(getDrawable(R.drawable.selected_tile));
                if (tileIsNotAlreadySelected(tileGrid[1][0])) {
                    deselectTile(selectedView[0]);
                    selectTile(tileGrid[1][0]);
                } else {
                    deselectTile(tileGrid[1][0]);
                }
            }
        });

        tileGrid[1][1].setTag(R.drawable.tile);
        tileGrid[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tileGrid[1][1].setImageDrawable(getDrawable(R.drawable.selected_tile));
                if (tileIsNotAlreadySelected(tileGrid[1][1])) {
                    deselectTile(selectedView[0]);
                    selectTile(tileGrid[1][1]);
                } else {
                    deselectTile(tileGrid[1][1]);
                }
            }
        });

        tileGrid[1][2].setTag(R.drawable.tile);
        tileGrid[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tileGrid[1][2].setImageDrawable(getDrawable(R.drawable.selected_tile));
                if (tileIsNotAlreadySelected(tileGrid[1][2])) {
                    deselectTile(selectedView[0]);
                    selectTile(tileGrid[1][2]);
                } else {
                    deselectTile(tileGrid[1][2]);
                }
            }
        });
    }

    private void deselectTile(ImageView imageView) {
        //imageView.setImageResource(R.drawable.tile);
        //imageView.setTag(R.drawable.tile);
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
        //imageView.setImageResource(R.drawable.selected_tile);
        //imageView.setTag(R.drawable.selected_tile);
        changeTileImage(imageView);
//        if (aTileIsSelected()) {
//            deselectTile(selectedView[0]);
//        }
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
}
