package application.view;

import javafx.scene.layout.Pane;

import static application.view.ViewController.TOTAL_PANE;

/**
 * Created by diman on 12.05.2017.
 */
public class PanesContainer {

    private int indexActivePane = 0;
    private Pane[] arrayOfPanes = new Pane[TOTAL_PANE];

    public void initPanes(ViewController controller) {

        arrayOfPanes[0] = controller.getFirstStepPane();
        arrayOfPanes[1] = controller.getSecondStepPane();
        arrayOfPanes[2] = controller.getThirdStepPane();
        arrayOfPanes[3] = controller.getFourthStepPane();
        arrayOfPanes[4] = controller.getFifthStepPane();
        arrayOfPanes[5] = controller.getSixthStepPane();
        arrayOfPanes[6] = controller.getSeventhStepPane();

        indexActivePane = 0;
        showPane(indexActivePane);

        for (int i=indexActivePane+1; i < TOTAL_PANE; i++) {
            hidePane(i);
        }
    }

    private void showPane(int numPane) {
        if (numPane >= TOTAL_PANE || numPane < 0) return;
        arrayOfPanes[numPane].setVisible(true);
        arrayOfPanes[numPane].setDisable(false);
    }

    private void hidePane(int numPane) {
        if (numPane >= TOTAL_PANE || numPane < 0) return;
        arrayOfPanes[numPane].setVisible(false);
        arrayOfPanes[numPane].setDisable(true);
    }

    public void setActivePane(int newIndexActivePane) {
        hidePane(indexActivePane);
        showPane(newIndexActivePane);
        indexActivePane = newIndexActivePane;
    }

    public int getIndexActivePane() {
        return indexActivePane;
    }

    public Pane[] getPanes() {
        return arrayOfPanes;
    }
}
