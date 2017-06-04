package application.view;

import javafx.scene.layout.Pane;

import static application.view.ViewController.TOTAL_PANE;
import static application.view.ViewController.INSTALLATION_PROGRESS_PANE_INDEX;

/**
 * Created by diman on 12.05.2017.
 *
 *  This is class that handles all view panes
 *
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
        arrayOfPanes[7] = controller.getInstallationProgressPane();

        indexActivePane = 0;
        showPane(indexActivePane);

        for (int i=indexActivePane+1; i < TOTAL_PANE; i++) {
            hidePane(i);
        }
    }

    private void showPane(int numPane) {
        if (numPane >= TOTAL_PANE || numPane < 0) return;
        arrayOfPanes[numPane].setDisable(false);
        arrayOfPanes[numPane].setVisible(true);
    }

    private void hidePane(int numPane) {
        if (numPane >= TOTAL_PANE || numPane < 0) return;
        arrayOfPanes[numPane].setDisable(true);
        arrayOfPanes[numPane].setVisible(false);
    }

    public void setActivePane(int newIndexActivePane) {
        hidePane(indexActivePane);
        showPane(newIndexActivePane);
        indexActivePane = newIndexActivePane;
    }

    public int getIndexActivePane() {
        return indexActivePane;
    }
    
    public void showInstallationProgressBar() {
//        for (int i=indexActivePane; i < INSTALLATION_PROGRESS_PANE_INDEX; i++) {
//            hidePane(i);
//        }
        hidePane(indexActivePane);
        showPane(INSTALLATION_PROGRESS_PANE_INDEX);
    }

    public void hideInstallationProgressBar() {
        hidePane(INSTALLATION_PROGRESS_PANE_INDEX);
        showPane(indexActivePane);
    }

//    public Pane[] getPanes() {
//        return arrayOfPanes;
//    }
}
